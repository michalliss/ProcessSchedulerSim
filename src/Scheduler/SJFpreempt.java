package Scheduler;

import Model.Process;
import Model.Utils;

import java.util.Iterator;
import java.util.LinkedList;

public class SJFpreempt implements ProcessScheduler {

    private int time;
    LinkedList<Process> ticks;
    private LinkedList<Process> queue;
    private LinkedList<Process> known;

    public SJFpreempt() {
        this.time = 0;
        this.ticks = new LinkedList<>();
        this.queue = new LinkedList<>();
        this.known = new LinkedList<>();
    }

    @Override
    public LinkedList<Process> schedule(LinkedList<Process> processes) {

        queue.addAll(processes);
        Utils.sortMoment(queue);
        addKnown();

        while (!queue.isEmpty() || !known.isEmpty()) {
            if (known.isEmpty()) {
                ticks.add(null);
                time++;
            } else {
                manageProcess(known.getFirst());
                time++;
            }
            addKnown();
        }
        return ticks;

    }

    private void addKnown() {
        Iterator<Process> iterator = queue.iterator();

        while (iterator.hasNext()) {
            Process p = iterator.next();
            if (p.getMomZglosz() <= time) {
                known.add(p);
                iterator.remove();
            }
        }
        Utils.sortDlFazy(known);
    }

    private void manageProcess(Process p) {
        ticks.add(p);
        p.setPozostalyCzas(p.getPozostalyCzas() - 1);
        if (p.getPozostalyCzas() == 0) {
            known.remove(p);
        }

    }


}
