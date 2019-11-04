package simulator;

import enums.EquipmentState;
import equipment.Equipment;
import equipment.rates.Rates;
import home.Room;

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
	
	protected Integer[] getBatteryHoursPower() {
		return new Integer[] {107, 55, 96, 44, 112, 69, 61, 65, 84, 70, 99, 79, 92, 103, 56, 95, 66, 22, 43, 61, 42, 91, 109, 22};
	}
	
	protected Double[] getPhotovoltaicHoursRadiation() {
		return new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 70.00, 165.00, 328.6, 486.6, 568.6, 395.00, 296.00, 425.2, 566.4, 299.8, 216.00, 56.4, 19.8, 0.0, 0.0, 0.0, 0.0, 0.0};
	}
	
	protected Double[] getWindHoursPower() {
		return new Double[] { 
				1787.0, 2875.0, 3456.0, 4584.0, 5172.0, 2209.0, 4541.0, 2905.0, 2484.0, 3030.0, 2879.0, 2059.0, 
				5192.0, 5083.0, 4576.0, 5055.0, 3986.0, 3702.0, 3103.0, 4101.0, 3762.0, 4011.0, 1809.0, 3782.0
		};
	}
	
	protected void setRoomsValues() throws Exception {
		
		Double[] room1Term1Gains = {2.7488e-07,0.039108,0.039108,0.078216};
		Double[] room1Term2Gains = {8.5839e-07,8.5839e-07,1.624,0.12213,1.5018};
		
		Double[] room2Term1Gains = {6.8066e-07,0.039108,0.039108,0.078216};
		Double[] room2Term2Gains = {2.5752e-06,2.5752e-06,0.89888,0.14796,0.75092};
		
		Double[] room3Term1Gains = {6.0825e-07,0.039108,0.039108,0.078216};
		Double[] room3Term2Gains = {2.2533e-06,2.2533e-06,0.80194,0.14488,0.65706};
		
		Double[] room4Term1Gains = {7.7264e-07,0.039108,0.039108,0.078216};
		Double[] room4Term2Gains = {3.0044e-06,3.0044e-06,1.0281,0.15207,0.87608};
		
		new Room(1, room1Term1Gains, room1Term2Gains, 0.65454, 21, 21);
		new Room(2, room2Term1Gains, room2Term2Gains, 0.11515, 21, 21);
		new Room(3, room3Term1Gains, room3Term2Gains, 0.11638, 21, 21);
		new Room(4, room4Term1Gains, room4Term2Gains, 0.11393, 21, 21);
	}	
}