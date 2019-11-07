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

public class SimulatorGroup3Case17 extends BaseSimulationCase {
		
	public SimulatorGroup3Case17(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(32, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {4,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11}, new double[] {2.01}), StoveBurnerSize.SIX, new double[] {41,95,26,0});
		new Room(3.3,2.81,2.72,2.18,1.59, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1, 1.3, getRates());
		new IncandescentLighting(63, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {1,2})), getRates());
		new CompactFluorescentLighting(37, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {1,3})), getRates());
		new Clothwasher(state, 230, new CyclicUsageParameter(2, new FixedTimeOfUse(72, new double[] {5,13})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
