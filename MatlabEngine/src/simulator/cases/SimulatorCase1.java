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

public class SimulatorCase1 extends BaseSimulationCase {
		
	public SimulatorCase1(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	public String getSimulationDescription() {
		return "SimulatorCase1";
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
		WaterHeaterParameters parameters = new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184, 0.62f);
		new WaterHeater(state, parameters, getRates());
	}

	private void setDryerValues(EquipmentState state) throws Exception {
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10,15,20})));	
	}

	private void setClothwasherValues(EquipmentState state) throws Exception {
		new Clothwasher(state, 154, new CyclicUsageParameter(3, new FixedTimeOfUse(60, new double[] {8, 13, 18})), 
				WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}

	private void setDishwasherValues(EquipmentState state) throws Exception {
		new Dishwasher(state, 250, new CyclicUsageParameter(3, new FixedTimeOfUse(120, new double[] {10, 15, 21.5})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}

	private void setPoolPumpValues(EquipmentState state) throws Exception {
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));
	}

	private void setBatteryValues(EquipmentState state) throws Exception {		
		Integer[] hoursPower = {35, 55, 20, 70, 25, 30, 45, 60, 45, 80, 85, 55, 92, 76, 51, 31, 62, 87, 97, 75, 61, 43, 29, 59};
		new Battery(state, new FixedTimeOfUse(50, new double[] {25, 25, 25}), hoursPower, 1500, getRates());
	}

	private void setPVValues(EquipmentState state) throws Exception {		
		Double[] hoursRadiation = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 25.85, 74.18, 195.32, 281.63, 453.22, 425.31, 556.11, 445.22, 485.13, 360.17, 253.0, 126.11, 33.25, 0.0, 0.0, 0.0};		
		new Photovoltaic(state, hoursRadiation, getRates());	
	}

	private void setWindValues(EquipmentState state) throws Exception {
		Double[] hoursPower = { 
				2965.0, 4220.0, 2825.0, 4555.0, 3220.0, 3711.0, 2430.0, 2000.0, 2650.0, 1700.0, 2440.0, 2250.0, 
				2680.0, 3100.0, 4500.0, 5150.0, 4550.0, 3200.0, 4100.0, 3600.0, 4000.0, 2600.0, 5220.0, 3600.0
		};		
		new Wind(state, hoursPower, getRates());	
	}

	private void setStoveValues(EquipmentState state) throws Exception {
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,20.30}, new double[] {.66667,1.5,.5}), new int[] {6,6,6,6}, new int[] {60,40,40,60});
	}

	private void setLightingValues(EquipmentState state) throws Exception {		
		// Set 'H_Many' params to 0 if no lighting provided.		
		new IncandescentLighting(100, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2, 5}, new double[] {5, 18})), getRates());
		new CompactFluorescentLighting(20, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2.5, 5.5}, new double[] {5.5, 15})), getRates());
		new TubeFluorescentLighting(15, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3, 6}, new double[] {5, 15})), getRates());
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
		new Refrigerator(state, 220, 0.9, 0.8, 1.7, getRates());
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