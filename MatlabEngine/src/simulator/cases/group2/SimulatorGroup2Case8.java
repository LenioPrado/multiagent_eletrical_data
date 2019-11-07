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

public class SimulatorGroup2Case8 extends BaseSimulationCase {
		
	public SimulatorGroup2Case8(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(22, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {2,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9}, new double[] {1.46}), StoveBurnerSize.NINE, new double[] {16,54,21,5});
		Room r1 = new Room(5.04,2.92,2.78,1.88,2.31, RoomWindow.YES);
		Room r2 = new Room(5.24,3.94,2.11,1.39,1.05, RoomWindow.YES);
		Room r3 = new Room(5.42,3.24,2.6,1.38,1.85, RoomWindow.YES);
		Room r4 = new Room(3.39,3.62,3.45,2.01,2.19, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.2, 1.3, 2, getRates());
		new IncandescentLighting(87, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,21}, new double[] {5,3})), getRates());
		new Dishwasher(state, 171, new CyclicUsageParameter(3, new FixedTimeOfUse(64, new double[] {5,12,22})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(21, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,19}, new double[] {4,1})), getRates());
		new Clothwasher(state, 234, new CyclicUsageParameter(1, new FixedTimeOfUse(73, new double[] {9})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
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
