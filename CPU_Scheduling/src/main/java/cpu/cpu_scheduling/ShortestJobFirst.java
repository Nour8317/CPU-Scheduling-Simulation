package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestJobFirst {

    private int contextSwitching;
    private ArrayList<Process>Processes;
    public ArrayList<Process>finishedProcesses;
    private int n ;
    private int Index =0 ;
    private int currTime;

    private int startTime;
    private int ProcessOrder = 1;
    // Priority queue based on the process's Burst, Lowest BurstTime is on the top
    private PriorityQueue<Process> waitingQueue ;
    public ShortestJobFirst(ArrayList<Process>Processes , int contextSwitching)
    {
        this.Processes = Processes;
        this.contextSwitching = contextSwitching;
        // sort the processes according to their ArrivalTime
        n = Processes.size();
        this.waitingQueue = new PriorityQueue<>(Comparator.comparingInt(process -> process.burstTime));
        Processes.sort(Comparator.comparingInt(Process::GetArrivalTime));
        finishedProcesses = new ArrayList<Process>();

    }

    public void  SJF(){
        Process currProcessOnCPU= null;
        // Starts at the lowest first arrival time
        currTime = Processes.get(0).arrivalTime;
        startTime = currTime;
        CheckArrivedProcesses(currTime);
        while(Index < n || !waitingQueue.isEmpty())
        {
            currProcessOnCPU = waitingQueue.poll();
            System.out.print(currProcessOnCPU.Name+" From time: " + currTime + " to: ");
            ExecuteProcess(currProcessOnCPU);
            System.out.println(currTime);
        }
        PrintFinalDetails();
    }
    private void ExecuteProcess(Process P){
//        System.out.println("Process: " + P.Name+ " Is being Processed");
        currTime+= P.burstTime+contextSwitching;
        P.EndTheProcess(currTime);
        P.Order = ProcessOrder;
        ProcessOrder++;
        P.createduration(startTime , currTime-contextSwitching);
        startTime = currTime;
        CheckArrivedProcesses(currTime);
        finishedProcesses.add(P);
    }
    private void PrintFinalDetails()
    {
        double TotalWaitingTime=0;
        double TotalTurnAroundTime=0;

        for(Process p :Processes)
        {
            TotalTurnAroundTime+= p.turnAroundTime+contextSwitching;
            TotalWaitingTime+= p.waitingTime;
            
            p.PrintProcessDetails();
            p.burstDone = 0;
        }
        
        
        System.out.println("Average Waiting Time is: " + TotalWaitingTime / n);
        System.out.println("Average TurnAround Time is: " + TotalTurnAroundTime / n);
    }

    private void CheckArrivedProcesses(int time) {
        if (Index >= n)
            return;
        Process currProcessInArray = Processes.get(Index);

        while (time >= currProcessInArray.arrivalTime) {
//            System.out.println("AT time: " + currTime + " we Added " + currProcessInArray.Name);
            waitingQueue.add(currProcessInArray);
            Index++;
            if (Index == n)
                break;
            currProcessInArray = Processes.get(Index);
        }
    }
    public void ResetProcesses(){
        for (Process p : Processes)
        {
            p.Reset();
        }
    }
}