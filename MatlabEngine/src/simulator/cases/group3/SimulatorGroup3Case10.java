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
		new TubeFluorescentLighting(33, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {4,5})), getRates());
		new Thermostat(23.3,  new double[] {7,10,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,16,18}, new double[] {1.85,2.66,0.63}), StoveBurnerSize.SIX, new double[] {27,21,54,55});
		new Room(2.03,5.01,2.2,1.82,2.27, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1.3, 1.2, getRates());
		new IncandescentLighting(97, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {9,19}, new double[] {3,4})), getRates());
		new CompactFluorescentLighting(39, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {3,4})), getRates());
		new Clothwasher(state, 168, new CyclicUsageParameter(3, new FixedTimeOfUse(49, new double[] {11,14,18})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
