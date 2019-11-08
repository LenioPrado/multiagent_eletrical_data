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

public class SimulatorGroup3Case13 extends BaseSimulationCase {
		
	public SimulatorGroup3Case13(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {1,20}, new double[] {5,3})), getRates());
		new Thermostat(18.4,  new double[] {7,11,15,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,15,18}, new double[] {2.88,2.9,1.85}), StoveBurnerSize.NINE, new double[] {48,97,50,84});
		new Room(2.12,3.05,3.14,1.88,1.78, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1.1, 1.7, getRates());
		new IncandescentLighting(80, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {5,4})), getRates());
		new CompactFluorescentLighting(37, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,21}, new double[] {1,5})), getRates());
		new Clothwasher(state, 273, new CyclicUsageParameter(3, new FixedTimeOfUse(56, new double[] {10,17,21})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
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
