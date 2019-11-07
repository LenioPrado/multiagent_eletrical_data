package simulator;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.cyclicusage.Dryer;
import equipment.cyclicusage.PoolPump;
import equipment.energysources.Battery;
import equipment.energysources.Photovoltaic;
import equipment.energysources.Wind;
import equipment.hvac.AirConditioner;
import equipment.hvac.Furnace;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
import equipment.lighting.CompactFluorescentLighting;
import equipment.lighting.IncandescentLighting;
import equipment.lighting.LightingParameter;
import equipment.lighting.TubeFluorescentLighting;
import equipment.rates.Rates;
import home.Room;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public abstract class BaseSimulationCase {

	private EquipmentState _offState = EquipmentState.OFF;
	private Rates _rates;
	
	public BaseSimulationCase(Rates rates) throws Exception {
		_rates = rates;
	}
	
	public abstract void setSimulationValues() throws Exception;
	
	public abstract void resetSimulationValues() throws Exception;
	
	public abstract String getSimulationDescription();
	
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
	
	protected Room[] getRooms() throws Exception {
		Room r1 = new Room(1, 1, 1, 1, 1, RoomWindow.YES);
		Room r2 = new Room(1, 1, 1, 1, 1, RoomWindow.YES);
		Room r3 = new Room(1, 1, 1, 1, 1, RoomWindow.YES);
		Room r4 = new Room(1, 1, 1, 1, 1, RoomWindow.YES);

		return new Room[] {r1, r2, r3, r4};		
	}
	
	protected void createOffAirConditioner() throws Exception {
		new AirConditioner(_offState, getRooms(), 30000, getRates());
	}
	
	protected void createOffFurnace() throws Exception {
		new Furnace(_offState, getRooms(), 30000, getRates());
	}
	
	protected void createOffClothwasher() throws Exception {
		new Clothwasher(_offState, 1, new CyclicUsageParameter(1, new FixedTimeOfUse(68, new double[] {10})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
	}
	
	protected void createOffRefrigerator() throws Exception {
		new Refrigerator(_offState, 127, 1.3, 1.2, 1.2, getRates());
	}
	
	protected void createOffStove() throws Exception {
		new Stove(_offState, new AllDayPeriodsTimeOfUse(new double[] {10,12}, new double[] {2.26,1.2}), StoveBurnerSize.NINE, new double[] {95,52,29,89});
	}
	
	protected void createOffDryer() throws Exception {
		new Dryer(_offState, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10, 15, 20})));		
	}
	
	protected void createOffPoolPump() throws Exception {
		new PoolPump(_offState, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));		
	}
	
	protected void createOffWaterHeater() throws Exception {
		new WaterHeater(_offState, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184,  0.62f), getRates());		
	}
	
	protected void createOffDishwasher() throws Exception {
		new Dishwasher(_offState, 241, new CyclicUsageParameter(2, new FixedTimeOfUse(48, new double[] {11,13})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);		
	}
	
	protected void createOffCompactFluorescentLighting() throws Exception {
		new CompactFluorescentLighting(0, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,21}, new double[] {3,3})), getRates());		
	}
	
	protected void createOffIncandescentLighting() throws Exception {
		new IncandescentLighting(0, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {4,3})), getRates());		
	}
	
	protected void createOffTubeFluorescentLighting() throws Exception {
		new TubeFluorescentLighting(0, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {2,3})), getRates());
	}
	
	protected void createOffBattery() throws Exception {
		new Battery(_offState, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());		
	}
	
	protected void createOffPhotovoltaic() throws Exception {
		new Photovoltaic(_offState, getPhotovoltaicHoursRadiation(), getRates());		
	}
	
	protected void createOffWind() throws Exception {
		new Wind(_offState, getWindHoursPower(), getRates());
	}
}