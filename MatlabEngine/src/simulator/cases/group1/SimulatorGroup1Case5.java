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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 36000, 62, 109, 0.63f), getRates());
		new TubeFluorescentLighting(22, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,21}, new double[] {4,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,12}, new double[] {1.96,2.13}), StoveBurnerSize.SIX, new double[] {67,65,35,66});
		Room r1 = new Room(4.12,2.83,3.48,1.56,1.89, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.2, 1.2, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(39, new double[] {7,17})));
		new IncandescentLighting(69, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {1,19}, new double[] {1,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(56, new double[] {6,15,20})));
		new Dishwasher(state, 250, new CyclicUsageParameter(3, new FixedTimeOfUse(46, new double[] {10,14,18})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(22, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {4,2})), getRates());
		new Clothwasher(state, 167, new CyclicUsageParameter(2, new FixedTimeOfUse(79, new double[] {9,13})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
