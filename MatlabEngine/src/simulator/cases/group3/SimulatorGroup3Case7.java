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
		new TubeFluorescentLighting(26, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {4,1})), getRates());
		new Thermostat(21.8,  new double[] {8,12,13,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,13,20}, new double[] {2.36,2.91,0.55}), StoveBurnerSize.NINE, new double[] {62,83,40,47});
		new Room(2.48,2.97,3.38,1,1.3, RoomWindow.YES);
		new Refrigerator(state, 220, 1.1, 1.1, 1.8, getRates());
		new IncandescentLighting(68, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {3,3})), getRates());
		new CompactFluorescentLighting(26, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {5,5})), getRates());
		new Clothwasher(state, 204, new CyclicUsageParameter(2, new FixedTimeOfUse(84, new double[] {9,12})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
