/**
 * 
 */
package com.autoStock.signal;

import com.autoStock.signal.SignalDefinitions.SignalMetricType;
import com.autoStock.signal.SignalDefinitions.SignalParameters;

/**
 * @author Kevin Kowalewski
 *
 */
public class SignalOfSAR extends SignalBase {
	public SignalOfSAR(SignalParameters signalParameters){
		super(SignalMetricType.metric_sar, signalParameters);
	}
}