/**
 * 
 */
package com.autoStock.indicator;

import com.autoStock.indicator.results.ResultsSTORSI;
import com.autoStock.signal.SignalDefinitions.IndicatorParameters;
import com.autoStock.signal.SignalDefinitions.SignalMetricType;
import com.autoStock.taLib.Core;
import com.autoStock.taLib.MAType;
import com.autoStock.taLib.MInteger;
import com.autoStock.taLib.RetCode;

/**
 * @author Kevin Kowalewski
 *
 */
public class IndicatorOfSTORSI extends IndicatorBase<ResultsSTORSI> {	
	public IndicatorOfSTORSI(IndicatorParameters indicatorParameters, CommonAnalysisData commonAnlaysisData, Core taLibCore, SignalMetricType signalMetricType) {
		super(indicatorParameters, commonAnlaysisData, taLibCore, signalMetricType);
	}
	
	@Override
	public ResultsSTORSI analyze(){
		results = new ResultsSTORSI(50);
		results.arrayOfDates = arrayOfDates;
		
		RetCode returnCode = taLibCore.stochRsi(0, endIndex, arrayOfPriceClose, 15, 12, 3, MAType.Trima, new MInteger(), new MInteger(), results.arrayOfPercentK, results.arrayOfPercentD);
		handleAnalysisResult(returnCode);
		
		return results;
	}
}
