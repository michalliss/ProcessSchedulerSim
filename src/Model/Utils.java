package Model;

import javax.sound.sampled.Line;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Utils {
    public static double srCzasOczekiwania(LinkedList<Process> processes) {
        double wynik = 0;
        for (Process p : processes) {
            wynik += (double) p.getCzasOczek() / (double) processes.size();
        }
        return wynik;
    }

    public static LinkedList<Process> load() {
        LinkedList<Process> processes = new LinkedList<>();
        try (Scanner reader = new Scanner(new File("processes.csv"))) {
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                processes.add(Process.parse(line));
            }
        } catch (Exception e) {
            System.out.println("błąd odczytu");
        }
        return processes;
    }

    public static void sortMoment(LinkedList<Process> processes) {
        processes.sort(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if (o1.getMomZglosz() == o2.getMomZglosz()) {
                    return 0;
                } else if (o1.getMomZglosz() > o2.getMomZglosz()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public static void sortDlFazy(LinkedList<Process> processes) {
        processes.sort(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if (o1.getPozostalyCzas() == o2.getPozostalyCzas()) {
                    return 0;
                } else if (o1.getPozostalyCzas() > o2.getPozostalyCzas()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public static void fillWaitingTimes(LinkedList<Process> processes, LinkedList<Process> ticks){
        for(Process p:processes){
            int wtime = ticks.lastIndexOf(p) + 1 - p.getMomZglosz() -p.getDlFazy();
            p.setCzasOczek(wtime);
        }
    }

    public static double getAverageWaitingTime(LinkedList<Process> processes){
        double wynik = 0;
        for(Process p: processes){
            wynik += (double)p.getCzasOczek()/(double)processes.size();
        }
        return wynik;
    }

}
