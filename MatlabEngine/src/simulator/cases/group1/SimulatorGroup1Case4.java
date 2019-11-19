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

public class SimulatorGroup1Case4 extends BaseSimulationCase {
		
	public SimulatorGroup1Case4(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 36000, 55, 89, 0.88f), getRates());
		new TubeFluorescentLighting(25, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,23}, new double[] {5,1})), getRates());
		Thermostat thermostat = new Thermostat(24.4,  new double[] {6,12,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,14,18}, new double[] {2.08,2.91,0.88}), StoveBurnerSize.SIX, new double[] {68,45,13,49});
		Room r1 = new Room(4.24,3.87,2.35,2.19,1.9, RoomWindow.YES);
		Room r2 = new Room(2.59,3.84,2.5,2.43,1.9, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 220, 1.1, 1.3, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(84, new double[] {8})));
		new IncandescentLighting(84, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {5,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(36, new double[] {5,17,22})));
		new Dishwasher(state, 246, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {9,14})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(33, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {5,2})), getRates());
		new Clothwasher(state, 200, new CyclicUsageParameter(1, new FixedTimeOfUse(49, new double[] {11})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 35345, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
