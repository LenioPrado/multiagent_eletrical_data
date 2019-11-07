package simulator.cases.group2;

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
import equipment.cyclicusage.Dishwasher;
import equipment.hvac.AirConditioner;
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

public class SimulatorGroup2Case20 extends BaseSimulationCase {
		
	public SimulatorGroup2Case20(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(34, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,21}, new double[] {5,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,14,18}, new double[] {2.15,1.75,0.96}), StoveBurnerSize.NINE, new double[] {23,6,64,52});
		Room r1 = new Room(5.02,4.86,2.16,1.97,1.21, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.1, 1.2, 1.9, getRates());
		new IncandescentLighting(99, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {5,5})), getRates());
		new Dishwasher(state, 203, new CyclicUsageParameter(2, new FixedTimeOfUse(47, new double[] {5,12})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(23, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {3,3})), getRates());
		new Clothwasher(state, 153, new CyclicUsageParameter(3, new FixedTimeOfUse(76, new double[] {6,13,19})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffDryer();
		createOffBattery();
	}
}
