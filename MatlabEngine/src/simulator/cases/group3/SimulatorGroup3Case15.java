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

public class SimulatorGroup3Case15 extends BaseSimulationCase {
		
	public SimulatorGroup3Case15(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(39, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2,21}, new double[] {4,2})), getRates());
		new Thermostat(24.2,  new double[] {6,10,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,13,21}, new double[] {2.33,0.8,2.86}), StoveBurnerSize.NINE, new double[] {62,77,97,10});
		new Room(4.53,3.5,2.08,2.09,1.61, RoomWindow.YES);
		new Refrigerator(state, 127, 1.1, 1.1, 1.6, getRates());
		new IncandescentLighting(92, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {7,22}, new double[] {3,3})), getRates());
		new CompactFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {5,1})), getRates());
		new Clothwasher(state, 205, new CyclicUsageParameter(3, new FixedTimeOfUse(31, new double[] {11,12,19})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
