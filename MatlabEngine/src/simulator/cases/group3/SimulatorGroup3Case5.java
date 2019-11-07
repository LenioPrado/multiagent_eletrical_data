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

public class SimulatorGroup3Case5 extends BaseSimulationCase {
		
	public SimulatorGroup3Case5(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(35, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {5,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,13,21}, new double[] {2,1.95,2.3}), StoveBurnerSize.NINE, new double[] {43,62,76,57});
		new Room(3.13,4.27,2.2,2.2,1.14, RoomWindow.YES);
		new Refrigerator(state, 220, 1.3, 1, 1.2, getRates());
		new IncandescentLighting(100, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {2,5})), getRates());
		new CompactFluorescentLighting(31, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {5,22}, new double[] {2,3})), getRates());
		new Clothwasher(state, 197, new CyclicUsageParameter(3, new FixedTimeOfUse(49, new double[] {8,12,18})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
