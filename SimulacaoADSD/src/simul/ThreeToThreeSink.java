package simul;

import eduni.simjava.*;
import java.util.Random;

public class ThreeToThreeSink extends Sim_entity {
	Sim_port in1, in2, in3, out1, out2, out3;
	double rate1, rate2, rate3;
    private double delay;
    private Random random = new Random();
    Sim_stat stat;

    public ThreeToThreeSink(String name, double delay, double rate1, double rate2, double rate3) {
      super(name);
      this.delay = delay;
      this.rate1 = rate1;
      this.rate2 = rate2;
      this.rate3 = rate3;

      in1 = new Sim_port("In1");
      in2 = new Sim_port("In2");
      in3 = new Sim_port("In3");

      out1 = new Sim_port("Out1");
      out2 = new Sim_port("Out2");
      out3 = new Sim_port("Out3");
      
      add_port(in1);
      add_port(in2);
      add_port(in3);
      add_port(out1);
      add_port(out2);
      add_port(out3);
      
      stat = new Sim_stat();
      stat.add_measure(Sim_stat.THROUGHPUT);
      stat.add_measure(Sim_stat.RESIDENCE_TIME);
      stat.add_measure(Sim_stat.SERVICE_TIME);
      stat.add_measure(Sim_stat.UTILISATION);
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
        }else if(rate1 < randomizer && randomizer <= rate2){
          sim_schedule(out2, 0.0, 1);
        }else {
          sim_schedule(out3, 0.0, 1);
        }
      }
     }
    
}
  