package simul;

import eduni.simjava.*;

public class ProcessorSubsystem {
  public static void main(String[] args) {

    Sim_system.initialise();

    // 300 requisiçoes
    Source source = new Source("Source", 10, 300);
    
    Firewall firewall = new Firewall("Firewall", 30);

    Disk disk1 = new Disk("Disk1", 10);
    Disk disk2 = new Disk("Disk2", 12);
    Disk disk3 = new Disk("Disk3", 14);
    Disk disk4 = new Disk("Disk4", 15);
    
    LoadBalancer loadBalancer = new LoadBalancer("LoadBalancer", 40);
    
    // taxas em ordem crescente
    ThreeToTwoSink cpu1 = new ThreeToTwoSink("Cpu1", 10, 5, 95); 
    ThreeToThreeSink cpu2 = new ThreeToThreeSink("Cpu2", 5, 3, 10, 87); 
    TwoToTwoSink cpu3 = new TwoToTwoSink("Cpu3", 7, 10, 90);
    ThreeToThreeSink cpu4 = new ThreeToThreeSink("Cpu4", 9, 3, 7, 90);
    
    Finalizer finalizer = new Finalizer("Finalizer", 0);
    
    Sim_system.link_ports("Source", "Out", "Firewall", "In");
    
    Sim_system.link_ports("Firewall", "Out", "Cpu1", "In1");
    
    Sim_system.link_ports("Cpu1", "Out1", "Disk1", "In");
    Sim_system.link_ports("Cpu1", "Out2", "LoadBalancer", "In");
    
    Sim_system.link_ports("Disk1", "Out", "Cpu1", "In2");
    
    Sim_system.link_ports("LoadBalancer", "Out1", "Cpu2", "In1");
    Sim_system.link_ports("LoadBalancer", "Out2", "Cpu3", "In1");
    
    Sim_system.link_ports("Cpu2", "Out1", "Disk2", "In");
    Sim_system.link_ports("Cpu2", "Out2", "Cpu1", "In3");
    Sim_system.link_ports("Cpu2", "Out3", "Cpu4", "In1");
    
    Sim_system.link_ports("Disk2", "Out", "Cpu2", "In2");
    
    Sim_system.link_ports("Cpu3", "Out1", "Disk3", "In");
    Sim_system.link_ports("Cpu3", "Out2", "Cpu4", "In2");

    Sim_system.link_ports("Disk3", "Out", "Cpu3", "In2");
    
    Sim_system.link_ports("Cpu4", "Out1", "Disk4", "In");
    Sim_system.link_ports("Cpu4", "Out2", "Cpu2", "In3");
    Sim_system.link_ports("Cpu4", "Out3", "Finalizer", "In");

    
    Sim_system.link_ports("Disk4", "Out", "Cpu4", "In3");

    Sim_system.run();
  }
}
