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
		new TubeFluorescentLighting(21, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {4,4})), getRates());
		Thermostat thermostat = new Thermostat(17.8,  new double[] {8,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,13,20}, new double[] {2.1,1.3,1}), StoveBurnerSize.NINE, new double[] {59,73,17,14});
		Room r1 = new Room(3.34,5.46,3,1.44,1.85, RoomWindow.YES);
		Room r2 = new Room(2.15,4.15,2.09,1.67,1.28, RoomWindow.YES);
		Room r3 = new Room(2.28,2.15,3.33,1.84,1.43, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.2, 1.2, 1.5, getRates());
		new IncandescentLighting(90, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {6,23}, new double[] {3,5})), getRates());
		new Dishwasher(state, 213, new CyclicUsageParameter(2, new FixedTimeOfUse(45, new double[] {5,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(32, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {9,19}, new double[] {4,1})), getRates());
		new Clothwasher(state, 172, new CyclicUsageParameter(1, new FixedTimeOfUse(30, new double[] {8})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 41478, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffDryer();
		createOffBattery();
	}
}
