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

public class SimulatorGroup3Case19 extends BaseSimulationCase {
		
	public SimulatorGroup3Case19(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(27, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {5,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,12,18}, new double[] {1.18,1.71,1.35}), StoveBurnerSize.NINE, new double[] {63,98,20,16});
		new Room(3.94,3.48,2.98,1.25,1.36, RoomWindow.YES);
		new Room(4.7,3.81,2.82,1.19,1.38, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1.1, 1.5, getRates());
		new IncandescentLighting(89, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,23}, new double[] {4,2})), getRates());
		new CompactFluorescentLighting(30, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2,18}, new double[] {1,1})), getRates());
		new Clothwasher(state, 200, new CyclicUsageParameter(2, new FixedTimeOfUse(48, new double[] {9,17})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
