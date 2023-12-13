/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 *
 * @author Nour eldeen
 */



public class PriorityScheduling {
    private PriorityQueue<Process> queue;
    ArrayList<Process> finishedProcesses;
    public PriorityScheduling() {
        queue = new PriorityQueue<>(Comparator.comparingInt(Process -> Process.Priority));
        finishedProcesses = new ArrayList<Process>();
    }

    
    public void startPriorityScheduling(ArrayList<Process> processes) {
        int currentTime = 0;
        while (!processes.isEmpty()) {
            currentTime = processes.get(0).arrivalTime;
            refresh(currentTime, processes);
            while (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                int start = (currentTime);
                int agingCounter = 0;

                while (currentProcess.burstTime > 0) {
                    refresh(currentTime, processes);
                    agingCounter++;
                    if (agingCounter == 5) {
                        for (Process process : queue) {
                            process.Priority--;
                        }
                        agingCounter = 0;
                    }
                    currentTime++;
                    currentProcess.burstTime--;

                }
                int end = currentTime;
                currentProcess.createduration(start, end);
                finishedProcesses.add(currentProcess);
                currentProcess.finishTime = currentTime;
            }
        }
        for (int i = 0; i < finishedProcesses.size(); i++) {
            System.out.println(finishedProcesses.get(i).Name + " " + finishedProcesses.get(i).printDurations());
        }
    }

    public void refresh(int time,ArrayList<Process> processes) {
        List<Process> toRemove = new ArrayList<>();
        for (Process process : processes) {
            if (process.arrivalTime == time) {
                queue.add(process);
                toRemove.add(process);
            }
        }
        processes.removeAll(toRemove);
    }
}