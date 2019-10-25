package equipment.cyclicusage;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;

public class Dishwasher extends CyclicUsageEquipment {
	
	public Dishwasher(EquipmentState state, int annualPower, CyclicUsageParameter usageParameter, EquipmentType type, Hotwater hotWater) throws Exception {
		super(state, usageParameter);
	
		addValue("TypeDW", type.getValue());
		addValue("AnnualPower", annualPower);
		addValue("WH conected", hotWater.getValue());
	}
	
	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Dishwasher/";
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "DW_OnOff";
	}
}