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

public class SimulatorGroup3Case14 extends BaseSimulationCase {
		
	public SimulatorGroup3Case14(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(22, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {1,1})), getRates());
		new Thermostat(27.7,  new double[] {6,11,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,12,18}, new double[] {0.53,0.5,2.98}), StoveBurnerSize.SEVEN, new double[] {55,32,69,55});
		new Room(3.26,2.47,2.3,1.34,1.86, RoomWindow.YES);
		new Room(4.73,4.42,2.17,1.27,2, RoomWindow.YES);
		new Room(4.31,4.39,2.49,2.48,2.34, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1, 2, getRates());
		new IncandescentLighting(91, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {1,2})), getRates());
		new CompactFluorescentLighting(36, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {1,22}, new double[] {4,4})), getRates());
		new Clothwasher(state, 151, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {11,14})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
