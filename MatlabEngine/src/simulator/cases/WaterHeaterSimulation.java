package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;

public class WaterHeaterSimulation extends BaseSimulationCase {

	private WaterHeaterParameters _parameters;
	
	public WaterHeaterSimulation(WaterHeaterParameters parameters, Rates rates) throws Exception {
		super(rates);
		_parameters = parameters;
	}
	
	@Override
	public String getSimulationDescription() {
		return "WaterHeaterSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new WaterHeater(state, _parameters, getRates());
	}
}