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

public class SimulatorGroup2Case19 extends BaseSimulationCase {
		
	public SimulatorGroup2Case19(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,20}, new double[] {3,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,12,19}, new double[] {1,2.28,1.38}), StoveBurnerSize.EIGHT, new double[] {75,58,47,5});
		Room r1 = new Room(2.8,2.61,2.85,2.15,1.84, RoomWindow.YES);
		Room r2 = new Room(2.78,5.04,2.71,2.31,1.9, RoomWindow.YES);
		Room r3 = new Room(3.97,5.46,3.25,1.7,2.47, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.3, 1, 1.3, getRates());
		new IncandescentLighting(95, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {1,1})), getRates());
		new Dishwasher(state, 158, new CyclicUsageParameter(1, new FixedTimeOfUse(52, new double[] {10})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(39, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {1,2})), getRates());
		new Clothwasher(state, 152, new CyclicUsageParameter(1, new FixedTimeOfUse(62, new double[] {9})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
