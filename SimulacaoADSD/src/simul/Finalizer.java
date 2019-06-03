package simul;

import eduni.simjava.*;
public class Finalizer extends Sim_entity{
	
	Sim_port in;
    private double delay;
    Sim_stat stat;

    Finalizer(String name, double delay) {
        super(name);
        this.delay = delay;

        in = new Sim_port("In");

        add_port(in);
        
      }

      public void body() {

        while (Sim_system.running()) {
          Sim_event e = new Sim_event();
          sim_get_next(e);
          sim_process(delay);
          sim_completed(e);
          }
        }
       
    

}
