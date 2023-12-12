/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.cpu_scheduling;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Nour eldeen
 */





public class priorityScheduling {
    private PriorityQueue<Process> pq;

    public priorityScheduling(Vector<Process> processes) {
        pq = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        pq.addAll(processes);
    }

    public void prioritySchedulingStart() {
        while (!pq.isEmpty()) {
            Process currentProcess = pq.poll();
            int start = Math.max(currentProcess.GetArrivalTime(), currentTime);
            int end = start + currentProcess.getBurstTime();
            
            currentProcess.setStartTime(start);
            currentProcess.setFinishTime(end);
            currentProcess.setTurnaroundTime(end - currentProcess.GetArrivalTime());
            currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());

            System.out.println("Process " + currentProcess.getName() + " completed from time " + start + " to " + end);

            currentTime = end;
        }
    }

    public static void main(String[] args) {
        Vector<Process> processes = new Vector<>();
        processes.add(new Process("P1", 0, 10, 3));
        processes.add(new Process("P2", 2, 5, 1));
        processes.add(new Process("P3", 5, 8, 2));

        PriorityScheduling scheduler = new PriorityScheduling(processes);
        scheduler.prioritySchedulingStart();
    }
}
    

