package simul;

import eduni.simjava.*;

// The class for the two disks
public class Disk extends Sim_entity {
  private Sim_port in, out;
  private double delay;
  private Sim_stat stat;

  public Disk(String name, double delay) {
    super(name);
    this.delay = delay;
    
    in = new Sim_port("In");
    out = new Sim_port("Out");
    
    add_port(in);
    add_port(out);
    
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
    }
  }
}
