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

public class SimulatorGroup2Case10 extends BaseSimulationCase {
		
	public SimulatorGroup2Case10(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(23, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {0,23}, new double[] {5,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10}, new double[] {1.23}), StoveBurnerSize.EIGHT, new double[] {47,8,19,41});
		Room r1 = new Room(4.54,3.21,2.27,1.18,1.62, RoomWindow.YES);
		Room r2 = new Room(4.96,2.5,2.01,2.31,1.87, RoomWindow.YES);
		Room r3 = new Room(4.64,3.47,3.4,1.95,1.35, RoomWindow.YES);
		Room r4 = new Room(2.14,5.48,2.6,1.08,2.13, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.1, 1.3, 1.8, getRates());
		new IncandescentLighting(62, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {1,4})), getRates());
		new Dishwasher(state, 169, new CyclicUsageParameter(2, new FixedTimeOfUse(60, new double[] {6,15})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(33, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {2,1})), getRates());
		new Clothwasher(state, 275, new CyclicUsageParameter(2, new FixedTimeOfUse(38, new double[] {5,12})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
