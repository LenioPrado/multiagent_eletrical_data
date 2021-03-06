package equipment.appliances;

import enums.EquipmentState;
import enums.StoveBurnerSize;
import equipment.ConnectableEquipment;
import time.AllDayPeriodsTimeOfUse;

public class Stove extends ConnectableEquipment {

	public Stove(EquipmentState state, AllDayPeriodsTimeOfUse timeOfUse, StoveBurnerSize burnersSizes, double[] burnersIntensity) throws Exception {
		super(state);
		
		if (burnersIntensity == null || burnersIntensity.length == 0 )
			throw new Exception("BurnersIntensity vector cannot be null or empty!!!");
		
		if (timeOfUse == null)
			throw new Exception("TimeOfUse vector cannot be null or empty!!!");		
		
		addValues(timeOfUse, burnersSizes, burnersIntensity);
	}
	
	private void addValues(AllDayPeriodsTimeOfUse timeOfUse, StoveBurnerSize burnersSizes, double[] burnersIntensity) throws Exception {
		addValue("TOU_Mor1", timeOfUse.getMorningAmountOfPowerOnHours());
		addValue("TOU_Noon1", timeOfUse.getNoonAmountOfPowerOnHours());
		addValue("TOU_Nig1", timeOfUse.getNightAmountOfPowerOnHours());
		
		addValue("switchedOn_Mor1", timeOfUse.getMorningClockTimeWhenIsUsed());
		addValue("switchedOn_Noon1", timeOfUse.getNoonClockTimeWhenIsUsed());
		addValue("switchedOn_Nig1", timeOfUse.getNightClockTimeWhenIsUsed());
				
		for (int index = 0; index < burnersIntensity.length; index++) {
			addValue("B"+(index+1),burnersIntensity[index]/100);
			addValue("SB"+(index+1),getSizeBurnerEquivalentValue(burnersSizes));
		}
	}
	
	private int getSizeBurnerEquivalentValue(StoveBurnerSize sizeOfBurner) {
		int size = 0;
		
		switch (sizeOfBurner) {
		case SIX:
			size = 1200;
			break;
		case SEVEN:
			size = 1500;
			break;
		case EIGHT:
			size = 2100;
			break;			
		case NINE:
			size = 2400;
			break;
		}
		return size;
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Range/";
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "OnOff_Range";
	}
}