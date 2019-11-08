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

public class SimulatorGroup3Case1 extends BaseSimulationCase {
		
	public SimulatorGroup3Case1(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(38, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {1,3})), getRates());
		new Thermostat(15.3,  new double[] {9,11,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,13,22}, new double[] {2.88,2.21,2.11}), StoveBurnerSize.SIX, new double[] {99,69,90,93});
		new Room(3.39,4.86,3.3,1.25,2.19, RoomWindow.YES);
		new Refrigerator(state, 127, 1, 1, 1.7, getRates());
		new IncandescentLighting(65, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {1,22}, new double[] {1,2})), getRates());
		new CompactFluorescentLighting(23, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {9,20}, new double[] {3,1})), getRates());
		new Clothwasher(state, 270, new CyclicUsageParameter(2, new FixedTimeOfUse(58, new double[] {11,12})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
