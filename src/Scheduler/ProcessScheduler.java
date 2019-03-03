package Scheduler;

import Model.Process;

import java.util.LinkedList;

public interface ProcessScheduler {
    public LinkedList<Process> schedule(LinkedList<Process> processes);
}
