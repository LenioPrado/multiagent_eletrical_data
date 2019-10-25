package equipment;

import enums.EquipmentState;

public abstract class ConnectableEquipment extends Equipment {
	
	public ConnectableEquipment(EquipmentState state) throws Exception {
		addValue(getEquipmentOnOffKey(), state.getValue());
	}
	
	protected abstract String getEquipmentOnOffKey();
}