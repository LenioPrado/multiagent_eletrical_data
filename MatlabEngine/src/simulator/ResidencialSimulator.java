package simulator;
import java.util.ArrayList;
import java.util.List;

import enums.WaterHeaterType;
import enums.YearSeason;
import equipment.hvac.WaterHeaterParameters;
import equipment.rates.Rates;
import helper.MatlabHelper;
import simulator.cases.appliances.AirConditionerSimulation;
import simulator.cases.appliances.BatterySimulation;
import simulator.cases.appliances.ClothwasherSimulation;
import simulator.cases.appliances.DishwasherSimulation;
import simulator.cases.appliances.DryerSimulation;
import simulator.cases.appliances.FurnaceSimulation;
import simulator.cases.appliances.PVSimulation;
import simulator.cases.appliances.PoolPumpSimulation;
import simulator.cases.appliances.RefrigeratorSimulation;
import simulator.cases.appliances.StoveSimulation;
import simulator.cases.appliances.WaterHeaterSimulation;
import simulator.cases.appliances.WindSimulation;
import simulator.cases.group1.SimulatorGroup1Case1;
import simulator.cases.group1.SimulatorGroup1Case2;
import simulator.cases.group1.SimulatorGroup1Case3;
import simulator.cases.group1.SimulatorGroup1Case4;
import simulator.cases.group1.SimulatorGroup1Case5;
import simulator.cases.group1.SimulatorGroup1Case6;
import simulator.cases.group1.SimulatorGroup1Case7;
import simulator.cases.group1.SimulatorGroup1Case8;
import simulator.cases.group1.SimulatorGroup1Case9;
import simulator.cases.group1.SimulatorGroup1Case10;
import simulator.cases.group1.SimulatorGroup1Case11;
import simulator.cases.group1.SimulatorGroup1Case12;
import simulator.cases.group1.SimulatorGroup1Case13;
import simulator.cases.group1.SimulatorGroup1Case14;
import simulator.cases.group1.SimulatorGroup1Case15;
import simulator.cases.group1.SimulatorGroup1Case16;
import simulator.cases.group1.SimulatorGroup1Case17;
import simulator.cases.group1.SimulatorGroup1Case18;
import simulator.cases.group1.SimulatorGroup1Case19;
import simulator.cases.group1.SimulatorGroup1Case20;
import simulator.cases.group2.SimulatorGroup2Case1;
import simulator.cases.group2.SimulatorGroup2Case2;
import simulator.cases.group2.SimulatorGroup2Case3;
import simulator.cases.group2.SimulatorGroup2Case4;
import simulator.cases.group2.SimulatorGroup2Case5;
import simulator.cases.group2.SimulatorGroup2Case6;
import simulator.cases.group2.SimulatorGroup2Case7;
import simulator.cases.group2.SimulatorGroup2Case8;
import simulator.cases.group2.SimulatorGroup2Case9;
import simulator.cases.group2.SimulatorGroup2Case10;
import simulator.cases.group2.SimulatorGroup2Case11;
import simulator.cases.group2.SimulatorGroup2Case12;
import simulator.cases.group2.SimulatorGroup2Case13;
import simulator.cases.group2.SimulatorGroup2Case14;
import simulator.cases.group2.SimulatorGroup2Case15;
import simulator.cases.group2.SimulatorGroup2Case16;
import simulator.cases.group2.SimulatorGroup2Case17;
import simulator.cases.group2.SimulatorGroup2Case18;
import simulator.cases.group2.SimulatorGroup2Case19;
import simulator.cases.group2.SimulatorGroup2Case20;
import simulator.cases.group3.SimulatorGroup3Case1;
import simulator.cases.group3.SimulatorGroup3Case2;
import simulator.cases.group3.SimulatorGroup3Case3;
import simulator.cases.group3.SimulatorGroup3Case4;
import simulator.cases.group3.SimulatorGroup3Case5;
import simulator.cases.group3.SimulatorGroup3Case6;
import simulator.cases.group3.SimulatorGroup3Case7;
import simulator.cases.group3.SimulatorGroup3Case8;
import simulator.cases.group3.SimulatorGroup3Case9;
import simulator.cases.group3.SimulatorGroup3Case10;
import simulator.cases.group3.SimulatorGroup3Case11;
import simulator.cases.group3.SimulatorGroup3Case12;
import simulator.cases.group3.SimulatorGroup3Case13;
import simulator.cases.group3.SimulatorGroup3Case14;
import simulator.cases.group3.SimulatorGroup3Case15;
import simulator.cases.group3.SimulatorGroup3Case16;
import simulator.cases.group3.SimulatorGroup3Case17;
import simulator.cases.group3.SimulatorGroup3Case18;
import simulator.cases.group3.SimulatorGroup3Case19;
import simulator.cases.group3.SimulatorGroup3Case20;

