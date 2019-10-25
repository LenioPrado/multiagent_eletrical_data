package simulator;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.rates.Rates;

public abstract class BaseSimulationCase {

	private Rates _rates;
	
	public BaseSimulationCase(Rates rates) throws Exception {
		_rates = rates;
	}
	
	public void setSimulationValues() throws Exception {
		getEquipment(EquipmentState.ON);
	}
	
	public void resetSimulationValues() throws Exception {
		getEquipment(EquipmentState.OFF);
	}
	
	public abstract String getSimulationDescription();
	
	protected abstract Equipment getEquipment(EquipmentState state) throws Exception;
	
	protected Rates getRates() {
		return _rates;
	}
}