package equipment.hvac;

import enums.EquipmentState;
import equipment.rates.Rates;
import home.Room;

public class Furnace extends Acclimatization {

	public Furnace(EquipmentState state, Room[] rooms, double BTU, Rates rates) throws Exception {
		super(state, rooms, rates);
		
		double coolCap = calculateCoolCap(BTU);
		
		addValue("Q_AC", coolCap);
		addValue("AA or Heat", 1);
		addValue("Cost_AA/Rates/Rate_Ele_WH",0);
		addValue("Cost_AA/Rates/Rate_Gas_WH",1);
		
		addSpecificKeyValue("Relay","OnOutputValue",1);
		addSpecificKeyValue("Relay","OffOutputValue",0);
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "AA_OnOff";
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Air Conditioner/";
	}
}