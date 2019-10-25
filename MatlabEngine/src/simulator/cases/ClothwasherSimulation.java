package simulator.cases;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.WaterTemperature;
import equipment.Equipment;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.FixedTimeOfUse;

public class ClothwasherSimulation extends BaseSimulationCase {

	public ClothwasherSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "ClothwasherSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Clothwasher(state, 154, new CyclicUsageParameter(3, new FixedTimeOfUse(60, new double[] {8, 13, 18})), 
				WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}
}