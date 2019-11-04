package simulator.cases.appliances;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.PoolPump;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.FixedTimeOfUse;

public class PoolPumpSimulation extends BaseSimulationCase {

	public PoolPumpSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "PoolPumpSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));
	}
}