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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 58000, 61, 97, 0.09f), getRates());
		new TubeFluorescentLighting(23, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {3,2})), getRates());
		Thermostat thermostat = new Thermostat(18,  new double[] {8,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,12,20}, new double[] {1.53,1.03,1.21}), StoveBurnerSize.NINE, new double[] {70,63,10,85});
		Room r1 = new Room(4.28,4.03,3.05,1.85,2.29, RoomWindow.YES);
		Room r2 = new Room(3.29,4.64,2.54,2.03,1.6, RoomWindow.YES);
		Room r3 = new Room(2.63,3.66,3.32,1.64,2.11, RoomWindow.YES);
		Room r4 = new Room(3.79,2.49,3.13,1.07,1.67, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1, 1, 2, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(62, new double[] {11,13})));
		new IncandescentLighting(87, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {5,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(49, new double[] {10})));
		new Dishwasher(state, 275, new CyclicUsageParameter(2, new FixedTimeOfUse(68, new double[] {5,12})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {5,4})), getRates());
		new Clothwasher(state, 202, new CyclicUsageParameter(2, new FixedTimeOfUse(56, new double[] {6,16})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 47745, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
