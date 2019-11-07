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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 30000, 43, 128, 0.14f), getRates());
		new TubeFluorescentLighting(21, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {8,18}, new double[] {3,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,17}, new double[] {1.8,1.8}), StoveBurnerSize.NINE, new double[] {4,24,52,68});
		Room r1 = new Room(4,2.47,2.79,2.34,2.48, RoomWindow.YES);
		Room r2 = new Room(3.45,4.4,2.85,1.9,2.16, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 127, 1, 1.3, 1.9, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(59, new double[] {11,14,19})));
		new IncandescentLighting(100, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {4,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(64, new double[] {6,14,21})));
		new Dishwasher(state, 205, new CyclicUsageParameter(1, new FixedTimeOfUse(65, new double[] {5})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(27, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {8,19}, new double[] {4,2})), getRates());
		new Clothwasher(state, 229, new CyclicUsageParameter(1, new FixedTimeOfUse(30, new double[] {11})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
