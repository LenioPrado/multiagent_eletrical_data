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

public class SimulatorGroup3Case13 extends BaseSimulationCase {
		
	public SimulatorGroup3Case13(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(35, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {4,2})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,15,21}, new double[] {2.25,1.61,2.26}), StoveBurnerSize.NINE, new double[] {78,1,93,80});
		new Room(4.58,4.95,3.38,1.24,1.91, RoomWindow.YES);
		new Room(4.67,2.13,2.81,2.32,1.7, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1.3, 1.9, getRates());
		new IncandescentLighting(90, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {3,2})), getRates());
		new CompactFluorescentLighting(38, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {5,5})), getRates());
		new Clothwasher(state, 269, new CyclicUsageParameter(2, new FixedTimeOfUse(86, new double[] {7,15})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
