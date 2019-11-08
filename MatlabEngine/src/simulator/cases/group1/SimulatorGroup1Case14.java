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

public class SimulatorGroup1Case14 extends BaseSimulationCase {
		
	public SimulatorGroup1Case14(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 49000, 64, 138, 0.1f), getRates());
		new TubeFluorescentLighting(35, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {5,5})), getRates());
		Thermostat thermostat = new Thermostat(16.6,  new double[] {9,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,20}, new double[] {1.68,2.66,2.63}), StoveBurnerSize.SEVEN, new double[] {88,26,64,69});
		Room r1 = new Room(2.46,3.53,2.5,1.64,1.28, RoomWindow.YES);
		Room r2 = new Room(4.36,3.28,3.02,2.29,1.94, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 127, 1.2, 1.3, 1.9, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(72, new double[] {8})));
		new IncandescentLighting(87, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {2,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(56, new double[] {10,12,22})));
		new Dishwasher(state, 206, new CyclicUsageParameter(2, new FixedTimeOfUse(86, new double[] {9,13})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(35, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {4,4})), getRates());
		new Clothwasher(state, 198, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {7,12})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 52689, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffBattery();
	}
}
