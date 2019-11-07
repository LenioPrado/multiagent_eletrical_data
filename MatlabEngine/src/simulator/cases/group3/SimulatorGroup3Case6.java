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

public class SimulatorGroup3Case6 extends BaseSimulationCase {
		
	public SimulatorGroup3Case6(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(38, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {1,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,15,20}, new double[] {2.11,0.86,1.5}), StoveBurnerSize.NINE, new double[] {0,55,64,5});
		new Room(3.01,3.38,2.41,2.42,1.83, RoomWindow.YES);
		new Room(2.98,5.5,2.08,2.11,1.68, RoomWindow.YES);
		new Room(5.48,3.38,3.11,1.85,1.79, RoomWindow.YES);
		new Refrigerator(state, 220, 1, 1.3, 1.2, getRates());
		new IncandescentLighting(74, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {4,1})), getRates());
		new CompactFluorescentLighting(30, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {2,1})), getRates());
		new Clothwasher(state, 194, new CyclicUsageParameter(2, new FixedTimeOfUse(41, new double[] {5,14})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
