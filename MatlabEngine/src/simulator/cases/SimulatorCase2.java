package simulator.cases;

import enums.AcclimatizationType;
import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.Equipment;
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
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorCase2 extends BaseSimulationCase {

	public SimulatorCase2(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "SimulatorCase2";
	}

	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return null;
	}
	
	@Override
	public void setSimulationValues() throws Exception {		
		define(EquipmentState.ON);
	}

	@Override
	public void resetSimulationValues() throws Exception {
		define(EquipmentState.OFF);
	}
	
	private void define(EquipmentState state) throws Exception {
		setWaterHeaterValues(state);
		setDryerValues(state);
		setClothwasherValues(state);
		setDishwasherValues(state);
		setPoolPumpValues(state);
		setBatteryValues(state);
		setPVValues(state);
		setWindValues(state);
		setStoveValues(state);
		setLightingValues(state);
		setAcclimatizationValues(state, AcclimatizationType.AIR_CONDITIONER);
		setRefrigeratorValues(state);
		setRoomsValues();
	}

	private void setWaterHeaterValues(EquipmentState state) throws Exception {
		WaterHeaterParameters parameters = new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 8, 23, 35000, 50, 100, 0.59f);
		new WaterHeater(state, parameters, getRates());
	}

	private void setDryerValues(EquipmentState state) throws Exception {
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(45, new double[] {12,14,16})));	
	}

	private void setClothwasherValues(EquipmentState state) throws Exception {
		new Clothwasher(state, 154, new CyclicUsageParameter(3, new FixedTimeOfUse(60, new double[] {13, 15, 17})), 
				WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}

	private void setDishwasherValues(EquipmentState state) throws Exception {
		new Dishwasher(state, 250, new CyclicUsageParameter(3, new FixedTimeOfUse(120, new double[] {14, 15, 16})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}

	private void setPoolPumpValues(EquipmentState state) throws Exception {
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {6, 7, 9})));
	}

	private void setBatteryValues(EquipmentState state) throws Exception {		
		Integer[] hoursPower = {107, 55, 96, 44, 112, 69, 61, 65, 84, 70, 99, 79, 92, 103, 56, 95, 66, 22, 43, 61, 42, 91, 109, 22};
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), hoursPower, 1500, getRates());
	}

	private void setPVValues(EquipmentState state) throws Exception {
		Double[] hoursRadiation = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 70.00, 165.00, 328.6, 486.6, 568.6, 395.00, 296.00, 425.2, 566.4, 299.8, 216.00, 56.4, 19.8, 0.0, 0.0, 0.0, 0.0, 0.0};		
		new Photovoltaic(state, hoursRadiation, getRates());	
	}

	private void setWindValues(EquipmentState state) throws Exception {
		Double[] hoursPower = { 
				1787.0, 2875.0, 3456.0, 4584.0, 5172.0, 2209.0, 4541.0, 2905.0, 2484.0, 3030.0, 2879.0, 2059.0, 
				5192.0, 5083.0, 4576.0, 5055.0, 3986.0, 3702.0, 3103.0, 4101.0, 3762.0, 4011.0, 1809.0, 3782.0
		};		
		new Wind(state, hoursPower, getRates());	
	}

	private void setStoveValues(EquipmentState state) throws Exception {
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,12,18.50}, new double[] {1.0,3.5,2.0}), new int[] {6,6,6,6}, new int[] {100,60,80,40});
	}

	private void setLightingValues(EquipmentState state) throws Exception {		
		// Set 'H_Many' params to 0 if no lighting provided.		
		new IncandescentLighting(100, new LightingParameter(8, new MorningNightTimeOfUse(new double[] {8, 15}, new double[] {9, 16})), getRates());
		new CompactFluorescentLighting(20, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {11, 20}, new double[] {3, 2})), getRates());
		new TubeFluorescentLighting(15, new LightingParameter(10, new MorningNightTimeOfUse(new double[] {5, 13}, new double[] {12, 20})), getRates());
	}

	private void setAcclimatizationValues(EquipmentState state, AcclimatizationType type) throws Exception {		
		switch (type) {
		case AIR_CONDITIONER:
			new AirConditioner(state, 48000, getRates());	
			break;
		case FURNACE:
			new Furnace(state, 40000, getRates());
			break;
		}
	}
	
	private void setRefrigeratorValues(EquipmentState state) throws Exception {
		new Refrigerator(state, 127, 1.5, 1.0, 2, getRates());
	}

	private void setRoomsValues() throws Exception {
		
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