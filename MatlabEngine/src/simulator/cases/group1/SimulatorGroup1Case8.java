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

public class SimulatorGroup1Case8 extends BaseSimulationCase {
		
	public SimulatorGroup1Case8(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 34000, 50, 127, 0.65f), getRates());
		new TubeFluorescentLighting(22, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {5,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,16,20}, new double[] {0.93,2.23,0.61}), StoveBurnerSize.EIGHT, new double[] {52,79,27,15});
		Room r1 = new Room(4.3,3.09,3.17,1.4,1, RoomWindow.YES);
		Room r2 = new Room(4.23,3.67,2.74,2,1.11, RoomWindow.YES);
		Room r3 = new Room(3.4,5.12,2.67,2.12,1.53, RoomWindow.YES);
		Room r4 = new Room(2.16,3.96,2.5,2.07,2.49, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.2, 1, 1.6, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(73, new double[] {6,12})));
		new IncandescentLighting(88, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {4,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(37, new double[] {7})));
		new Dishwasher(state, 254, new CyclicUsageParameter(2, new FixedTimeOfUse(69, new double[] {5,14})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(31, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {1,20}, new double[] {3,5})), getRates());
		new Clothwasher(state, 211, new CyclicUsageParameter(1, new FixedTimeOfUse(54, new double[] {11})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
