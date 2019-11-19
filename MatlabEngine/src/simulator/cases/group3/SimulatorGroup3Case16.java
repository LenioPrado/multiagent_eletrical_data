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

public class SimulatorGroup3Case16 extends BaseSimulationCase {
		
	public SimulatorGroup3Case16(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(23, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {1,2})), getRates());
		new Thermostat(26.7,  new double[] {6,11,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,13,18}, new double[] {2.16,1.98,0.78}), StoveBurnerSize.SIX, new double[] {13,10,38,80});
		new Room(3.17,2.71,3,2.11,1.46, RoomWindow.YES);
		new Room(4.6,2.91,2.93,2.28,1.9, RoomWindow.YES);
		new Refrigerator(state, 220, 1, 1, 1.4, getRates());
		new IncandescentLighting(67, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {1,22}, new double[] {5,2})), getRates());
		new CompactFluorescentLighting(24, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {4,5})), getRates());
		new Clothwasher(state, 263, new CyclicUsageParameter(3, new FixedTimeOfUse(82, new double[] {5,15,18})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
