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

public class SimulatorGroup1Case16 extends BaseSimulationCase {
		
	public SimulatorGroup1Case16(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 55000, 51, 117, 0.54f), getRates());
		new TubeFluorescentLighting(28, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,22}, new double[] {1,1})), getRates());
		Thermostat thermostat = new Thermostat(21.6,  new double[] {6,10,13,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,12,21}, new double[] {2.4,2.31,2.7}), StoveBurnerSize.SIX, new double[] {32,35,62,71});
		Room r1 = new Room(2.15,2.84,2.65,2.47,1.5, RoomWindow.YES);
		Room r2 = new Room(3.43,3.34,3.18,2,1.58, RoomWindow.YES);
		Room r3 = new Room(3.42,3.97,2.24,1.06,1.08, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.3, 1, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(63, new double[] {7})));
		new IncandescentLighting(91, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {1,21}, new double[] {5,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(65, new double[] {8})));
		new Dishwasher(state, 194, new CyclicUsageParameter(3, new FixedTimeOfUse(64, new double[] {6,15,18})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(23, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {3,4})), getRates());
		new Clothwasher(state, 267, new CyclicUsageParameter(1, new FixedTimeOfUse(35, new double[] {5})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 48827, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffBattery();
	}
}
