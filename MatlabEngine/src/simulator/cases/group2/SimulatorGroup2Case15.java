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

public class SimulatorGroup2Case15 extends BaseSimulationCase {
		
	public SimulatorGroup2Case15(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(22, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {4,19}, new double[] {1,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,16,18}, new double[] {2.46,1.3,1.43}), StoveBurnerSize.SIX, new double[] {82,79,70,88});
		Room r1 = new Room(4.2,5.47,2.21,2.21,2.16, RoomWindow.YES);
		Room r2 = new Room(5.05,4.32,3.1,1.23,1.11, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 127, 1, 1.1, 1.4, getRates());
		new IncandescentLighting(95, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {3,3})), getRates());
		new Dishwasher(state, 207, new CyclicUsageParameter(2, new FixedTimeOfUse(73, new double[] {7,14})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(40, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {5,5})), getRates());
		new Clothwasher(state, 183, new CyclicUsageParameter(3, new FixedTimeOfUse(51, new double[] {6,15,20})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
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
