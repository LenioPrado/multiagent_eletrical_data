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

public class SimulatorGroup3Case12 extends BaseSimulationCase {
		
	public SimulatorGroup3Case12(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(35, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {2,3})), getRates());
		new Thermostat(16,  new double[] {7,12,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,17,20}, new double[] {0.78,0.85,1.21}), StoveBurnerSize.NINE, new double[] {13,59,83,58});
		new Room(2.68,2.44,3.32,1.02,1.86, RoomWindow.YES);
		new Room(2.43,4.52,2.37,2.28,2.24, RoomWindow.YES);
		new Refrigerator(state, 127, 1, 1.2, 1.4, getRates());
		new IncandescentLighting(99, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,20}, new double[] {5,1})), getRates());
		new CompactFluorescentLighting(31, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {2,1})), getRates());
		new Clothwasher(state, 245, new CyclicUsageParameter(1, new FixedTimeOfUse(72, new double[] {9})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
