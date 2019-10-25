package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.hvac.Furnace;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;

public class FurnaceSimulation extends BaseSimulationCase {

	public FurnaceSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "FurnaceSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Furnace(state, 32000, getRates());
	}
}