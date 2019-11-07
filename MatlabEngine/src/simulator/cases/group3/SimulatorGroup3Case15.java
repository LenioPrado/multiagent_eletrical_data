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

public class SimulatorGroup3Case15 extends BaseSimulationCase {
		
	public SimulatorGroup3Case15(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(39, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {5,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,17,19}, new double[] {1.15,2.3,1.88}), StoveBurnerSize.SIX, new double[] {21,34,69,29});
		new Room(2.37,4.54,3.23,1.42,1.64, RoomWindow.YES);
		new Room(3.76,2.21,2.98,2.49,1.71, RoomWindow.YES);
		new Room(4.27,3.61,2.19,1.87,2.1, RoomWindow.YES);
		new Refrigerator(state, 220, 1.3, 1.2, 1.1, getRates());
		new IncandescentLighting(91, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {4,18}, new double[] {5,1})), getRates());
		new CompactFluorescentLighting(24, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {1,4})), getRates());
		new Clothwasher(state, 252, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {8,12,22})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
