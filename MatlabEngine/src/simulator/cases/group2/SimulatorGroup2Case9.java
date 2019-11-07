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

public class SimulatorGroup2Case9 extends BaseSimulationCase {
		
	public SimulatorGroup2Case9(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(22, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,22}, new double[] {2,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,16,20}, new double[] {1.23,0.9,1.35}), StoveBurnerSize.EIGHT, new double[] {56,53,23,74});
		Room r1 = new Room(3.58,3.78,3.38,2.31,1.67, RoomWindow.YES);
		Room r2 = new Room(4.53,4.77,3.15,2.27,1.58, RoomWindow.YES);
		Room r3 = new Room(4.79,2.34,2.08,1.85,1.22, RoomWindow.YES);
		Room r4 = new Room(4.02,2.77,2.01,1.75,2.24, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1.1, 1, 1.1, getRates());
		new IncandescentLighting(72, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {4,4})), getRates());
		new Dishwasher(state, 252, new CyclicUsageParameter(3, new FixedTimeOfUse(36, new double[] {5,15,18})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,22}, new double[] {3,4})), getRates());
		new Clothwasher(state, 153, new CyclicUsageParameter(2, new FixedTimeOfUse(81, new double[] {11,13})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
