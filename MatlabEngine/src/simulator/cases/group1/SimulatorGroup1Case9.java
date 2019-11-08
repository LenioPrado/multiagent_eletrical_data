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

public class SimulatorGroup1Case9 extends BaseSimulationCase {
		
	public SimulatorGroup1Case9(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 35000, 64, 102, 0.83f), getRates());
		new TubeFluorescentLighting(37, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {2,4})), getRates());
		Thermostat thermostat = new Thermostat(27.8,  new double[] {6,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,13,20}, new double[] {2.33,1.78,2.66}), StoveBurnerSize.SIX, new double[] {2,74,33,88});
		Room r1 = new Room(4.07,4.02,2.43,1.09,1.46, RoomWindow.YES);
		Room r2 = new Room(4.49,3.83,3.02,1.32,1.32, RoomWindow.YES);
		Room r3 = new Room(4.43,5.04,2.54,1.04,1.53, RoomWindow.YES);
		Room r4 = new Room(3.43,3.57,3.11,1.83,1.94, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.2, 1.1, 1.6, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(84, new double[] {8})));
		new IncandescentLighting(94, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {4,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(41, new double[] {6,15,22})));
		new Dishwasher(state, 278, new CyclicUsageParameter(1, new FixedTimeOfUse(61, new double[] {11})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(27, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {4,3})), getRates());
		new Clothwasher(state, 182, new CyclicUsageParameter(3, new FixedTimeOfUse(84, new double[] {11,13,21})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 39908, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffBattery();
	}
}
