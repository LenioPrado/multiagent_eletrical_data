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
		new TubeFluorescentLighting(23, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {5,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {2.28}), StoveBurnerSize.SIX, new double[] {80,48,19,15});
		new Room(4.01,4.78,2.41,2.18,1.22, RoomWindow.YES);
		new Room(4.8,4.19,2.93,1.79,2.04, RoomWindow.YES);
		new Room(5.35,4.85,2.45,1.03,1.9, RoomWindow.YES);
		new Refrigerator(state, 220, 1, 1, 1.2, getRates());
		new IncandescentLighting(81, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {3,5})), getRates());
		new CompactFluorescentLighting(29, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {1,4})), getRates());
		new Clothwasher(state, 270, new CyclicUsageParameter(3, new FixedTimeOfUse(89, new double[] {9,13,22})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
