package simulator;

import java.util.ArrayList;
import java.util.List;
import enums.YearSeason;
import equipment.rates.Rates;
import helper.MatlabHelper;
import simulator.cases.group1.*;
import simulator.cases.group2.*;
import simulator.cases.group3.*;

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
		simulationCases.add(new SimulatorGroup1Case1(rates));
		simulationCases.add(new SimulatorGroup1Case2(rates));
		simulationCases.add(new SimulatorGroup1Case3(rates));
		simulationCases.add(new SimulatorGroup1Case4(rates));
		simulationCases.add(new SimulatorGroup1Case5(rates));
		simulationCases.add(new SimulatorGroup1Case6(rates));
		simulationCases.add(new SimulatorGroup1Case7(rates));
		simulationCases.add(new SimulatorGroup1Case8(rates));
		simulationCases.add(new SimulatorGroup1Case9(rates));
		simulationCases.add(new SimulatorGroup1Case10(rates));
		simulationCases.add(new SimulatorGroup1Case11(rates));
		simulationCases.add(new SimulatorGroup1Case12(rates));
		simulationCases.add(new SimulatorGroup1Case13(rates));
		simulationCases.add(new SimulatorGroup1Case14(rates));
		simulationCases.add(new SimulatorGroup1Case15(rates));
		simulationCases.add(new SimulatorGroup1Case16(rates));
		simulationCases.add(new SimulatorGroup1Case17(rates));
		simulationCases.add(new SimulatorGroup1Case18(rates));
		simulationCases.add(new SimulatorGroup1Case19(rates));
		simulationCases.add(new SimulatorGroup1Case20(rates));
		
		// Group 2 Cases
		simulationCases.add(new SimulatorGroup2Case1(rates));
		simulationCases.add(new SimulatorGroup2Case2(rates));
		simulationCases.add(new SimulatorGroup2Case3(rates));
		simulationCases.add(new SimulatorGroup2Case4(rates));
		simulationCases.add(new SimulatorGroup2Case5(rates));
		simulationCases.add(new SimulatorGroup2Case6(rates));
		simulationCases.add(new SimulatorGroup2Case7(rates));
		simulationCases.add(new SimulatorGroup2Case8(rates));
		simulationCases.add(new SimulatorGroup2Case9(rates));
		simulationCases.add(new SimulatorGroup2Case10(rates));
		simulationCases.add(new SimulatorGroup2Case11(rates));
		simulationCases.add(new SimulatorGroup2Case12(rates));
		simulationCases.add(new SimulatorGroup2Case13(rates));
		simulationCases.add(new SimulatorGroup2Case14(rates));
		simulationCases.add(new SimulatorGroup2Case15(rates));
		simulationCases.add(new SimulatorGroup2Case16(rates));
		simulationCases.add(new SimulatorGroup2Case17(rates));
		simulationCases.add(new SimulatorGroup2Case18(rates));
		simulationCases.add(new SimulatorGroup2Case19(rates));
		simulationCases.add(new SimulatorGroup2Case20(rates));

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
		
		return simulationCases;
	}
}