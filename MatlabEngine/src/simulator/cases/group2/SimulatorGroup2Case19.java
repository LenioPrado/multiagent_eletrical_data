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
import home.Thermostat;
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
		new TubeFluorescentLighting(37, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {1,2})), getRates());
		Thermostat thermostat = new Thermostat(23.9,  new double[] {6,12,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,13,19}, new double[] {2.61,0.58,2.1}), StoveBurnerSize.SIX, new double[] {35,66,26,44});
		Room r1 = new Room(2.35,5.11,2.78,1.17,2.11, RoomWindow.YES);
		Room r2 = new Room(2.59,3.18,2.99,2.47,2.07, RoomWindow.YES);
		Room r3 = new Room(2.02,2.93,3.01,1.08,1.74, RoomWindow.YES);
		Room r4 = new Room(4.87,5.06,2.14,2.03,1.56, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.1, 1.2, 1.3, getRates());
		new IncandescentLighting(76, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {1,19}, new double[] {1,3})), getRates());
		new Dishwasher(state, 180, new CyclicUsageParameter(2, new FixedTimeOfUse(84, new double[] {9,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(37, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {4,2})), getRates());
		new Clothwasher(state, 273, new CyclicUsageParameter(2, new FixedTimeOfUse(67, new double[] {7,15})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 40169, getRates());
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
