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

public class SimulatorGroup2Case5 extends BaseSimulationCase {
		
	public SimulatorGroup2Case5(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(36, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {1,2})), getRates());
		Thermostat thermostat = new Thermostat(16.2,  new double[] {9,11,14,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,12,21}, new double[] {1.91,2.43,0.71}), StoveBurnerSize.EIGHT, new double[] {56,82,16,49});
		Room r1 = new Room(2.08,4.75,2.74,2.19,1.68, RoomWindow.YES);
		Room r2 = new Room(2.85,2.15,2.77,2.1,1.3, RoomWindow.YES);
		Room r3 = new Room(4.12,4.6,2.62,2.23,2.43, RoomWindow.YES);
		Room r4 = new Room(2.98,3.95,3.43,1.66,1, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.3, 1.3, 1.5, getRates());
		new IncandescentLighting(86, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {1,3})), getRates());
		new Dishwasher(state, 238, new CyclicUsageParameter(3, new FixedTimeOfUse(69, new double[] {5,17,21})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(40, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {5,1})), getRates());
		new Clothwasher(state, 175, new CyclicUsageParameter(2, new FixedTimeOfUse(76, new double[] {11,17})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 45003, getRates());
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
