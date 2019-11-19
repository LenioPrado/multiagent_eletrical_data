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
import home.Thermostat;
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup2Case8 extends BaseSimulationCase {
		
	public SimulatorGroup2Case8(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(30, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {4,5})), getRates());
		Thermostat thermostat = new Thermostat(23.8,  new double[] {7,12,14,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,12,21}, new double[] {0.65,1.06,2.28}), StoveBurnerSize.SIX, new double[] {33,71,18,94});
		Room r1 = new Room(5.37,5.48,3.11,1.9,1.69, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.1, 1.2, 1.9, getRates());
		new IncandescentLighting(61, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {4,21}, new double[] {5,2})), getRates());
		new Dishwasher(state, 234, new CyclicUsageParameter(1, new FixedTimeOfUse(61, new double[] {9})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {5,5})), getRates());
		new Clothwasher(state, 239, new CyclicUsageParameter(1, new FixedTimeOfUse(35, new double[] {10})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 46249, getRates());
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
