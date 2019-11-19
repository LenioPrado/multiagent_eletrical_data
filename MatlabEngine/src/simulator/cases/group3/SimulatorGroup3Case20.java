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

public class SimulatorGroup3Case20 extends BaseSimulationCase {
		
	public SimulatorGroup3Case20(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(26, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {9,20}, new double[] {5,3})), getRates());
		new Thermostat(22.1,  new double[] {8,12,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,17,21}, new double[] {2.15,1.18,2.4}), StoveBurnerSize.NINE, new double[] {70,76,1,83});
		new Room(4.24,4.19,2.87,1.36,1.17, RoomWindow.YES);
		new Refrigerator(state, 220, 1, 1.2, 1.6, getRates());
		new IncandescentLighting(83, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {5,1})), getRates());
		new CompactFluorescentLighting(24, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,22}, new double[] {5,5})), getRates());
		new Clothwasher(state, 217, new CyclicUsageParameter(3, new FixedTimeOfUse(64, new double[] {6,17,19})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