public class ResidencialSimulator {
	
	public static void main(String[] args) throws Exception {
		ResidencialSimulator simulator = new ResidencialSimulator();
		simulator.executeResidencialSimulator("E:\\Develop\\PhD Softwares\\SRLS - Smart Residential Load Simulator\\SRLS2\\","Energy_Cost.mdl");
	}

	private void executeResidencialSimulator(String modelFolder, String modelName) throws Exception {
		MatlabHelper.changeToFolder(modelFolder);	
		boolean runModel = true;
		if(runModel) {
			MatlabHelper.loadSimulinkModel(modelFolder, modelName);
			List<BaseSimulationCase> simulationCases = getSimulationCases();
			
			for (int counter=0; counter<simulationCases.size(); counter++) {
				try {					
					System.out.println("Simulation: " + (counter+1));
					BaseSimulationCase sim = simulationCases.get(counter);
					sim.setSimulationValues();
					MatlabHelper.runSimulinkModel(modelName, counter);
					MatlabHelper.feval("processResults", counter);
					MatlabHelper.getEngine().eval(String.format("plotLoadValues('%s_%d', %d)", sim.getSimulationDescription(), counter, counter));					
					sim.resetSimulationValues();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}				
			}				
			
			System.out.println("Ending App...");
		}
	}
	
	//MatlabHelper.getEngine().eval(String.format("plotGenerationValues('%s_%d')", sim.getSimulationDescription(), i));
	//MatlabHelper.getEngine().eval(String.format("plotApplianceValues('%s_%d')", sim.getSimulationDescription(), i));
	//MatlabHelper.getEngine().eval(String.format("plotStateOfChargeValues('%s_%d')", sim.getSimulationDescription(), i));	
	
