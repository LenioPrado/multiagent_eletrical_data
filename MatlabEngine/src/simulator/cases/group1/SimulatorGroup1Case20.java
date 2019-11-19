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

public class SimulatorGroup1Case20 extends BaseSimulationCase {
		
	public SimulatorGroup1Case20(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 33000, 48, 126, 0.1f), getRates());
		new TubeFluorescentLighting(28, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {5,5})), getRates());
		Thermostat thermostat = new Thermostat(17.7,  new double[] {6,10,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,13,19}, new double[] {2.16,2.71,2.05}), StoveBurnerSize.SIX, new double[] {11,40,54,57});
		Room r1 = new Room(2.88,4.65,2.23,1.29,2.47, RoomWindow.YES);
		Room r2 = new Room(2.23,2.05,3.18,1.98,1.19, RoomWindow.YES);
		Room r3 = new Room(3.37,5.11,2.61,1.12,1.48, RoomWindow.YES);
		Room r4 = new Room(5.04,4.27,2.24,1.59,1.23, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1, 1, 1.2, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(59, new double[] {8,12})));
		new IncandescentLighting(83, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {5,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(33, new double[] {7})));
		new Dishwasher(state, 213, new CyclicUsageParameter(2, new FixedTimeOfUse(76, new double[] {7,14})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(34, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {1,3})), getRates());
		new Clothwasher(state, 262, new CyclicUsageParameter(2, new FixedTimeOfUse(31, new double[] {9,13})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 32918, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
