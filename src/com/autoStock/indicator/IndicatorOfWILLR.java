/**
 * 
 */
package com.autoStock.indicator;

import java.util.Arrays;

import com.autoStock.indicator.results.ResultsWILLR;
import com.autoStock.signal.SignalDefinitions.SignalMetricType;
import com.autoStock.taLib.Core;
import com.autoStock.taLib.MInteger;
import com.autoStock.taLib.RetCode;
import com.autoStock.types.basic.ImmutableInteger;

/**
 * @author Kevin Kowalewski
 *
 */
public class IndicatorOfWILLR extends IndicatorBase{
	public ResultsWILLR results;
	
	public IndicatorOfWILLR(ImmutableInteger periodLength, int resultsetLength, CommonAnalysisData commonAnlaysisData, Core taLibCore, SignalMetricType signalMetricType) {
		super(periodLength, resultsetLength, commonAnlaysisData, taLibCore, signalMetricType);
	}
	
	public ResultsWILLR analyize(){
		results = new ResultsWILLR(resultsetLength+1); //WILLR Specific
		
		results.arrayOfDates = commonAnlaysisData.arrayOfDates;
		results.arrayOfPrice = commonAnlaysisData.arrayOfPriceClose;
		
		RetCode returnCode = taLibCore.willR(0, endIndex, arrayOfPriceHigh, arrayOfPriceLow, arrayOfPriceClose, periodLength.value-1, new MInteger(), new MInteger(), results.arrayOfWILLR);
		
		results.arrayOfWILLR = Arrays.copyOfRange(results.arrayOfWILLR, 1, resultsetLength +1); //WILLR specific
		
		handleAnalysisResult(returnCode);
		
		return results;
	}
}
