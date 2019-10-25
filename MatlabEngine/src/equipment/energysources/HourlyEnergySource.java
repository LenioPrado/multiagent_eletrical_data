package equipment.energysources;

import enums.EquipmentState;
import equipment.RatedConnectableEquipment;
import equipment.rates.Rates;

public abstract class HourlyEnergySource extends RatedConnectableEquipment {

	public <T> HourlyEnergySource(EquipmentState state, T[] hoursVector, Rates rates) throws Exception {
		super(state, rates);
		
		if(hoursVector == null || hoursVector.length != 24) {
			throw new Exception("Hours vector must have 24 elements");
		}
		
		for (int hour = 0; hour < hoursVector.length; hour++) {
			String paramName = String.format("%s%s", getHourVectorName(), hour+1);
			addValue(paramName, String.valueOf(hoursVector[hour]));
		}
	}
	
	protected abstract String getHourVectorName();
}