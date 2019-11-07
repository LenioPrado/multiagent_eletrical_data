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

public class SimulatorGroup1Case3 extends BaseSimulationCase {
		
	public SimulatorGroup1Case3(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 34000, 60, 83, 0.38f), getRates());
		new TubeFluorescentLighting(37, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {1,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,16}, new double[] {2.9,1.25}), StoveBurnerSize.SIX, new double[] {3,20,34,50});
		Room r1 = new Room(5.43,3.87,3.5,1.1,1.63, RoomWindow.YES);
		Room r2 = new Room(2.39,5.16,2.25,1.8,1.75, RoomWindow.YES);
		Room r3 = new Room(2.15,2.63,2.96,2.1,2.17, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.3, 1.2, 1.4, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(62, new double[] {11,17,18})));
		new IncandescentLighting(96, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {1,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(46, new double[] {8,12})));
		new Dishwasher(state, 231, new CyclicUsageParameter(2, new FixedTimeOfUse(49, new double[] {11,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(33, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {4,4})), getRates());
		new Clothwasher(state, 265, new CyclicUsageParameter(2, new FixedTimeOfUse(64, new double[] {9,15})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
