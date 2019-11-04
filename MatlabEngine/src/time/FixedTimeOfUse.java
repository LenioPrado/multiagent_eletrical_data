package time;

public class FixedTimeOfUse {

	private int _minutesOfUse;
	private double[] _clockTimesWhenIsUsed;
	
	public FixedTimeOfUse(int minutesOfUse, double[] clockTimesWhenIsUsed) throws Exception {
		_minutesOfUse = minutesOfUse;
		_clockTimesWhenIsUsed = clockTimesWhenIsUsed;
		
		if (clockTimesWhenIsUsed == null || clockTimesWhenIsUsed.length < 1 || clockTimesWhenIsUsed.length > 3) {
			throw new Exception("ClockTimesWhenIsUsed vector must have 1 to 3 elements");
		}
	}

	public int getMinutesOfUse() {
		return _minutesOfUse;
	}
	
	public double[] getClockTimesWhenIsUsed() {
		return _clockTimesWhenIsUsed;
	}

	public double getFirstTimeUsage() {
		return _clockTimesWhenIsUsed[0];
	}

	public double getSecondTimeUsage() {
		return _clockTimesWhenIsUsed.length > 1 ? _clockTimesWhenIsUsed[1] : 0;
	}

	public double getThirdTimeUsage() {
		return _clockTimesWhenIsUsed.length > 2 ? _clockTimesWhenIsUsed[2] : 0;
	}	
}