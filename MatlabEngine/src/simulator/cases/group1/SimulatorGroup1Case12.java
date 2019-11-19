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

public class SimulatorGroup1Case12 extends BaseSimulationCase {
		
	public SimulatorGroup1Case12(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 49000, 43, 95, 0.58f), getRates());
		new TubeFluorescentLighting(40, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {9,23}, new double[] {2,4})), getRates());
		Thermostat thermostat = new Thermostat(25.1,  new double[] {6,11,13,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,15,18}, new double[] {2.55,2.46,2.76}), StoveBurnerSize.SIX, new double[] {57,4,15,11});
		Room r1 = new Room(5.03,4.48,3.29,1.11,2.4, RoomWindow.YES);
		Room r2 = new Room(4.73,3.26,3.1,1.77,1.8, RoomWindow.YES);
		Room r3 = new Room(4.17,2.11,3,2.46,1.68, RoomWindow.YES);
		Room r4 = new Room(4.77,4.55,2.91,1.02,1.77, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1, 1.1, 1.2, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(86, new double[] {9})));
		new IncandescentLighting(67, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {9,23}, new double[] {2,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(51, new double[] {7,15,19})));
		new Dishwasher(state, 201, new CyclicUsageParameter(2, new FixedTimeOfUse(60, new double[] {7,15})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {4,2})), getRates());
		new Clothwasher(state, 261, new CyclicUsageParameter(3, new FixedTimeOfUse(49, new double[] {8,16,19})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 45809, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
