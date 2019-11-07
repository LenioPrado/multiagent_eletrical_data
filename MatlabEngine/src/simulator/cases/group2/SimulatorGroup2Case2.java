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

public class SimulatorGroup2Case2 extends BaseSimulationCase {
		
	public SimulatorGroup2Case2(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(21, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {2,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,12}, new double[] {0.53,2.18}), StoveBurnerSize.NINE, new double[] {79,18,31,42});
		Room r1 = new Room(2.64,3.8,2.39,2.49,1.52, RoomWindow.YES);
		Room r2 = new Room(2.77,3.98,3.41,2.06,2.01, RoomWindow.YES);
		Room r3 = new Room(4.63,3.17,3.36,1.09,2.02, RoomWindow.YES);
		Room r4 = new Room(2.64,4.67,3.32,1.24,1.5, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1.3, 1.3, 1, getRates());
		new IncandescentLighting(99, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,20}, new double[] {2,1})), getRates());
		new Dishwasher(state, 151, new CyclicUsageParameter(3, new FixedTimeOfUse(86, new double[] {5,14,22})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(33, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {5,1})), getRates());
		new Clothwasher(state, 170, new CyclicUsageParameter(3, new FixedTimeOfUse(33, new double[] {6,15,18})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
