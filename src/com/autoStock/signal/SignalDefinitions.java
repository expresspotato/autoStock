/**
 * 
 */
package com.autoStock.signal;
import com.autoStock.guage.SignalGuage;
import com.autoStock.tools.MathTools;
import com.autoStock.types.basic.ImmutableEnum;
import com.autoStock.types.basic.ImmutableInteger;

/**
 * @author Kevin Kowalewski
 *
 */
public class SignalDefinitions {
	public static enum SignalSource{
		from_algorithm,
		from_market_trend,
		from_news,
		from_manual
	}
	
	public static enum SignalPointType {
		long_entry,
		long_exit,
		short_entry,
		short_exit,
		no_change,
		none
	}
	
	public static enum SignalGuageType {
//		guage_peak,
//		guage_trough,
		guage_threshold_met,
		guage_threshold_left,
		;
	}
	
	public static enum SignalBounds {
		bounds_upper,
		bounds_lower,
	}
	
	public static enum SignalCoherence {
		//cusp
		//fringe
		//peak
		//trough
		//steady
		//tapered
		//crossover
	}
	
	public enum SignalMetricType {
		metric_adx(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (MathTools.pow(input - 10, 0.8));}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 48)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -44)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_ppc(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) ((input - 1) * 3000);}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 48)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -44)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_di(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) input * 1;}},
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_cci(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (input / 6);}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -15)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_left), SignalBounds.bounds_upper, 21)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_left), SignalBounds.bounds_upper, 22)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_left), SignalBounds.bounds_lower, -15)}),
			
		metric_macd(
			new NormalizeInterface(){@Override public int normalize(double input){return (int) (input * 800);}}, 
			new ImmutableInteger(45), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -10)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, 20)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_rsi(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (input - 45);}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, 100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_trix(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (input * 600);}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 48)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -44)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_roc(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (input * 25);}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 11)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -18)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_mfi(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (input * 1.0) - 50;}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 20)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -15)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_willr(
			new NormalizeInterface(){@Override public int normalize(double input) {return (int) (input  + 50);}}, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 30)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -40)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
			
		metric_storsi(null, 
			new ImmutableInteger(30), new ImmutableInteger(1),
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, 48)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -44)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_lower, -100)},
			new SignalGuage[]{new SignalGuage(new ImmutableEnum<SignalGuageType>(SignalGuageType.guage_threshold_met), SignalBounds.bounds_upper, -100)}),
		
		metric_candlestick_group,
		
		none,
		no_change,
		mixed,
		;
		
		NormalizeInterface normalizeInterface;
		public ImmutableInteger periodLength;
		public ImmutableInteger maxSignalAverage;
		public SignalGuage[] arrayOfSignalGuageForLongEntry;
		public SignalGuage[] arrayOfSignalGuageForLongExit;
		public SignalGuage[] arrayOfSignalGuageForShortEntry;
		public SignalGuage[] arrayOfSignalGuageForShortExit;
		
		private SignalMetricType(){}
		
		private SignalMetricType(NormalizeInterface normalizeInterface, ImmutableInteger periodLength, ImmutableInteger maxSignalAverage, SignalGuage[] arrayOfSignalGuageForLongEntry, SignalGuage[] arrayOfSignalGuageForLongExit, SignalGuage[] arrayOfSignalGuageForShortEntry, SignalGuage[] arrayOfSignalGuageForShortExit){
			this.periodLength = periodLength;
			this.normalizeInterface = normalizeInterface;
			this.maxSignalAverage = maxSignalAverage;
			this.arrayOfSignalGuageForLongEntry = arrayOfSignalGuageForLongEntry;
			this.arrayOfSignalGuageForLongExit = arrayOfSignalGuageForLongExit;
			this.arrayOfSignalGuageForShortEntry = arrayOfSignalGuageForShortEntry;
			this.arrayOfSignalGuageForShortExit = arrayOfSignalGuageForShortExit;
		}

		public synchronized int getNormalizedValue(double input) {
			return this.normalizeInterface.normalize(input);
		}
	}
}
