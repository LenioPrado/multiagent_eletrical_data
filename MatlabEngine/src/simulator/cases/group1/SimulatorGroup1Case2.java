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

public class SimulatorGroup1Case2 extends BaseSimulationCase {
		
	public SimulatorGroup1Case2(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 59000, 56, 116, 0.27f), getRates());
		new TubeFluorescentLighting(28, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {3,1})), getRates());
		Thermostat thermostat = new Thermostat(19.3,  new double[] {9,11,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,16,20}, new double[] {0.71,2.96,1.1}), StoveBurnerSize.EIGHT, new double[] {93,43,71,3});
		Room r1 = new Room(3.86,2.38,3.36,2.02,1.65, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.2, 1.2, 2, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(84, new double[] {10})));
		new IncandescentLighting(93, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {2,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(45, new double[] {11})));
		new Dishwasher(state, 249, new CyclicUsageParameter(1, new FixedTimeOfUse(37, new double[] {10})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(35, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {2,2})), getRates());
		new Clothwasher(state, 169, new CyclicUsageParameter(1, new FixedTimeOfUse(76, new double[] {7})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 44889, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
