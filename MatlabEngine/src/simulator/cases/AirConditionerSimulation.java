package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.hvac.AirConditioner;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;

public class AirConditionerSimulation extends BaseSimulationCase {

	public AirConditionerSimulation(Rates rates) throws Exception {
		super(rates);		
	}
	
	@Override
	public String getSimulationDescription() {
		return "AirConditionerSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new AirConditioner(state, 60000, getRates());
	}
}