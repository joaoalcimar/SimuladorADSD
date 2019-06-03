package simul;

import eduni.simjava.*;

class Source extends Sim_entity {
    private Sim_port out;
    private double delay;
    private int numProcesses;

    Source(String name, double delay, int numProcesses) {
      super(name);
      this.delay = delay;
      this.numProcesses = numProcesses;

      out = new Sim_port("Out");
      add_port(out);
    }

    @Override
    public void body() {
    
      for (int i=0; i < numProcesses; i++) {

        sim_schedule(out, 0.0, 1);

        sim_pause(delay);
      }
    }
  }