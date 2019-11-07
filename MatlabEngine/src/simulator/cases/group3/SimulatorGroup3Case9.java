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

public class SimulatorGroup3Case9 extends BaseSimulationCase {
		
	public SimulatorGroup3Case9(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(21, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {5,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,13,18}, new double[] {2.75,0.53,2.28}), StoveBurnerSize.NINE, new double[] {13,66,3,23});
		new Room(4.8,5.08,2.55,2.23,2.09, RoomWindow.YES);
		new Room(4.46,2.82,2.61,2.01,1.47, RoomWindow.YES);
		new Room(3.36,4.98,2.27,1.1,1.32, RoomWindow.YES);
		new Refrigerator(state, 127, 1.3, 1, 1.6, getRates());
		new IncandescentLighting(100, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {9,21}, new double[] {1,4})), getRates());
		new CompactFluorescentLighting(23, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {4,4})), getRates());
		new Clothwasher(state, 184, new CyclicUsageParameter(3, new FixedTimeOfUse(78, new double[] {7,13,18})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
