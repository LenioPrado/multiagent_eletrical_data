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
		new TubeFluorescentLighting(33, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {2,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,12}, new double[] {2.26,1.2}), StoveBurnerSize.NINE, new double[] {95,52,29,89});
		new Room(5.36,2.94,2.46,1.47,2.41, RoomWindow.YES);
		new Room(2.61,4.13,3.14,1.41,1.61, RoomWindow.YES);
		new Room(5.19,3.12,2.31,1.31,2.14, RoomWindow.YES);
		new Refrigerator(state, 127, 1.3, 1.2, 1.2, getRates());
		new IncandescentLighting(100, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {4,3})), getRates());
		new CompactFluorescentLighting(25, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {3,21}, new double[] {3,3})), getRates());
		new Clothwasher(state, 182, new CyclicUsageParameter(1, new FixedTimeOfUse(68, new double[] {10})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
