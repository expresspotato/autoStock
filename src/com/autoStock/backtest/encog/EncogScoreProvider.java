package com.autoStock.backtest.encog;

import java.util.ArrayList;

import org.encog.ml.MLRegression;
import org.encog.neural.networks.training.CalculateScore;

import com.autoStock.algorithm.core.AlgorithmDefinitions.AlgorithmMode;
import com.autoStock.algorithm.core.AlgorithmRemodeler;
import com.autoStock.backtest.AlgorithmModel;
import com.autoStock.backtest.BacktestEvaluation;
import com.autoStock.backtest.BacktestEvaluationBuilder;
import com.autoStock.backtest.SingleBacktest;
import com.autoStock.signal.SignalCache;
import com.autoStock.tools.Benchmark;
import com.autoStock.trading.types.HistoricalData;

/**
 * @author Kevin Kowalewski
 *
 */
public class EncogScoreProvider implements CalculateScore {
	private HistoricalData historicalData;	
	public static long runCount;
	private AlgorithmModel algorithmModel;
	public static ArrayList<EncogTest> listOfEncogTest = new ArrayList<EncogTest>();
	private SignalCache signalCache;
	private Benchmark bench = new Benchmark();
	
	public void setDetails(AlgorithmModel algorithmModel, HistoricalData historicalData){
		this.algorithmModel = algorithmModel;
		this.historicalData = historicalData;
	}
	
	@Override //Needs to stay synchronized despite the performance hit. Looks like a bug with Encog!
	public double calculateScore(MLRegression network) {
//		Co.print("--> Calculate score... " + algorithmModel.getUniqueIdentifier() + " ");
		//Co.println(BacktestEvaluationReader.getPrecomputedEvaluation(exchange, symbol).toString());
		
		bench.tick();
		
		SingleBacktest singleBacktest = new SingleBacktest(historicalData, AlgorithmMode.mode_backtest_single);
		new AlgorithmRemodeler(singleBacktest.backtestContainer.algorithm, algorithmModel).remodel(true, true, true, false);
		singleBacktest.selfPopulateBacktestData();
		singleBacktest.backtestContainer.algorithm.signalGroup.signalOfEncog.setNetwork(network);
//		singleBacktest.backtestContainer.setSignalCache(signalCache);
		singleBacktest.runBacktest();
		
		BacktestEvaluation backtestEvaluation = new BacktestEvaluationBuilder().buildEvaluation(singleBacktest.backtestContainer, false, false);
		
		runCount++;
		
		double score = backtestEvaluation.getScore();
		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		new PersistBasicNetwork().save(baos, network);
//		String hash = MiscTools.getHash(baos.toString());
//		Co.print(hash);
//		Co.println(" " + score);
		
//		bench.printTick("Scored");
		
		return score > 0 ? score : Double.MIN_VALUE;
	}

	@Override
	public boolean shouldMinimize() {
		return false;
	}
	
	public static class EncogTest {
		public MLRegression network;
		public BacktestEvaluation backtestEvaluation;
		public String table;
		
		public EncogTest(MLRegression network, BacktestEvaluation backtestEvaluation, String table) {
			this.network = network;
			this.backtestEvaluation = backtestEvaluation;
			this.table = table;
		}
	}

	public void setSignalCache(SignalCache signalCache) {
		this.signalCache = signalCache;
	}

	public AlgorithmModel getAlgorithmModel() {
		return algorithmModel;
	}
}
