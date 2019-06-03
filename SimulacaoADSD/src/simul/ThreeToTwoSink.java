package simul;

import java.util.Random;

import eduni.simjava.*;

public class ThreeToTwoSink extends Sim_entity{
	Sim_port in1, in2, in3, out1, out2;
	double rate1, rate2;
    private double delay;
    private Random random = new Random();
    Sim_stat stat;

    ThreeToTwoSink(String name, double delay, double rate1, double rate2) {
        super(name);
        this.delay = delay;
        this.rate1 = rate1;
        this.rate2 = rate2;


        in1 = new Sim_port("In1");
        in2 = new Sim_port("In2");
        in3 = new Sim_port("In3");

        out1 = new Sim_port("Out1");
        out2 = new Sim_port("Out2");

        
        add_port(in1);
        add_port(in2);
        add_port(in3);
        add_port(out1);
        add_port(out2);
        
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
            
          }else{
            sim_schedule(out2, 0.0, 1);
          }
        }
       }
    
    
    
}
