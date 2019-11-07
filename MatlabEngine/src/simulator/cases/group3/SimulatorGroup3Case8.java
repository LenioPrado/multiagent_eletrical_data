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
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup3Case8 extends BaseSimulationCase {
		
	public SimulatorGroup3Case8(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(36, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {4,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,13}, new double[] {2.35,0.71}), StoveBurnerSize.EIGHT, new double[] {30,51,29,96});
		new Room(2.54,4.81,2.76,1.94,1.07, RoomWindow.YES);
		new Room(3.43,4.94,2.84,1.24,1.49, RoomWindow.YES);
		new Room(2.6,3.8,2.31,2.49,2.49, RoomWindow.YES);
		new Room(2.19,2.95,2.43,2.17,1.58, RoomWindow.YES);
		new Refrigerator(state, 220, 1.3, 1.1, 1.3, getRates());
		new IncandescentLighting(79, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {8,19}, new double[] {5,4})), getRates());
		new CompactFluorescentLighting(29, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {5,3})), getRates());
		new Clothwasher(state, 234, new CyclicUsageParameter(1, new FixedTimeOfUse(51, new double[] {11})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffDryer();
		createOffDishwasher();
		createOffBattery();
		createOffAirConditioner();
	}
}
