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

public class SimulatorGroup3Case11 extends BaseSimulationCase {
		
	public SimulatorGroup3Case11(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(33, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {5,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9}, new double[] {1.38}), StoveBurnerSize.SEVEN, new double[] {80,89,90,24});
		new Room(3.46,3.04,2.55,2.33,2.09, RoomWindow.YES);
		new Room(3.36,2.45,3.44,1.28,2.27, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1, 1.3, getRates());
		new IncandescentLighting(69, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {1,21}, new double[] {3,3})), getRates());
		new CompactFluorescentLighting(29, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {2,1})), getRates());
		new Clothwasher(state, 258, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {10,14})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
