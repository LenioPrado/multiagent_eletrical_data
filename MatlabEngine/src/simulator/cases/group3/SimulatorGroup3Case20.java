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

public class SimulatorGroup3Case20 extends BaseSimulationCase {
		
	public SimulatorGroup3Case20(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(35, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {5,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7}, new double[] {1.13}), StoveBurnerSize.EIGHT, new double[] {89,91,27,2});
		new Room(2.95,2.78,3.07,2.44,2.31, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1.3, 2, getRates());
		new IncandescentLighting(64, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {4,3})), getRates());
		new CompactFluorescentLighting(37, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {1,20}, new double[] {4,3})), getRates());
		new Clothwasher(state, 187, new CyclicUsageParameter(2, new FixedTimeOfUse(54, new double[] {6,12})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
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
