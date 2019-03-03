package Scheduler;

import Model.Process;
import Model.Utils;

import java.util.Iterator;
import java.util.LinkedList;

public class Rot implements ProcessScheduler {

    private int ticksize;
    private int time;
    private LinkedList<Process> ticks;
    private LinkedList<Process> queue;
    private LinkedList<Process> ready;

    public Rot(int ticksize) {
        this.ticksize = ticksize;
        time = 0;
        ticks = new LinkedList<>();
        queue = new LinkedList<>();
        ready = new LinkedList<>();
    }

    @Override
    public LinkedList<Process> schedule(LinkedList<Process> processes) {

        queue.addAll(processes);
        Utils.sortMoment(queue);
        addReady();
        while (!ready.isEmpty() || !queue.isEmpty()) {
            if (ready.isEmpty()) {
                ticks.add(null);
                time++;
            } else {
                manageProcess(ready.removeFirst());
            }
            addReady();
        }
        return ticks;

    }

    private void addReady() {
        Iterator<Process> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Process p = iterator.next();
            if (p.getMomZglosz() <= time) {
                ready.add(p);
                iterator.remove();
            }
        }
    }

    private void manageProcess(Process p) {
        if (p.getPozostalyCzas() - ticksize <= 0) {
            for (int i = 0; i < p.getPozostalyCzas(); i++) {
                ticks.add(p);
                time++;
            }
            p.setPozostalyCzas(0);
        } else {
            p.setPozostalyCzas(p.getPozostalyCzas() - ticksize);
            for (int i = 0; i < ticksize; i++) {
                ticks.add(p);
                time++;
            }
            addReady();
            ready.add(p);
        }

    }


}
