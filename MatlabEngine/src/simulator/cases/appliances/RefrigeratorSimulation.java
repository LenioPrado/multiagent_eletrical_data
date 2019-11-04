package simulator.cases.appliances;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.appliances.Refrigerator;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;

public class RefrigeratorSimulation extends BaseSimulationCase {

	public RefrigeratorSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "RefrigeratorSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Refrigerator(state, 127, 0.9, 0.8, 1.7, getRates());
	}
}