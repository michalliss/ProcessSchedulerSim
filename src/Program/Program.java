package Program;

import Model.Process;
import Model.Utils;
import Scheduler.*;

import java.util.LinkedList;

public class Program {
    public void run() {

        Process p1 = new Process("1", 20, 2);
        Process p2 = new Process("2", 1, 3);
        Process p3 = new Process("3", 3, 3);
        Process p4 = new Process("4", 34, 3);
        Process p5 = new Process("5", 12, 3);

        /*
        LinkedList<Process> processes = new LinkedList<>();
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
*/

        LinkedList<Process> processes = new LinkedList<>();
        LinkedList<Process> scheduledProcesses = new LinkedList<>();
        processes = Utils.load();



        ProcessScheduler processScheduler = new FCFS();
        scheduledProcesses = processScheduler.schedule(processes);
        Utils.fillWaitingTimes(processes, scheduledProcesses);
        System.out.println("Średni czas FCFS: " + Utils.getAverageWaitingTime(processes));
        System.out.println("Kolejne kroki zegara: " + scheduledProcesses);
        System.out.println();

        processes = Utils.load();
        processScheduler = new SJFn();
        scheduledProcesses = processScheduler.schedule(processes);
        Utils.fillWaitingTimes(processes, scheduledProcesses);
        System.out.println("Średni czas SJF (bez wywlaszczenia): " + Utils.getAverageWaitingTime(processes));
        System.out.println("Kolejne kroki zegara: " + scheduledProcesses);
        System.out.println();


        processes = Utils.load();
        processScheduler = new SJFpreempt();
        scheduledProcesses = processScheduler.schedule(processes);
        Utils.fillWaitingTimes(processes, scheduledProcesses);
        System.out.println("Średni czas SJF (z wywlaszczeniem): " + Utils.getAverageWaitingTime(processes));
        System.out.println("Kolejne kroki zegara: " + scheduledProcesses);
        System.out.println();

        processes = Utils.load();
        processScheduler = new Rot(3);
        scheduledProcesses = processScheduler.schedule(processes);
        Utils.fillWaitingTimes(processes, scheduledProcesses);
        System.out.println("Średni czas rotacyjnego: " + Utils.getAverageWaitingTime(processes));
        System.out.println("Kolejne kroki zegara: " + scheduledProcesses);
        System.out.println();

        for(Process p:processes){
            System.out.print(p.getId() + ":" + p.getCzasOczek() + ", ");
        }





    }
}
