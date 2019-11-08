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

public class SimulatorGroup3Case5 extends BaseSimulationCase {
		
	public SimulatorGroup3Case5(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(30, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {1,2})), getRates());
		new Thermostat(22.3,  new double[] {7,11,14,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,13,20}, new double[] {0.75,1.46,1.21}), StoveBurnerSize.SEVEN, new double[] {92,11,85,96});
		new Room(2.54,3.83,2.47,1.32,1.86, RoomWindow.YES);
		new Room(5.09,4.37,2.96,2.04,1.29, RoomWindow.YES);
		new Room(4.07,2.22,2.72,1.58,2.45, RoomWindow.YES);
		new Room(4.07,2.07,3.44,1.03,2.09, RoomWindow.YES);
		new Refrigerator(state, 127, 1.3, 1.3, 1.3, getRates());
		new IncandescentLighting(99, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {5,4})), getRates());
		new CompactFluorescentLighting(37, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {3,5})), getRates());
		new Clothwasher(state, 161, new CyclicUsageParameter(2, new FixedTimeOfUse(57, new double[] {7,16})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
