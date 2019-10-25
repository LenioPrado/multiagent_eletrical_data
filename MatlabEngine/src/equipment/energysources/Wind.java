package equipment.energysources;

import enums.EquipmentState;
import equipment.rates.Rates;

public class Wind extends HourlyEnergySource {
	
	public <T> Wind(EquipmentState state, T[] hoursPower, Rates rates) throws Exception {
		super(state, hoursPower, rates);
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "WD_OnOff";
	}
	
	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Wind/";
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