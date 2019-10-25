package equipment.appliances;

import enums.EquipmentState;
import equipment.ConnectableEquipment;
import time.AllDayPeriodsTimeOfUse;

public class Stove extends ConnectableEquipment {

	public Stove(EquipmentState state, AllDayPeriodsTimeOfUse timeOfUse, int[] burnersSizes, int[] burnersIntensity) throws Exception {
		super(state);

		if (burnersSizes == null || burnersSizes.length == 0 )
			throw new Exception("BurnersSizes vector cannot be null or empty!!!");
		
		if (burnersIntensity == null || burnersIntensity.length == 0 )
			throw new Exception("BurnersIntensity vector cannot be null or empty!!!");
		
		if (burnersSizes.length != burnersIntensity.length)
			throw new Exception("BurnersSizes and BurnersIntensity vectors must have the same size!!!");
		
		if (timeOfUse == null)
			throw new Exception("TimeOfUse vector cannot be null or empty!!!");		
		
		addValues(timeOfUse, burnersSizes, burnersIntensity);
	}
	
	private void addValues(AllDayPeriodsTimeOfUse timeOfUse, int[] burnersSizes, int[] burnersIntensity) throws Exception {
		addValue("switchedOn_Mor1", timeOfUse.getMorningAmountOfPowerOnHours());
		addValue("switchedOn_Noon1", timeOfUse.getNoonAmountOfPowerOnHours());
		addValue("switchedOn_Nig1", timeOfUse.getNightAmountOfPowerOnHours());
		
		addValue("TOU_Mor1", timeOfUse.getMorningClockTimeWhenIsUsed());
		addValue("TOU_Noon1", timeOfUse.getNoonClockTimeWhenIsUsed());
		addValue("TOU_Nig1", timeOfUse.getNightClockTimeWhenIsUsed());
				
		for (int index = 0; index < burnersIntensity.length; index++) {
			addValue("B"+(index+1),burnersIntensity[index]/100);
			addValue("SB"+(index+1),getSizeBurnerEquivalentValue(burnersSizes[index]));
		}
	}
	
	private int getSizeBurnerEquivalentValue(int sizeOfBurner) {
		int size = 0;
		
		switch (sizeOfBurner) {
		case 1:
			size = 1200;
			break;
		case 2:
			size = 1500;
			break;
		case 3:
			size = 2100;
			break;			
		case 4:
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