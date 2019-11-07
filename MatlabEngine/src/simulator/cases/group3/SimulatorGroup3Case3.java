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
		new TubeFluorescentLighting(35, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {9,21}, new double[] {3,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,15,20}, new double[] {0.96,0.88,0.63}), StoveBurnerSize.SIX, new double[] {57,38,65,0});
		new Room(4.8,2.04,3.48,1.76,2.09, RoomWindow.YES);
		new Room(4.88,3.5,2.31,2.35,2.32, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1.2, 1.4, getRates());
		new IncandescentLighting(83, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {3,4})), getRates());
		new CompactFluorescentLighting(38, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {9,19}, new double[] {3,4})), getRates());
		new Clothwasher(state, 275, new CyclicUsageParameter(3, new FixedTimeOfUse(79, new double[] {9,13,19})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
