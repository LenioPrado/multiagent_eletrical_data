package agents.phd.unifei.policethief;

import jade.core.Agent;

public class ThiefAgent extends Agent{
    
	@Override
    public void setup()
    {
        System.out.println("Thief agent name is: " +getAID().getName());
    }
}