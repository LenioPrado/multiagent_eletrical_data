package equipment.hvac;

import enums.EquipmentState;
import equipment.rates.Rates;
import home.Room;
import home.Thermostat;

public class AirConditioner extends Acclimatization {
	
	public AirConditioner(EquipmentState state, Thermostat thermostat, Room[] rooms, double BTU, Rates rates) throws Exception {
		super(state, thermostat, rooms, rates);
		
		double coolCap = calculateCoolCap(BTU);
		
		addValue("Q_AC", -coolCap);
		addValue("AA or Heat", 2);
		addValue("Cost_AA/Rates/Rate_Ele_WH",1);
		addValue("Cost_AA/Rates/Rate_Gas_WH",0);
		
		addSpecificKeyValue("Relay","OnOutputValue",0);
		addSpecificKeyValue("Relay","OffOutputValue",1);
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