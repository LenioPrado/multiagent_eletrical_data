package simulator.cases.appliances;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import equipment.Equipment;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.FixedTimeOfUse;

public class DishwasherSimulation extends BaseSimulationCase {

	public DishwasherSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "DishwasherSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Dishwasher(state, 250, new CyclicUsageParameter(3, new FixedTimeOfUse(120, new double[] {10, 15, 21.5})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}
}