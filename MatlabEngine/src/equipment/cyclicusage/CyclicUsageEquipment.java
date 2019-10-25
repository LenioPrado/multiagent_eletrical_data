package equipment.cyclicusage;

import enums.EquipmentState;
import equipment.ConnectableEquipment;

public abstract class CyclicUsageEquipment extends ConnectableEquipment {

	public CyclicUsageEquipment(EquipmentState state, CyclicUsageParameter cyclicUsageParameter) throws Exception {
		super(state);
		
		if(cyclicUsageParameter == null)
			throw new Exception("CyclicUsageParameter cannot be null"); 

		addValue("LoadsDay", cyclicUsageParameter.getLoadsPerDay());
		addValue("MinOfLoad", cyclicUsageParameter.getTimeOfUse().getMinutesOfUse());
		addValue("WhenUsed1", cyclicUsageParameter.getTimeOfUse().getFirstTimeUsage());
		addValue("WhenUsed2", cyclicUsageParameter.getTimeOfUse().getSecondTimeUsage());
		addValue("WhenUsed3", cyclicUsageParameter.getTimeOfUse().getThirdTimeUsage());
	}
}