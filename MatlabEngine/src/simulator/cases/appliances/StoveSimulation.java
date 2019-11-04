package simulator.cases.appliances;

import enums.EquipmentState;
import enums.StoveBurnerSize;
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
		return new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,20.30}, new double[] {.66667,1.5,.5}), StoveBurnerSize.EIGHT, new double[] {60,40,40,60});
	}
}