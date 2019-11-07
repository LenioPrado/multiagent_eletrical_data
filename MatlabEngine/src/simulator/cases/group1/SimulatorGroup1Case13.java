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

public class SimulatorGroup1Case13 extends BaseSimulationCase {
		
	public SimulatorGroup1Case13(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 51000, 52, 87, 0.91f), getRates());
		new TubeFluorescentLighting(36, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {4,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,22}, new double[] {0.78,2.9,2.81}), StoveBurnerSize.NINE, new double[] {1,17,56,45});
		Room r1 = new Room(4.43,2.44,3.36,1,2.23, RoomWindow.YES);
		Room r2 = new Room(5.3,2.05,2.19,1.14,1.49, RoomWindow.YES);
		Room r3 = new Room(3.72,4.92,2.36,1.2,1.39, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.3, 1.1, 1.1, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(89, new double[] {10,12})));
		new IncandescentLighting(74, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {1,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(64, new double[] {6,16})));
		new Dishwasher(state, 256, new CyclicUsageParameter(2, new FixedTimeOfUse(66, new double[] {9,17})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {5,5})), getRates());
		new Clothwasher(state, 175, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {8,17,20})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
