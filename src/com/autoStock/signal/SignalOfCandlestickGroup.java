package com.autoStock.signal;

import com.autoStock.signal.SignalDefinitions.SignalMetricType;
import com.autoStock.signal.SignalDefinitions.SignalParameters;

/**
 * @author Kevin Kowalewski
 *
 */
public class SignalOfCandlestickGroup extends SignalBase {
	
	public SignalOfCandlestickGroup(SignalParameters signalParameters) {
		super(SignalMetricType.metric_candlestick_group, signalParameters);
	}
}