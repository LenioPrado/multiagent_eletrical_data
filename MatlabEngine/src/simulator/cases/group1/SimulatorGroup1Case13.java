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

public class SimulatorGroup1Case13 extends BaseSimulationCase {
		
	public SimulatorGroup1Case13(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 58000, 56, 111, 0.37f), getRates());
		new TubeFluorescentLighting(22, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {4,1})), getRates());
		Thermostat thermostat = new Thermostat(25.5,  new double[] {8,12,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,16,22}, new double[] {2.2,1.86,1.75}), StoveBurnerSize.EIGHT, new double[] {97,65,48,54});
		Room r1 = new Room(5.48,4.3,2.04,1.15,1.36, RoomWindow.YES);
		Room r2 = new Room(5.1,2.88,2.01,1.46,2.25, RoomWindow.YES);
		Room r3 = new Room(5.37,2.97,2.16,2.3,2.49, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.1, 1, 1.4, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(40, new double[] {6,15,19})));
		new IncandescentLighting(88, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,23}, new double[] {2,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(57, new double[] {8,17,20})));
		new Dishwasher(state, 254, new CyclicUsageParameter(1, new FixedTimeOfUse(37, new double[] {8})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(30, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,23}, new double[] {5,2})), getRates());
		new Clothwasher(state, 251, new CyclicUsageParameter(1, new FixedTimeOfUse(65, new double[] {6})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 34231, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
