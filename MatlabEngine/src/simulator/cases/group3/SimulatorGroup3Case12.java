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

public class SimulatorGroup3Case12 extends BaseSimulationCase {
		
	public SimulatorGroup3Case12(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(30, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {2,18}, new double[] {5,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5}, new double[] {1.03}), StoveBurnerSize.SIX, new double[] {61,88,34,78});
		new Room(3.52,3.15,2.86,1.82,1.94, RoomWindow.YES);
		new Room(2.65,3.67,2.36,2.22,1.06, RoomWindow.YES);
		new Room(3.3,2.58,3.22,2.13,2.41, RoomWindow.YES);
		new Room(2.58,4.99,2.1,1.05,1.58, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1, 1.7, getRates());
		new IncandescentLighting(81, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {3,3})), getRates());
		new CompactFluorescentLighting(28, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,23}, new double[] {4,2})), getRates());
		new Clothwasher(state, 161, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {8,14})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
