package simulator.cases;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.energysources.Photovoltaic;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;

public class PVSimulation extends BaseSimulationCase {

	private Double[] hoursRadiation = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 25.85, 74.18, 195.32, 281.63, 453.22, 425.31, 556.11, 445.22, 485.13, 360.17, 253.0, 126.11, 33.25, 0.0, 0.0, 0.0};
	
	public PVSimulation(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "PVSimulation";
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return new Photovoltaic(state, hoursRadiation, getRates());
	}
}