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

public class SimulatorGroup3Case7 extends BaseSimulationCase {
		
	public SimulatorGroup3Case7(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(32, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,20}, new double[] {3,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14}, new double[] {2.25,1.28}), StoveBurnerSize.NINE, new double[] {25,30,57,68});
		new Room(5.4,5.22,3.44,2.08,1.29, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1.2, 1.1, getRates());
		new IncandescentLighting(97, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {1,2})), getRates());
		new CompactFluorescentLighting(33, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {0,22}, new double[] {3,5})), getRates());
		new Clothwasher(state, 271, new CyclicUsageParameter(3, new FixedTimeOfUse(46, new double[] {5,16,21})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
