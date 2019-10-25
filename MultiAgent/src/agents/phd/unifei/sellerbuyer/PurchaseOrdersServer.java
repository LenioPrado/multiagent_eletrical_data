package agents.phd.unifei.sellerbuyer;

import java.util.Hashtable;
import java.util.Set;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Inner class PurchaseOrdersServer. This is the behaviour used by Book-seller
 * agents to serve incoming offer acceptances (i.e. purchase orders) from buyer
 * agents. The seller agent removes the purchased book from its catalogue and
 * replies with an INFORM message to notify the buyer that the purchase has been
 * sucesfully completed.
 */
public class PurchaseOrdersServer extends CyclicBehaviour {
	
	private Hashtable catalogue;
	
	public PurchaseOrdersServer(Hashtable catalogue) {
		this.catalogue = catalogue;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			// ACCEPT_PROPOSAL Message received. Process it
			String title = msg.getContent();
			ACLMessage reply = msg.createReply();

			Integer price = (Integer) catalogue.remove(title);
			if (price != null) {
				reply.setPerformative(ACLMessage.INFORM);
				System.out.println(title + " sold to agent " + msg.getSender().getName());
			} else {
				// The requested book has been sold to another buyer in the meanwhile .
				reply.setPerformative(ACLMessage.FAILURE);
				reply.setContent("not-available");
			}
			myAgent.send(reply);
		} else {
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