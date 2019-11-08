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

public class SimulatorGroup3Case17 extends BaseSimulationCase {
		
	public SimulatorGroup3Case17(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(37, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {1,4})), getRates());
		new Thermostat(17,  new double[] {6,10,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,13,18}, new double[] {0.96,2.5,1.2}), StoveBurnerSize.SIX, new double[] {43,8,86,47});
		new Room(4.81,5.21,3.24,1.55,1.5, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1.2, 1.7, getRates());
		new IncandescentLighting(95, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {4,1})), getRates());
		new CompactFluorescentLighting(28, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {3,2})), getRates());
		new Clothwasher(state, 266, new CyclicUsageParameter(1, new FixedTimeOfUse(52, new double[] {9})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
