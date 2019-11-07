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
		new TubeFluorescentLighting(28, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {4,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,12}, new double[] {2.95,1.8}), StoveBurnerSize.NINE, new double[] {77,84,6,22});
		new Room(3.96,5.25,2.49,1.5,2.23, RoomWindow.YES);
		new Room(2.34,2.2,2.52,1.48,2.01, RoomWindow.YES);
		new Room(4.99,3.05,3.42,1.96,2.1, RoomWindow.YES);
		new Refrigerator(state, 220, 1.1, 1.2, 1.9, getRates());
		new IncandescentLighting(76, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {5,2})), getRates());
		new CompactFluorescentLighting(22, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,21}, new double[] {3,5})), getRates());
		new Clothwasher(state, 173, new CyclicUsageParameter(1, new FixedTimeOfUse(69, new double[] {10})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
