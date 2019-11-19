package simulator.cases.group1;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.cyclicusage.Dryer;
import equipment.cyclicusage.PoolPump;
import equipment.hvac.AirConditioner;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
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

public class SimulatorGroup1Case11 extends BaseSimulationCase {
		
	public SimulatorGroup1Case11(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 56000, 50, 110, 0.84f), getRates());
		new TubeFluorescentLighting(35, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {2,1})), getRates());
		Thermostat thermostat = new Thermostat(18.6,  new double[] {6,12,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,13,22}, new double[] {1.85,1.08,1.06}), StoveBurnerSize.SEVEN, new double[] {80,26,9,42});
		Room r1 = new Room(2.96,4.3,2.24,1.45,1.17, RoomWindow.YES);
		Room r2 = new Room(4.85,3.67,2.22,2.02,1.98, RoomWindow.YES);
		Room r3 = new Room(4.1,4.69,3.17,2.49,1.55, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1, 1.2, 1.5, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(82, new double[] {5,14,20})));
		new IncandescentLighting(71, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {4,2})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(56, new double[] {9})));
		new Dishwasher(state, 235, new CyclicUsageParameter(1, new FixedTimeOfUse(75, new double[] {9})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(34, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {9,21}, new double[] {2,4})), getRates());
		new Clothwasher(state, 227, new CyclicUsageParameter(1, new FixedTimeOfUse(42, new double[] {9})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 42103, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
