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

public class SimulatorGroup3Case10 extends BaseSimulationCase {
		
	public SimulatorGroup3Case10(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(40, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {2,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,15,22}, new double[] {1.25,0.95,2.33}), StoveBurnerSize.NINE, new double[] {47,99,88,50});
		new Room(4.25,2.05,3.39,2.14,1.96, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1.3, 1.7, getRates());
		new IncandescentLighting(95, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {8,18}, new double[] {5,3})), getRates());
		new CompactFluorescentLighting(30, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,20}, new double[] {4,2})), getRates());
		new Clothwasher(state, 248, new CyclicUsageParameter(1, new FixedTimeOfUse(86, new double[] {5})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
