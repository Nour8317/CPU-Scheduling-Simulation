/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 *
 * @author Nour eldeen
 */
public class SRTF {
    PriorityQueue<Process> pq;
    ArrayList<Process> finishedProcesses;
    public SRTF() {
        pq = new PriorityQueue<Process>(Comparator.comparingInt(process -> process.burstTime));
        finishedProcesses = new ArrayList<Process>();
    }

    public void refresh(int currTime, ArrayList<Process> processes) {
        if (processes.isEmpty()) {
            return;
        }
        for (int i = 0; i < processes.size(); i++) {
            if(processes.get(i).arrivalTime<=currTime){
                pq.add(processes.get(i));
                processes.remove(i);
            }
        }
    }
    public void startSRTF(ArrayList<Process> processes) {
        int currTime = 0;
        int n = 0;
        while (n != processes.size()) {
            refresh(currTime, processes);
            if (pq.isEmpty()) {
                currTime++;
                break;
            }
            int start = currTime;
            while (!pq.isEmpty()) {

                Process currProcess = pq.poll();
                if (!pq.isEmpty() && pq.peek().burstTime < currProcess.burstTime) {
                    currProcess.createduration(start, currTime);
                    start = currTime;
                    pq.add(currProcess);
                    currProcess = pq.poll();
                }
                currTime++;
                currProcess.burstDone++;
                if (currProcess.burstDone != currProcess.burstTime) {
                    pq.add(currProcess);
                } else {
                    n++;
                    finishedProcesses.add(currProcess);
                    currProcess.finishTime = currTime;
                    currProcess.createduration(start, currTime);
                    start = currTime;
                }
            }

        }
        for (int i = 0; i < finishedProcesses.size(); i++) {
            System.out.println(finishedProcesses.get(i).Name + " " + finishedProcesses.get(i).printDurations());
        }
    }
    
}
