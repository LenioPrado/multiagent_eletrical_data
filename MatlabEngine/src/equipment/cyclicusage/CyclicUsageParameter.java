package equipment.cyclicusage;

import time.FixedTimeOfUse;

public class CyclicUsageParameter {

	private int _loadsPerDay;
	private FixedTimeOfUse _timeOfUse;

	public CyclicUsageParameter(int loadsPerDay, FixedTimeOfUse timeOfUse) throws Exception {
		_loadsPerDay = loadsPerDay;
		_timeOfUse = timeOfUse;
		
		if(timeOfUse == null)
			throw new Exception("TimeOfUse cannot be null");
	}

	public int getLoadsPerDay() {
		return _loadsPerDay;
	}

	public FixedTimeOfUse getTimeOfUse() {
		return _timeOfUse;
	}
}