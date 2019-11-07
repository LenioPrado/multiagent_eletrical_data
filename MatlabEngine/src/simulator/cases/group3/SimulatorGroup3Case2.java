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

public class SimulatorGroup3Case2 extends BaseSimulationCase {
		
	public SimulatorGroup3Case2(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(38, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {3,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,17}, new double[] {2.75,2.26}), StoveBurnerSize.SEVEN, new double[] {18,51,64,18});
		new Room(3.76,4.42,2.05,1.03,2.07, RoomWindow.YES);
		new Room(3.27,3.25,2.2,2.06,1.11, RoomWindow.YES);
		new Room(2.22,3.89,3.48,1.69,1.85, RoomWindow.YES);
		new Room(3.09,4.41,3.39,2.46,1.09, RoomWindow.YES);
		new Refrigerator(state, 220, 1.3, 1, 2, getRates());
		new IncandescentLighting(95, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,23}, new double[] {2,2})), getRates());
		new CompactFluorescentLighting(27, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {7,21}, new double[] {4,1})), getRates());
		new Clothwasher(state, 252, new CyclicUsageParameter(2, new FixedTimeOfUse(82, new double[] {11,13})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
