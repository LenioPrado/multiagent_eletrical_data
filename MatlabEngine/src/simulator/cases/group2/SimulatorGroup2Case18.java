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

public class SimulatorGroup2Case18 extends BaseSimulationCase {
		
	public SimulatorGroup2Case18(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(26, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {2,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,13,22}, new double[] {1.18,1.06,1.63}), StoveBurnerSize.SEVEN, new double[] {36,46,77,74});
		Room r1 = new Room(2.56,2.01,2.44,2.42,1.24, RoomWindow.YES);
		Room r2 = new Room(3.73,5.01,2.63,1.42,1.25, RoomWindow.YES);
		Room r3 = new Room(4.95,5.25,2.12,2.5,1.51, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.2, 1.1, 1.2, getRates());
		new IncandescentLighting(76, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {4,4})), getRates());
		new Dishwasher(state, 170, new CyclicUsageParameter(3, new FixedTimeOfUse(87, new double[] {5,17,18})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(31, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {5,5})), getRates());
		new Clothwasher(state, 265, new CyclicUsageParameter(2, new FixedTimeOfUse(51, new double[] {7,16})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
