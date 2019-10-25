package agents.phd.unifei.sellerbuyer;

import java.util.Hashtable;
import java.util.Set;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Inner class OfferRequestsServer. This is the behaviour used by Book-seller
 * agents to serve incoming requests for offer from buyer agents. If the
 * requested book is in the local catalogue the seller agent replies with a
 * PROPOSE message specifying the price. Otherwise a REFUSE message is sent
 * back.
 */
public class OfferRequestsServer extends CyclicBehaviour {
	
	private Hashtable catalogue;

	public OfferRequestsServer(Hashtable catalogue) {
		this.catalogue = catalogue;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			// Message received. Process it
			String title = msg.getContent();
			ACLMessage reply = msg.createReply();
			Integer price = (Integer) catalogue.get(title);
			if (price != null) {
				// The requested book is available for sale. Reply with the price
				reply.setPerformative(ACLMessage.PROPOSE);
				reply.setContent(String.valueOf(price.intValue()));
			} else {
				// The requested book is NOT available for sale.
				reply.setPerformative(ACLMessage.REFUSE);
				reply.setContent("not-available");
			}
			myAgent.send(reply);
		}
		else {
			block();
		}
	}
	
	public void setCatalogue(Hashtable catalogue) {
		this.catalogue = catalogue;
		
		Set<String> keys = catalogue.keySet();
		for (String key: keys) {
			System.out.println("Key-Value: " + key + " - " + ((Integer)catalogue.get(key)).intValue());
		}
	}
} // End of inner class OfferRequestsServer
