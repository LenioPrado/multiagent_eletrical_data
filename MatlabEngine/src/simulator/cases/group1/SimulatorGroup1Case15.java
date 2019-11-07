package simulator.cases.group1;

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
import equipment.hvac.AirConditioner;
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

public class SimulatorGroup1Case15 extends BaseSimulationCase {
		
	public SimulatorGroup1Case15(Rates rates) throws Exception {
		super(rates);
	}

	@Override
	public void setSimulationValues() throws Exception {		
		setOnCaseValues(EquipmentState.ON);
		setOffCaseValues(EquipmentState.OFF);
	}

	@Override
	public void resetSimulationValues() throws Exception {
		setOnCaseValues(EquipmentState.OFF);
		setOffCaseValues(EquipmentState.OFF);
	}
	
	@Override
	public String getSimulationDescription() {
		return "SimulationGroup1Case1";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 44000, 50, 101, 0.13f), getRates());
		new TubeFluorescentLighting(34, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {3,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {1.25}), StoveBurnerSize.NINE, new double[] {15,49,82,14});
		Room r1 = new Room(5.08,3.45,3.11,1.17,1.8, RoomWindow.YES);
		Room r2 = new Room(4.53,2.99,2.98,1.85,1.71, RoomWindow.YES);
		Room r3 = new Room(5.02,2.96,2.95,2.21,2.43, RoomWindow.YES);
		Room r4 = new Room(3.81,2.47,3.33,1.4,1.51, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1, 1, 1.4, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(35, new double[] {8,15,19})));
		new IncandescentLighting(95, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,21}, new double[] {1,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(44, new double[] {10,14})));
		new Dishwasher(state, 252, new CyclicUsageParameter(1, new FixedTimeOfUse(69, new double[] {5})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {2,2})), getRates());
		new Clothwasher(state, 207, new CyclicUsageParameter(1, new FixedTimeOfUse(57, new double[] {11})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
