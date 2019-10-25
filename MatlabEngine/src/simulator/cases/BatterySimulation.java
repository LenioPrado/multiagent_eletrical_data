package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.energysources.Battery;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.FixedTimeOfUse;

public class BatterySimulation extends BaseSimulationCase {

	private Integer[] hoursPower = {35, 55, 20, 70, 25, 30, 45, 60, 45, 80, 85, 55, 92, 76, 51, 31, 62, 87, 97, 75, 61, 43, 29, 59};
	
	public BatterySimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "BatterySimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Battery(state, new FixedTimeOfUse(50, new double[] {25, 25, 25}), hoursPower, 1500, getRates());
	}
}