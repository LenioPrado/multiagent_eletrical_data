package agents.phd.unifei.policethief;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import jade.core.Runtime;

public class PoliceThiefStarter {
	
	public static void main(String[] args){
	       Runtime runtime = Runtime.instance();
	       Profile profile = new ProfileImpl();
	       profile.setParameter(Profile.MAIN_HOST, "localhost");
	       profile.setParameter(Profile.GUI, "true");
	       ContainerController containerController = runtime.createMainContainer(profile);

	       for(int i=1; i<6; i++){
	           AgentController policeAgentController;
	           try {
	               policeAgentController = containerController.createNewAgent("PoliceAgent"+i, "agents.phd.unifei.policethief.PoliceAgent", null);
	               policeAgentController.start();    
	           } catch (StaleProxyException e) {
	               e.printStackTrace();
	           }
	       }

	       for(int i=1; i<4; i++){
	           AgentController thiefAgentController;
	           try {
	               thiefAgentController = containerController.createNewAgent("ThiefAgent"+i, "agents.phd.unifei.policethief.ThiefAgent", null);
	               thiefAgentController.start();    
	           } catch (StaleProxyException e) {
	               e.printStackTrace();
	           }
	        }
	   }	

}
