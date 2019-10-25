package equipment.energysources;

import enums.EquipmentState;
import equipment.rates.Rates;
import time.FixedTimeOfUse;

public class Battery extends HourlyEnergySource {
	
	public <T> Battery(EquipmentState state, FixedTimeOfUse timeOfUse, T[] hoursPower, int totalPower, Rates rates) throws Exception {
		super(state, hoursPower, rates);

		addValue("MinOfLoad",timeOfUse.getMinutesOfUse());
		addValue("WhenUsed1",timeOfUse.getFirstTimeUsage());
		addValue("WhenUsed2",timeOfUse.getSecondTimeUsage());
		addValue("WhenUsed3",timeOfUse.getThirdTimeUsage());
		addValue("Power25", totalPower);
	}
	
	@Override
	protected String getEquipmentOnOffKey() {
		return "BT_OnOff";
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Battery/";
	}

	@Override
	protected String getHourVectorName() {
		return "Power";
	}
	
	@Override
	protected void setEquipmentRates() throws Exception {
		addValue("Rates/Constant", getRates().getOffPeak());
		addValue("Rates/Constant2", getRates().getMidPeak());
		addValue("Rates/Constant1", getRates().getOnPeak());
	}
}