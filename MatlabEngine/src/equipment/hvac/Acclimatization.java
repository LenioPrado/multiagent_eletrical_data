package equipment.hvac;

import enums.EquipmentState;
import equipment.RatedConnectableEquipment;
import equipment.rates.Rates;

public abstract class Acclimatization extends RatedConnectableEquipment {
	
	public Acclimatization(EquipmentState state, Rates rates) throws Exception {
		super(state, rates);

		addGain("Cost_AA/AcHt", 1);
	}
	
	protected double calculateCoolCap(double BTU) {
		return BTU*3600/3.412;
	}
	
	@Override
	protected void setEquipmentRates() throws Exception {
		addValue("Cost_AA/Rates/Constant", getRates().getOffPeak());
		addValue("Cost_AA/Rates/Constant1", getRates().getMidPeak());
		addValue("Cost_AA/Rates/Constant2", getRates().getOnPeak());
	}
}