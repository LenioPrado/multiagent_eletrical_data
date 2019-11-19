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

public class SimulatorGroup1Case10 extends BaseSimulationCase {
		
	public SimulatorGroup1Case10(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 33000, 58, 137, 0.98f), getRates());
		new TubeFluorescentLighting(27, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {2,3})), getRates());
		Thermostat thermostat = new Thermostat(19.6,  new double[] {6,10,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,13,20}, new double[] {1.53,2.25,2.81}), StoveBurnerSize.NINE, new double[] {50,58,87,52});
		Room r1 = new Room(2.83,4.5,3.5,1.48,2.47, RoomWindow.YES);
		Room r2 = new Room(4.04,4.66,2.54,1.78,2.29, RoomWindow.YES);
		Room r3 = new Room(4.87,2.42,2.93,1.9,1.7, RoomWindow.YES);
		Room r4 = new Room(2.63,4.6,2.37,1.96,1.83, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.3, 1.3, 1.5, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(43, new double[] {9,17})));
		new IncandescentLighting(99, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {3,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(50, new double[] {10,13})));
		new Dishwasher(state, 180, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {9,17})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(21, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {1,4})), getRates());
		new Clothwasher(state, 171, new CyclicUsageParameter(2, new FixedTimeOfUse(64, new double[] {7,15})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 36627, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
