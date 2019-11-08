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
import home.Thermostat;
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 55000, 52, 92, 0.98f), getRates());
		new TubeFluorescentLighting(20, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {1,19}, new double[] {5,2})), getRates());
		Thermostat thermostat = new Thermostat(24.6,  new double[] {6,11,15,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,12,22}, new double[] {0.7,3,1.93}), StoveBurnerSize.SIX, new double[] {10,32,24,35});
		Room r1 = new Room(4.78,4.42,2.45,2.15,2.16, RoomWindow.YES);
		Room r2 = new Room(4.32,2.39,2.17,1.47,2.27, RoomWindow.YES);
		Room r3 = new Room(2.39,3.96,2.38,1.12,2.44, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1, 1.3, 1.8, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {11,13})));
		new IncandescentLighting(61, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,23}, new double[] {4,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(45, new double[] {11,13,18})));
		new Dishwasher(state, 262, new CyclicUsageParameter(3, new FixedTimeOfUse(63, new double[] {6,14,22})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(37, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {5,1})), getRates());
		new Clothwasher(state, 230, new CyclicUsageParameter(2, new FixedTimeOfUse(38, new double[] {8,12})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 52302, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffBattery();
	}
}
