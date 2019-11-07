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

public class SimulatorGroup1Case7 extends BaseSimulationCase {
		
	public SimulatorGroup1Case7(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 53000, 50, 120, 0.59f), getRates());
		new TubeFluorescentLighting(20, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {4,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,15}, new double[] {1.65,2.91}), StoveBurnerSize.SEVEN, new double[] {73,23,9,67});
		Room r1 = new Room(2.71,5.25,2.12,2.4,2.1, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.2, 1.3, 1.3, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(77, new double[] {6,14,22})));
		new IncandescentLighting(66, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {3,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {5,17})));
		new Dishwasher(state, 215, new CyclicUsageParameter(1, new FixedTimeOfUse(86, new double[] {7})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(22, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {1,3})), getRates());
		new Clothwasher(state, 229, new CyclicUsageParameter(3, new FixedTimeOfUse(50, new double[] {6,12,20})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
