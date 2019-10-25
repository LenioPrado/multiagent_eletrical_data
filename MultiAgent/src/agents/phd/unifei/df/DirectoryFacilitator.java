package agents.phd.unifei.df;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class DirectoryFacilitator extends Agent{

	@Override
	protected void setup() {
		ServiceDescription sd = new ServiceDescription();
		sd.setType("sender");
		sd.setName(getLocalName());
		register(sd);
	}
	
	protected void register(ServiceDescription sd) {
		try {
			DFAgentDescription df = new DFAgentDescription();
			df.setName(this.getAID());
			df.addServices(sd);
			DFService.register(this, df);
			
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}
	
	protected void takeDown() 
    {
       try { DFService.deregister(this); }
       catch (Exception e) {}
    }
}
