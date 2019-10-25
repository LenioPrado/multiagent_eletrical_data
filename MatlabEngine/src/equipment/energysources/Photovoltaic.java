package equipment.energysources;

import enums.EquipmentState;
import equipment.rates.Rates;

public class Photovoltaic extends HourlyEnergySource {

	public <T> Photovoltaic(EquipmentState state, T[] hoursRadiation, Rates rates) throws Exception {
		super(state, hoursRadiation, rates);
	}
	
	@Override
	protected String getEquipmentOnOffKey() {
		return "PV_OnOff";
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/PV/";
	}

	@Override
	protected String getHourVectorName() {
		return "Radiation";
	}
	
	@Override
	protected void setEquipmentRates() throws Exception {
		addValue("Rates/Constant", getRates().getOffPeak());
		addValue("Rates/Constant2", getRates().getMidPeak());
		addValue("Rates/Constant1", getRates().getOnPeak());
	}
}