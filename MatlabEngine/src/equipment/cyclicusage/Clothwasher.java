package equipment.cyclicusage;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.WaterTemperature;

public class Clothwasher extends CyclicUsageEquipment {

	public Clothwasher(EquipmentState state, int annualPower, CyclicUsageParameter usageParameter, WaterTemperature waterTemperature, EquipmentType type, Hotwater hotWater) throws Exception {
		super(state, usageParameter);

		addValue("TypeDW", type.getValue());
		addValue("AnnualPower", annualPower);
		addValue("WH conected", hotWater.getValue());
		addValue("WaterTemp", waterTemperature.getValue());
	}
	
	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Clothwasher/";
	}

	@Override
	protected String getEquipmentOnOffKey() {
		return "DW_OnOff";
	}
}