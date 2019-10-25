package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.energysources.Wind;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;

public class WindSimulation extends BaseSimulationCase {

	private Double[] hoursPower = { 
			5121.0,3640.0,4860.0,1931.0,1704.0,3055.0,5148.0,4372.0,5020.0,3476.0,2061.0,3687.0,
			4832.0,4424.0,3298.0,5168.0,3912.0,1973.0,3631.0,3140.0,3153.0,3463.0,4993.0,3443.0
	};
	
	public WindSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "WindSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Wind(state, hoursPower, getRates());
	}
}