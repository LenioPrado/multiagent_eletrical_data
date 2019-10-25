package equipment.cyclicusage;

import enums.EquipmentState;

public class PoolPump extends CyclicUsageEquipment {
	
	public PoolPump(EquipmentState state, CyclicUsageParameter usageParameter) throws Exception {
		super(state, usageParameter);
	}

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/PoolPump/";
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "PP_OnOff";
	}
}