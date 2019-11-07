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

public class SimulatorGroup1Case19 extends BaseSimulationCase {
		
	public SimulatorGroup1Case19(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 44000, 55, 90, 0.64f), getRates());
		new TubeFluorescentLighting(40, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {9,19}, new double[] {1,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {1.01}), StoveBurnerSize.NINE, new double[] {21,25,48,65});
		Room r1 = new Room(5.44,5.46,3.45,1.46,1.56, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.2, 1.1, 2, getRates());
		new PoolPump(state, new CyclicUsageParameter(1, new FixedTimeOfUse(59, new double[] {10})));
		new IncandescentLighting(83, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,23}, new double[] {5,5})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(31, new double[] {10,16})));
		new Dishwasher(state, 202, new CyclicUsageParameter(1, new FixedTimeOfUse(30, new double[] {8})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(31, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {5,1})), getRates());
		new Clothwasher(state, 161, new CyclicUsageParameter(2, new FixedTimeOfUse(34, new double[] {9,16})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