	private List<BaseSimulationCase> getSimulationCases() throws Exception {
		List<BaseSimulationCase> simulationCases = new ArrayList<BaseSimulationCase>();		
		
		Rates rates = new Rates(6.2, 9.2, 10.8, 35.7, YearSeason.SUMMER);
		
		// Group 1 Cases
//		simulationCases.add(new SimulatorGroup1Case1(rates));
//		simulationCases.add(new SimulatorGroup1Case2(rates));
//		simulationCases.add(new SimulatorGroup1Case3(rates));
//		simulationCases.add(new SimulatorGroup1Case4(rates));
//		simulationCases.add(new SimulatorGroup1Case5(rates));
//		simulationCases.add(new SimulatorGroup1Case6(rates));
//		simulationCases.add(new SimulatorGroup1Case7(rates));
//		simulationCases.add(new SimulatorGroup1Case8(rates));
//		simulationCases.add(new SimulatorGroup1Case9(rates));
//		simulationCases.add(new SimulatorGroup1Case10(rates));
//		simulationCases.add(new SimulatorGroup1Case11(rates));
//		simulationCases.add(new SimulatorGroup1Case12(rates));
//		simulationCases.add(new SimulatorGroup1Case13(rates));
//		simulationCases.add(new SimulatorGroup1Case14(rates));
//		simulationCases.add(new SimulatorGroup1Case15(rates));
//		simulationCases.add(new SimulatorGroup1Case16(rates));
//		simulationCases.add(new SimulatorGroup1Case17(rates));
//		simulationCases.add(new SimulatorGroup1Case18(rates));
//		simulationCases.add(new SimulatorGroup1Case19(rates));
//		simulationCases.add(new SimulatorGroup1Case20(rates));
		
		// Group 2 Cases
//		simulationCases.add(new SimulatorGroup2Case1(rates));
//		simulationCases.add(new SimulatorGroup2Case2(rates));
//		simulationCases.add(new SimulatorGroup2Case3(rates));
//		simulationCases.add(new SimulatorGroup2Case4(rates));
//		simulationCases.add(new SimulatorGroup2Case5(rates));
//		simulationCases.add(new SimulatorGroup2Case6(rates));
//		simulationCases.add(new SimulatorGroup2Case7(rates));
//		simulationCases.add(new SimulatorGroup2Case8(rates));
//		simulationCases.add(new SimulatorGroup2Case9(rates));
//		simulationCases.add(new SimulatorGroup2Case10(rates));
//		simulationCases.add(new SimulatorGroup2Case11(rates));
//		simulationCases.add(new SimulatorGroup2Case12(rates));
//		simulationCases.add(new SimulatorGroup2Case13(rates));
//		simulationCases.add(new SimulatorGroup2Case14(rates));
//		simulationCases.add(new SimulatorGroup2Case15(rates));
//		simulationCases.add(new SimulatorGroup2Case16(rates));
//		simulationCases.add(new SimulatorGroup2Case17(rates));
//		simulationCases.add(new SimulatorGroup2Case18(rates));
//		simulationCases.add(new SimulatorGroup2Case19(rates));
//		simulationCases.add(new SimulatorGroup2Case20(rates));

		// Group 3 Cases
		simulationCases.add(new SimulatorGroup3Case1(rates));
		simulationCases.add(new SimulatorGroup3Case2(rates));
		simulationCases.add(new SimulatorGroup3Case3(rates));
		simulationCases.add(new SimulatorGroup3Case4(rates));
		simulationCases.add(new SimulatorGroup3Case5(rates));
		simulationCases.add(new SimulatorGroup3Case6(rates));
		simulationCases.add(new SimulatorGroup3Case7(rates));
		simulationCases.add(new SimulatorGroup3Case8(rates));
		simulationCases.add(new SimulatorGroup3Case9(rates));
		simulationCases.add(new SimulatorGroup3Case10(rates));
		simulationCases.add(new SimulatorGroup3Case11(rates));
		simulationCases.add(new SimulatorGroup3Case12(rates));
		simulationCases.add(new SimulatorGroup3Case13(rates));
		simulationCases.add(new SimulatorGroup3Case14(rates));
		simulationCases.add(new SimulatorGroup3Case15(rates));
		simulationCases.add(new SimulatorGroup3Case16(rates));
		simulationCases.add(new SimulatorGroup3Case17(rates));
		simulationCases.add(new SimulatorGroup3Case18(rates));
		simulationCases.add(new SimulatorGroup3Case19(rates));
		simulationCases.add(new SimulatorGroup3Case20(rates));
		
		//addSimulationCases(simulationCases, rates);
		
		return simulationCases;
	}
	
	public void addSimulationCases(List<BaseSimulationCase> simulationCases, Rates rates) throws Exception {
		simulationCases.add(new AirConditionerSimulation(rates));
		simulationCases.add(new FurnaceSimulation(rates));
		simulationCases.add(new WaterHeaterSimulation(new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184, 0.62f), rates));
		simulationCases.add(new WaterHeaterSimulation(new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 5, 13, 60000, 68, 284, 0.52f), rates));
		
		simulationCases.add(new DryerSimulation(rates));		
		simulationCases.add(new ClothwasherSimulation(rates));
		simulationCases.add(new DishwasherSimulation(rates));
		simulationCases.add(new PoolPumpSimulation(rates));		
		simulationCases.add(new RefrigeratorSimulation(rates));
		simulationCases.add(new StoveSimulation(rates));

		simulationCases.add(new BatterySimulation(rates));		
		simulationCases.add(new PVSimulation(rates));
		simulationCases.add(new WindSimulation(rates));		
	}
}