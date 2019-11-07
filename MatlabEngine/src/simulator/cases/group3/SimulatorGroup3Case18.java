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
		new TubeFluorescentLighting(37, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {1,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,16}, new double[] {0.6,1.58}), StoveBurnerSize.NINE, new double[] {63,58,86,14});
		new Room(3.65,2.77,2.5,1.2,2.32, RoomWindow.YES);
		new Room(5.49,2.55,3.24,1.81,2.16, RoomWindow.YES);
		new Room(4.66,4.2,3.33,1.74,1.77, RoomWindow.YES);
		new Refrigerator(state, 220, 1, 1.2, 1.6, getRates());
		new IncandescentLighting(71, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {1,3})), getRates());
		new CompactFluorescentLighting(32, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,20}, new double[] {4,5})), getRates());
		new Clothwasher(state, 235, new CyclicUsageParameter(2, new FixedTimeOfUse(85, new double[] {9,13})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
