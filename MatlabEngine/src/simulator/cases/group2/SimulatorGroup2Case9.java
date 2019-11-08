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
		new TubeFluorescentLighting(29, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {5,3})), getRates());
		Thermostat thermostat = new Thermostat(26.6,  new double[] {6,12,13,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,13,20}, new double[] {0.88,0.71,1}), StoveBurnerSize.SEVEN, new double[] {73,80,76,68});
		Room r1 = new Room(3.12,3.84,2.36,2.34,1.31, RoomWindow.YES);
		Room r2 = new Room(4.91,2.28,3.24,1.67,1.04, RoomWindow.YES);
		Room r3 = new Room(4.11,4.53,2.23,1.49,1.6, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.2, 1.2, 1.8, getRates());
		new IncandescentLighting(77, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {3,4})), getRates());
		new Dishwasher(state, 172, new CyclicUsageParameter(2, new FixedTimeOfUse(44, new double[] {5,12})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(27, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {2,4})), getRates());
		new Clothwasher(state, 221, new CyclicUsageParameter(2, new FixedTimeOfUse(72, new double[] {7,12})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 30821, getRates());
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
