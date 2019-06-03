package simul;

import java.util.Random;

import eduni.simjava.*;

public class LoadBalancer extends Sim_entity{

    private Sim_port in, out1, out2;
    private double delay;
    private int rate1 = 40;
    private int rate2 = 60;
    private Random random = new Random();
	private Sim_stat stat;

    public LoadBalancer(String name, double delay) {
      super(name);
      
      this.delay = delay;
      
      in = new Sim_port("In");
      out1 = new Sim_port("Out1");
      out2 = new Sim_port("Out2");
      
      add_port(in);
      add_port(out1);
      add_port(out2);
      
      stat = new Sim_stat();
      stat.add_measure(Sim_stat.THROUGHPUT);
      stat.add_measure(Sim_stat.RESIDENCE_TIME);
      stat.add_measure(Sim_stat.SERVICE_TIME);
      stat.add_measure(Sim_stat.UTILISATION);
      stat.add_measure("Thread use", Sim_stat.STATE_BASED, false);
      set_stat(stat);
    }

    public void body() {
    	
      while (Sim_system.running()) {
      	int randomizer = random.nextInt(100);
      	
        Sim_event e = new Sim_event();
        sim_get_next(e);
        sim_process(delay);
        sim_completed(e);
        
        if (randomizer <= rate1) {
          sim_schedule(out1, 0.0, 1);
        } else {
          sim_schedule(out2, 0.0, 1);
        }

      }
    }
	
}
