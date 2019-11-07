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

public class SimulatorGroup1Case10 extends BaseSimulationCase {
		
	public SimulatorGroup1Case10(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 43000, 60, 139, 0.27f), getRates());
		new TubeFluorescentLighting(29, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {3,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,12}, new double[] {2.96,2.25}), StoveBurnerSize.SEVEN, new double[] {13,42,53,10});
		Room r1 = new Room(4.65,4.69,2.54,2.16,1.42, RoomWindow.YES);
		Room r2 = new Room(4.4,2.63,3.07,1.7,1.92, RoomWindow.YES);
		Room r3 = new Room(2.64,2.12,2.87,1.67,2.24, RoomWindow.YES);
		Room r4 = new Room(4.46,2.71,2.61,1.67,1.37, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1, 1.2, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(31, new double[] {6})));
		new IncandescentLighting(65, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {3,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(53, new double[] {5,14})));
		new Dishwasher(state, 198, new CyclicUsageParameter(2, new FixedTimeOfUse(59, new double[] {5,16})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(21, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,22}, new double[] {1,2})), getRates());
		new Clothwasher(state, 261, new CyclicUsageParameter(1, new FixedTimeOfUse(60, new double[] {9})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
