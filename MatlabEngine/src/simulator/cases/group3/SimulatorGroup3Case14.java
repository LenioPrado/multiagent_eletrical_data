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

public class SimulatorGroup3Case14 extends BaseSimulationCase {
		
	public SimulatorGroup3Case14(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(21, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {3,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,14}, new double[] {1.65,0.73}), StoveBurnerSize.EIGHT, new double[] {28,44,17,15});
		new Room(3.61,4.4,2.41,2.08,1.19, RoomWindow.YES);
		new Refrigerator(state, 220, 1.1, 1.3, 1.6, getRates());
		new IncandescentLighting(88, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {1,22}, new double[] {2,5})), getRates());
		new CompactFluorescentLighting(31, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {4,3})), getRates());
		new Clothwasher(state, 259, new CyclicUsageParameter(1, new FixedTimeOfUse(84, new double[] {5})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
