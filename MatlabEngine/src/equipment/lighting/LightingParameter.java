package equipment.lighting;

import time.MorningNightTimeOfUse;

public class LightingParameter {

	private int _howManyBulbs;
	private MorningNightTimeOfUse _timeOfUse;

	public LightingParameter(int howManyBulbs, MorningNightTimeOfUse timeOfUse) throws Exception {
		_howManyBulbs = howManyBulbs;		
		_timeOfUse = timeOfUse;
		
		if(_timeOfUse == null)
			throw new Exception("TimeOfUse cannot be null");	
	}

	public int getHowManyBulbs() {
		return _howManyBulbs;
	}

	public MorningNightTimeOfUse getTimeOfUse() {
		return _timeOfUse;
	}
}