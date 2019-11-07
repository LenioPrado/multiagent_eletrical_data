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

public class SimulatorGroup1Case20 extends BaseSimulationCase {
		
	public SimulatorGroup1Case20(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 39000, 36, 81, 0.96f), getRates());
		new TubeFluorescentLighting(27, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {1,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,14,20}, new double[] {0.65,1.35,2.7}), StoveBurnerSize.NINE, new double[] {73,73,20,94});
		Room r1 = new Room(4.12,2.37,2.63,2.04,2.22, RoomWindow.YES);
		Room r2 = new Room(5.47,2.16,2.26,1.57,1.84, RoomWindow.YES);
		Room r3 = new Room(3.45,3.4,2.8,1.49,2.16, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.2, 1, 2, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(50, new double[] {9,16,20})));
		new IncandescentLighting(73, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {5,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(52, new double[] {11,12})));
		new Dishwasher(state, 167, new CyclicUsageParameter(2, new FixedTimeOfUse(55, new double[] {9,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {2,1})), getRates());
		new Clothwasher(state, 236, new CyclicUsageParameter(2, new FixedTimeOfUse(42, new double[] {11,13})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
