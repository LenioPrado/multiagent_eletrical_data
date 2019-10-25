package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.appliances.Stove;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;

public class StoveSimulation extends BaseSimulationCase {

	public StoveSimulation(Rates rates) throws Exception {
		super(rates);		
	}
	
	@Override
	public String getSimulationDescription() {
		return "StoveSimulation";
	}

	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,20.30}, new double[] {.66667,1.5,.5}), new int[] {6,6,6,6}, new int[] {60,40,40,60});
	}
}