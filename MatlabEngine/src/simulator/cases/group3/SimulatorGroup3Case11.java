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
		new TubeFluorescentLighting(39, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {1,2})), getRates());
		new Thermostat(19.1,  new double[] {7,11,13,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,14,19}, new double[] {1.23,2.2,2.48}), StoveBurnerSize.EIGHT, new double[] {60,15,30,0});
		new Room(3.91,3.04,2.24,1.51,2.08, RoomWindow.YES);
		new Room(2.44,3.13,2.3,1.38,1.46, RoomWindow.YES);
		new Refrigerator(state, 127, 1.3, 1.2, 1.1, getRates());
		new IncandescentLighting(91, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {4,5})), getRates());
		new CompactFluorescentLighting(28, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {4,5})), getRates());
		new Clothwasher(state, 176, new CyclicUsageParameter(1, new FixedTimeOfUse(66, new double[] {5})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
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
