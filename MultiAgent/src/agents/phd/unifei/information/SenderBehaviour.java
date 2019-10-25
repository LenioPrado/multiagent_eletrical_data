package agents.phd.unifei.information;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class SenderBehaviour extends Behaviour {

	private static final long serialVersionUID = 75319841653145795L;

	private String msgContent;
	private AID[] receivers;

	public SenderBehaviour(AID[] receivers, String messageContent) {
		this.receivers = receivers;
		msgContent = messageContent;
	}

	@Override
	public void action() {
		if (receivers != null && !msgContent.isEmpty()) {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			for (AID aid : receivers) {
				msg.addReceiver(aid);
			}
			msg.setPerformative(ACLMessage.AGREE);
			msg.setContent(msgContent);
			msg.setConversationId("electrical-data");
			myAgent.send(msg);
		}
		block();
	}

	@Override
	public boolean done() {
		return true;
	}
}
