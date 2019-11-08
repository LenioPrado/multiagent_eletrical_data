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

public class SimulatorGroup3Case18 extends BaseSimulationCase {
		
	public SimulatorGroup3Case18(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(22, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,21}, new double[] {4,5})), getRates());
		new Thermostat(20.9,  new double[] {9,11,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,16,20}, new double[] {2.7,2.38,2.08}), StoveBurnerSize.SEVEN, new double[] {21,55,36,10});
		new Room(4.61,4.14,3.29,1.45,1.66, RoomWindow.YES);
		new Refrigerator(state, 127, 1, 1, 1.8, getRates());
		new IncandescentLighting(90, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {3,1})), getRates());
		new CompactFluorescentLighting(39, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {5,1})), getRates());
		new Clothwasher(state, 160, new CyclicUsageParameter(1, new FixedTimeOfUse(87, new double[] {7})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
