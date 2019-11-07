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

public class SimulatorGroup1Case2 extends BaseSimulationCase {
		
	public SimulatorGroup1Case2(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 51000, 60, 137, 0.68f), getRates());
		new TubeFluorescentLighting(32, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {3,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {1}), StoveBurnerSize.SEVEN, new double[] {94,79,48,52});
		Room r1 = new Room(3.47,2.75,3.31,1.46,2.16, RoomWindow.YES);
		Room r2 = new Room(4.45,4.91,3.28,2.23,1.72, RoomWindow.YES);
		Room r3 = new Room(2.19,4.1,2.48,1.12,1.07, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.3, 1.1, 1.8, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(60, new double[] {6,13})));
		new IncandescentLighting(72, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,19}, new double[] {3,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(56, new double[] {9})));
		new Dishwasher(state, 180, new CyclicUsageParameter(1, new FixedTimeOfUse(82, new double[] {9})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(24, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,21}, new double[] {1,4})), getRates());
		new Clothwasher(state, 235, new CyclicUsageParameter(2, new FixedTimeOfUse(40, new double[] {6,13})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
