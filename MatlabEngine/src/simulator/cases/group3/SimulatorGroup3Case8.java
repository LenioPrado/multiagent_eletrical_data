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

public class SimulatorGroup3Case8 extends BaseSimulationCase {
		
	public SimulatorGroup3Case8(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(25, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {1,20}, new double[] {4,2})), getRates());
		new Thermostat(22.6,  new double[] {7,12,15,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,15,20}, new double[] {1.71,0.66,0.56}), StoveBurnerSize.SIX, new double[] {21,99,13,63});
		new Room(3.52,3.91,2.76,1.33,2.48, RoomWindow.YES);
		new Room(4.27,4.22,2.26,2.04,1.5, RoomWindow.YES);
		new Room(2.62,4.04,2.93,1.77,1.34, RoomWindow.YES);
		new Refrigerator(state, 220, 1.2, 1.3, 1.9, getRates());
		new IncandescentLighting(100, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {1,3})), getRates());
		new CompactFluorescentLighting(23, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {8,21}, new double[] {5,1})), getRates());
		new Clothwasher(state, 199, new CyclicUsageParameter(2, new FixedTimeOfUse(48, new double[] {11,16})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffDryer();
		createOffDishwasher();
		createOffBattery();
		createOffAirConditioner();
	}
}
