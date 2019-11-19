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

public class SimulatorGroup3Case2 extends BaseSimulationCase {
		
	public SimulatorGroup3Case2(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(37, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {3,1})), getRates());
		new Thermostat(16.9,  new double[] {8,11,15,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,13,19}, new double[] {2.78,0.86,2.9}), StoveBurnerSize.SIX, new double[] {39,10,97,32});
		new Room(4.16,4.17,2.54,1.43,2.41, RoomWindow.YES);
		new Refrigerator(state, 220, 1.1, 1, 1.5, getRates());
		new IncandescentLighting(65, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,21}, new double[] {2,5})), getRates());
		new CompactFluorescentLighting(32, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,18}, new double[] {2,5})), getRates());
		new Clothwasher(state, 227, new CyclicUsageParameter(1, new FixedTimeOfUse(64, new double[] {10})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
