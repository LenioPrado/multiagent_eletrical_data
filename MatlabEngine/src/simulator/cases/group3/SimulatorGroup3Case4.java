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

public class SimulatorGroup3Case4 extends BaseSimulationCase {
		
	public SimulatorGroup3Case4(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {4,1})), getRates());
		new Thermostat(20.6,  new double[] {9,10,14,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,16,18}, new double[] {1.03,1.33,1.9}), StoveBurnerSize.NINE, new double[] {26,96,99,11});
		new Room(3.77,2.93,2.45,1.08,1.29, RoomWindow.YES);
		new Room(4.73,4.36,3.11,1.91,2.21, RoomWindow.YES);
		new Refrigerator(state, 220, 1.1, 1.3, 1.2, getRates());
		new IncandescentLighting(69, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {3,2})), getRates());
		new CompactFluorescentLighting(20, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {4,3})), getRates());
		new Clothwasher(state, 213, new CyclicUsageParameter(1, new FixedTimeOfUse(46, new double[] {5})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
