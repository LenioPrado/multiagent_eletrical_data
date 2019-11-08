package simulator.cases.group3;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
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

public class SimulatorGroup3Case6 extends BaseSimulationCase {
		
	public SimulatorGroup3Case6(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(29, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,22}, new double[] {4,5})), getRates());
		new Thermostat(19.9,  new double[] {8,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,16,20}, new double[] {2.4,1.76,0.91}), StoveBurnerSize.SEVEN, new double[] {74,39,10,38});
		new Room(3.17,4.88,3.48,2.22,1.47, RoomWindow.YES);
		new Room(3.22,4.07,2.18,1.58,1.34, RoomWindow.YES);
		new Room(3.56,5.14,2.08,1.13,2.43, RoomWindow.YES);
		new Room(3.6,5.46,2.38,1.89,1, RoomWindow.YES);
		new Refrigerator(state, 220, 1, 1.1, 1.6, getRates());
		new IncandescentLighting(86, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {4,1})), getRates());
		new CompactFluorescentLighting(28, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {5,2})), getRates());
		new Clothwasher(state, 213, new CyclicUsageParameter(1, new FixedTimeOfUse(70, new double[] {11})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffDryer();
		createOffDishwasher();
		createOffBattery();
		createOffAirConditioner();
	}
}
