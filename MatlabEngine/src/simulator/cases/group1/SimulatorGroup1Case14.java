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

public class SimulatorGroup1Case14 extends BaseSimulationCase {
		
	public SimulatorGroup1Case14(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 50000, 40, 119, 0.5f), getRates());
		new TubeFluorescentLighting(23, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {1,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5}, new double[] {1.5}), StoveBurnerSize.NINE, new double[] {44,55,86,69});
		Room r1 = new Room(3.99,3.38,2.97,1.81,2.26, RoomWindow.YES);
		Room r2 = new Room(5.48,3.17,2.33,2.25,1.68, RoomWindow.YES);
		Room r3 = new Room(3.11,3.13,2.42,1.22,1.36, RoomWindow.YES);
		Room r4 = new Room(3.79,3.93,3.24,2.06,1.05, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.3, 1.3, 1.1, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(70, new double[] {11,16,18})));
		new IncandescentLighting(77, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {3,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(57, new double[] {7,16,21})));
		new Dishwasher(state, 273, new CyclicUsageParameter(1, new FixedTimeOfUse(68, new double[] {7})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {4,2})), getRates());
		new Clothwasher(state, 240, new CyclicUsageParameter(2, new FixedTimeOfUse(88, new double[] {7,14})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
