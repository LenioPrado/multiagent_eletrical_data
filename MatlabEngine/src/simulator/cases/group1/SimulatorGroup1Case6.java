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

public class SimulatorGroup1Case6 extends BaseSimulationCase {
		
	public SimulatorGroup1Case6(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 56000, 65, 121, 0.44f), getRates());
		new TubeFluorescentLighting(20, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {4,4})), getRates());
		Thermostat thermostat = new Thermostat(23.1,  new double[] {9,12,15,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,17,21}, new double[] {2.51,1.16,0.6}), StoveBurnerSize.SEVEN, new double[] {71,77,24,40});
		Room r1 = new Room(4.08,3.33,2.56,2.29,1.08, RoomWindow.YES);
		Room r2 = new Room(4.93,2.57,2.52,1.27,1.31, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 127, 1.3, 1, 1.9, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(87, new double[] {5})));
		new IncandescentLighting(96, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,21}, new double[] {4,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(90, new double[] {11,12,22})));
		new Dishwasher(state, 194, new CyclicUsageParameter(1, new FixedTimeOfUse(69, new double[] {10})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(21, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {2,1})), getRates());
		new Clothwasher(state, 214, new CyclicUsageParameter(2, new FixedTimeOfUse(42, new double[] {9,17})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 46798, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffBattery();
	}
}
