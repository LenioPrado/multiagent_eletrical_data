package simulator.cases.appliances;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dryer;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.FixedTimeOfUse;

public class DryerSimulation extends BaseSimulationCase {

	public DryerSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "DryerSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10,15,20})));
	}
}