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
		new TubeFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {8,21}, new double[] {4,2})), getRates());
		new Thermostat(23.1,  new double[] {6,12,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,16,21}, new double[] {2.2,2.28,2.2}), StoveBurnerSize.EIGHT, new double[] {71,48,33,10});
		new Room(2.09,3.17,2.1,2.2,2.03, RoomWindow.YES);
		new Refrigerator(state, 220, 1.1, 1, 1.7, getRates());
		new IncandescentLighting(100, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,23}, new double[] {5,2})), getRates());
		new CompactFluorescentLighting(24, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5,18}, new double[] {4,3})), getRates());
		new Clothwasher(state, 267, new CyclicUsageParameter(3, new FixedTimeOfUse(42, new double[] {5,13,18})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
