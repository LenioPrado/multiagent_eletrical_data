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

public class SimulatorGroup2Case3 extends BaseSimulationCase {
		
	public SimulatorGroup2Case3(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(21, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,18}, new double[] {1,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,15}, new double[] {2.03,1.66}), StoveBurnerSize.NINE, new double[] {43,69,0,89});
		Room r1 = new Room(5.2,4.27,3.34,2.3,1.75, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.2, 1.2, 1.3, getRates());
		new IncandescentLighting(97, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {3,3})), getRates());
		new Dishwasher(state, 150, new CyclicUsageParameter(3, new FixedTimeOfUse(90, new double[] {7,13,19})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(22, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,20}, new double[] {1,5})), getRates());
		new Clothwasher(state, 223, new CyclicUsageParameter(3, new FixedTimeOfUse(73, new double[] {6,16,18})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
