package Scheduler;

import Model.Process;
import Model.Utils;

import java.util.Iterator;
import java.util.LinkedList;


public class FCFS implements ProcessScheduler {

    private int time;
    private LinkedList<Process> ticks;

    public FCFS() {
        this.time = 0;
        this.ticks = new LinkedList<>();
    }

    @Override
    public LinkedList<Process> schedule(LinkedList<Process> processes) {

        Utils.sortMoment(processes);

        Iterator<Process> iterator = processes.iterator();

        while (iterator.hasNext()) {
            if (processes.getFirst().getMomZglosz() <= time) {
                manageProcess(iterator.next());
            } else {
                time++;
                ticks.add(null);
            }
        }


        return ticks;
    }

    private void manageProcess(Process p) {
        p.setCzasOczek(this.time - p.getMomZglosz());
        for (int i = 0; i < p.getDlFazy(); i++) {
            ticks.add(p);
        }
        this.time += p.getDlFazy();
    }
}