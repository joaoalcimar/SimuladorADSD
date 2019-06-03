package simul;

import java.util.Random;

import eduni.simjava.*;

public class Firewall extends Sim_entity{
    private Sim_port out, in;
    private double delay;
    private int rate = 70;
    private Random random = new Random();
	private Sim_stat stat;

    public Firewall(String name, double delay) {
      super(name);
      this.delay = delay;

      out = new Sim_port("Out");
      in = new Sim_port("In");
      
      add_port(out);
      add_port(in);
      stat = new Sim_stat();
      stat.add_measure(Sim_stat.THROUGHPUT);
      stat.add_measure(Sim_stat.RESIDENCE_TIME);
      stat.add_measure(Sim_stat.SERVICE_TIME);
      stat.add_measure(Sim_stat.UTILISATION);
      set_stat(stat);
    }

    public void body() {
    	while (Sim_system.running()) {
        Sim_event e = new Sim_event();
        sim_get_next(e);
        sim_process(delay);
        sim_completed(e);
        int randomizer = random.nextInt(100);
        if (randomizer <= rate) {
            sim_schedule(out, 0.0, 1);
        }else{

        }

     
        sim_pause(delay);
    	}
    }
    
    

}
