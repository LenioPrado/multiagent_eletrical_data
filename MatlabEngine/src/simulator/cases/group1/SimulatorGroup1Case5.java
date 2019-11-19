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

public class SimulatorGroup1Case5 extends BaseSimulationCase {
		
	public SimulatorGroup1Case5(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 48000, 37, 118, 0.21f), getRates());
		new TubeFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,20}, new double[] {5,2})), getRates());
		Thermostat thermostat = new Thermostat(26.2,  new double[] {9,10,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,12,21}, new double[] {2.83,1.68,1.3}), StoveBurnerSize.EIGHT, new double[] {24,59,55,89});
		Room r1 = new Room(4.98,4.42,3.24,1.55,1.42, RoomWindow.YES);
		Room r2 = new Room(4.49,4.54,2.06,1.03,2.26, RoomWindow.YES);
		Room r3 = new Room(4.17,2.18,2.76,2.02,2.18, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.3, 1.1, 1.1, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(89, new double[] {7,14,20})));
		new IncandescentLighting(71, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,21}, new double[] {4,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(50, new double[] {5,17,21})));
		new Dishwasher(state, 252, new CyclicUsageParameter(1, new FixedTimeOfUse(78, new double[] {7})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(22, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {1,2})), getRates());
		new Clothwasher(state, 196, new CyclicUsageParameter(2, new FixedTimeOfUse(52, new double[] {7,12})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 47275, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
