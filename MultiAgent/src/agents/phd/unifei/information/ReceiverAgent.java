package agents.phd.unifei.information;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ReceiverAgent extends Agent {

	@Override
	protected void setup() {
		System.out.println("Hello! Receiver Agent " + getAID().getName() + " is ready.");		
		setService();		
		addBehaviour(new ReceiverBehaviour());
	}
	
	protected void takeDown() {
		System.out.println("Receiver-agent " + getAID().getName() + " terminating.");
	}
	
	protected void setService() {
		ServiceDescription sd = new ServiceDescription();
		sd.setType("receiver");
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
}
