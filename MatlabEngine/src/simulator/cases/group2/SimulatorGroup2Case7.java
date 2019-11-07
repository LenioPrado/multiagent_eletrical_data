package simulator.cases.group2;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.hvac.AirConditioner;
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

public class SimulatorGroup2Case7 extends BaseSimulationCase {
		
	public SimulatorGroup2Case7(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {4,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11}, new double[] {2.35}), StoveBurnerSize.SEVEN, new double[] {49,87,28,92});
		Room r1 = new Room(3.66,2.68,2.62,2.25,1.35, RoomWindow.YES);
		Room r2 = new Room(4.2,3.39,3.04,1.02,2.21, RoomWindow.YES);
		Room r3 = new Room(2.16,2.24,2.5,1.56,2.35, RoomWindow.YES);
		Room r4 = new Room(4.2,3.38,2.24,2.34,1.19, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.2, 1.1, 1.4, getRates());
		new IncandescentLighting(85, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {5,5})), getRates());
		new Dishwasher(state, 213, new CyclicUsageParameter(3, new FixedTimeOfUse(69, new double[] {9,13,19})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(39, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {1,4})), getRates());
		new Clothwasher(state, 266, new CyclicUsageParameter(2, new FixedTimeOfUse(86, new double[] {5,14})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffDryer();
		createOffBattery();
	}
}
