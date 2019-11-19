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
		new TubeFluorescentLighting(32, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,21}, new double[] {1,3})), getRates());
		Thermostat thermostat = new Thermostat(25.4,  new double[] {9,11,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,16,18}, new double[] {1.35,2.85,1.86}), StoveBurnerSize.NINE, new double[] {12,37,81,96});
		Room r1 = new Room(3.66,3.5,3.48,2.4,1.56, RoomWindow.YES);
		Room r2 = new Room(2.51,3.05,3.22,1.56,1.85, RoomWindow.YES);
		Room r3 = new Room(5.08,2.42,3.41,1.1,1.27, RoomWindow.YES);
		Room r4 = new Room(2.11,2.35,3.04,1.56,2.19, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1.1, 1.3, 1.8, getRates());
		new IncandescentLighting(73, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {4,5})), getRates());
		new Dishwasher(state, 193, new CyclicUsageParameter(3, new FixedTimeOfUse(72, new double[] {9,14,22})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {1,2})), getRates());
		new Clothwasher(state, 179, new CyclicUsageParameter(3, new FixedTimeOfUse(82, new double[] {5,15,22})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 44791, getRates());
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
