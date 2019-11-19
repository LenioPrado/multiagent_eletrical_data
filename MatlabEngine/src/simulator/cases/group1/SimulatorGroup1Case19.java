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

public class SimulatorGroup1Case19 extends BaseSimulationCase {
		
	public SimulatorGroup1Case19(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 36000, 45, 82, 0.02f), getRates());
		new TubeFluorescentLighting(30, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {3,5})), getRates());
		Thermostat thermostat = new Thermostat(18.8,  new double[] {8,10,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,13,19}, new double[] {1.93,1.18,0.85}), StoveBurnerSize.SEVEN, new double[] {91,26,92,39});
		Room r1 = new Room(3.68,2.92,3,1.21,1.27, RoomWindow.YES);
		Room r2 = new Room(3.41,3.44,3.05,1.51,2.17, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 220, 1, 1.3, 1.1, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(34, new double[] {6,12})));
		new IncandescentLighting(97, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {9,20}, new double[] {5,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(59, new double[] {7})));
		new Dishwasher(state, 213, new CyclicUsageParameter(1, new FixedTimeOfUse(83, new double[] {9})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(32, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,22}, new double[] {5,2})), getRates());
		new Clothwasher(state, 206, new CyclicUsageParameter(1, new FixedTimeOfUse(40, new double[] {9})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 39301, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
