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

public class SimulatorGroup3Case3 extends BaseSimulationCase {
		
	public SimulatorGroup3Case3(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(33, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {5,2})), getRates());
		new Thermostat(16.1,  new double[] {9,11,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,15,18}, new double[] {1.98,1.23,2.73}), StoveBurnerSize.EIGHT, new double[] {18,39,87,62});
		new Room(3.76,4.38,3.18,2.06,2.44, RoomWindow.YES);
		new Room(3.51,2.15,3.46,1.61,1.82, RoomWindow.YES);
		new Room(3.89,2.87,2.62,1.96,1.73, RoomWindow.YES);
		new Room(5.09,4.91,2.03,2.38,2.18, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1.2, 1.6, getRates());
		new IncandescentLighting(66, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5,18}, new double[] {2,1})), getRates());
		new CompactFluorescentLighting(33, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {2,2})), getRates());
		new Clothwasher(state, 256, new CyclicUsageParameter(1, new FixedTimeOfUse(87, new double[] {5})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
