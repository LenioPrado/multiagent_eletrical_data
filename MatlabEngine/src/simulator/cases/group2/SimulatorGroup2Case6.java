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

public class SimulatorGroup2Case6 extends BaseSimulationCase {
		
	public SimulatorGroup2Case6(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(40, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {4,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,12,19}, new double[] {0.93,2.08,2.15}), StoveBurnerSize.NINE, new double[] {3,12,68,43});
		Room r1 = new Room(4.2,3.31,2.1,2.01,1.61, RoomWindow.YES);
		Room r2 = new Room(4.84,3.93,2.7,1.29,1.61, RoomWindow.YES);
		Room r3 = new Room(4.44,4.62,2.46,1.79,1.77, RoomWindow.YES);
		Room r4 = new Room(5.45,2.7,2.67,2.43,1.57, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.1, 1, 1.4, getRates());
		new IncandescentLighting(92, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {5,4})), getRates());
		new Dishwasher(state, 212, new CyclicUsageParameter(2, new FixedTimeOfUse(53, new double[] {11,12})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {4,4})), getRates());
		new Clothwasher(state, 186, new CyclicUsageParameter(3, new FixedTimeOfUse(37, new double[] {5,17,18})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
