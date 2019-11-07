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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 36000, 62, 116, 0.52f), getRates());
		new TubeFluorescentLighting(38, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {1,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,17}, new double[] {2.65,3}), StoveBurnerSize.EIGHT, new double[] {99,34,52,89});
		Room r1 = new Room(2.43,5.2,2.11,1.23,2.18, RoomWindow.YES);
		Room r2 = new Room(2.11,3.73,3.2,1.11,2.16, RoomWindow.YES);
		Room r3 = new Room(5.34,3.41,2.88,1.79,1.64, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.3, 1.1, 1.9, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(53, new double[] {6})));
		new IncandescentLighting(61, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {4,21}, new double[] {3,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(76, new double[] {11,12,19})));
		new Dishwasher(state, 159, new CyclicUsageParameter(1, new FixedTimeOfUse(56, new double[] {7})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(24, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {4,3})), getRates());
		new Clothwasher(state, 213, new CyclicUsageParameter(2, new FixedTimeOfUse(48, new double[] {5,13})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
