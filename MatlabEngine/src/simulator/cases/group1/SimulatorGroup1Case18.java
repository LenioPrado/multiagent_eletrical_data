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

public class SimulatorGroup1Case18 extends BaseSimulationCase {
		
	public SimulatorGroup1Case18(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 39000, 36, 136, 0.95f), getRates());
		new TubeFluorescentLighting(24, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {4,5})), getRates());
		Thermostat thermostat = new Thermostat(19.1,  new double[] {9,11,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,16,19}, new double[] {1.93,1.7,2.63}), StoveBurnerSize.SEVEN, new double[] {94,14,12,85});
		Room r1 = new Room(2.47,4.84,3.07,2.32,1.41, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1, 1.2, 1.7, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(77, new double[] {8})));
		new IncandescentLighting(100, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {1,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(58, new double[] {9,17,21})));
		new Dishwasher(state, 160, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {8,17})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(31, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {4,3})), getRates());
		new Clothwasher(state, 190, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {11,12})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 34532, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffBattery();
	}
}
