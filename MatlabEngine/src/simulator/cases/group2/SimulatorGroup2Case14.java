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

public class SimulatorGroup2Case14 extends BaseSimulationCase {
		
	public SimulatorGroup2Case14(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(33, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,18}, new double[] {4,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,16,22}, new double[] {1.38,1.86,2.15}), StoveBurnerSize.SIX, new double[] {57,1,27,12});
		Room r1 = new Room(3.13,4.85,2.16,1.51,1.65, RoomWindow.YES);
		Room r2 = new Room(3.04,2.34,3.12,1.07,1.65, RoomWindow.YES);
		Room r3 = new Room(4.47,4.08,2.81,1.14,2.37, RoomWindow.YES);
		Room r4 = new Room(3.38,3.57,2.32,1.76,1.16, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.1, 1, 1.5, getRates());
		new IncandescentLighting(80, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {5,3})), getRates());
		new Dishwasher(state, 206, new CyclicUsageParameter(1, new FixedTimeOfUse(82, new double[] {6})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(24, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,21}, new double[] {4,3})), getRates());
		new Clothwasher(state, 174, new CyclicUsageParameter(1, new FixedTimeOfUse(84, new double[] {5})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
