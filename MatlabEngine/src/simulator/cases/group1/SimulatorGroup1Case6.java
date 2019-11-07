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

public class SimulatorGroup1Case6 extends BaseSimulationCase {
		
	public SimulatorGroup1Case6(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 33000, 56, 85, 0.85f), getRates());
		new TubeFluorescentLighting(31, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,20}, new double[] {2,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,14,18}, new double[] {2.96,2,2.46}), StoveBurnerSize.NINE, new double[] {0,61,57,77});
		Room r1 = new Room(5.32,2.75,2.13,1.88,1.41, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.3, 1, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(70, new double[] {6,12,18})));
		new IncandescentLighting(81, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {1,19}, new double[] {1,2})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(48, new double[] {9,17,18})));
		new Dishwasher(state, 224, new CyclicUsageParameter(2, new FixedTimeOfUse(68, new double[] {11,16})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(25, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {1,3})), getRates());
		new Clothwasher(state, 152, new CyclicUsageParameter(3, new FixedTimeOfUse(48, new double[] {8,13,20})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
