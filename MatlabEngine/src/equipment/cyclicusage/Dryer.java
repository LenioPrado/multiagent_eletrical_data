package equipment.cyclicusage;

import enums.EquipmentState;

public class Dryer extends CyclicUsageEquipment {
	
	public Dryer(EquipmentState state, CyclicUsageParameter usageParameter) throws Exception {
		super(state, usageParameter);
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Dryer/";
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "DY_OnOff";
	}
}