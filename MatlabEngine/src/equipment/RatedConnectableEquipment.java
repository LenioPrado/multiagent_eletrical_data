package equipment;

import enums.EquipmentState;
import equipment.rates.Rates;

public abstract class RatedConnectableEquipment extends RatedEquipment {
	
	public RatedConnectableEquipment(EquipmentState state, Rates rates) throws Exception {
		super(rates);
		
		if(getEquipmentOnOffKey() == null || getEquipmentOnOffKey().isEmpty())
			throw new Exception("EquipmentOnOffKey cannot be null or empty");
		
		addValue(getEquipmentOnOffKey(), state.getValue());
	}
	
	protected abstract String getEquipmentOnOffKey();
}