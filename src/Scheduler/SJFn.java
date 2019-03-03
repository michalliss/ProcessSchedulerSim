package Scheduler;

import Model.Process;
import Model.Utils;

import java.util.Iterator;
import java.util.LinkedList;

public class SJFn implements ProcessScheduler {

    private int time;
    LinkedList<Process> ticks;
    private LinkedList<Process> queue;
    private LinkedList<Process> known;

    public SJFn() {
        this.time = 0;
        ticks = new LinkedList<>();
        this.known = new LinkedList<>();
        this.queue = new LinkedList<>();
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
                manageProcess(known.removeFirst());
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
        for (int i = 0; i < p.getDlFazy(); i++) {
            ticks.add(p);
        }
        time += p.getDlFazy();
    }

}

