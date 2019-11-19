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

public class SimulatorGroup1Case1 extends BaseSimulationCase {
		
	public SimulatorGroup1Case1(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 48000, 63, 113, 0.28f), getRates());
		new TubeFluorescentLighting(39, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,21}, new double[] {5,3})), getRates());
		Thermostat thermostat = new Thermostat(17.9,  new double[] {8,10,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,14,20}, new double[] {2.08,2.65,1.43}), StoveBurnerSize.NINE, new double[] {45,97,18,33});
		Room r1 = new Room(3.39,3.43,2.72,2.47,1.98, RoomWindow.YES);
		Room r2 = new Room(3.62,3.26,2.42,2.45,1.38, RoomWindow.YES);
		Room r3 = new Room(4.85,2.32,2.65,1.9,1.38, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.2, 1.3, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(58, new double[] {8,12,18})));
		new IncandescentLighting(70, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,23}, new double[] {2,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(52, new double[] {11,17})));
		new Dishwasher(state, 159, new CyclicUsageParameter(2, new FixedTimeOfUse(89, new double[] {10,15})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(35, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {5,2})), getRates());
		new Clothwasher(state, 162, new CyclicUsageParameter(3, new FixedTimeOfUse(72, new double[] {7,15,22})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 31427, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
