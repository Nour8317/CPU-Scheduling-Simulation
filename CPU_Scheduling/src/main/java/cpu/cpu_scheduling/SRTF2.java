package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

import static java.lang.Thread.sleep;

public class SRTF2 {
    private ArrayList<Process> Processes;
    private PriorityQueue<Process> waitingQueue = new PriorityQueue<>(Comparator.comparingInt(process -> process.BurstRemaining));
    public ArrayList<Process> finishedProcesses;
    private int currTime;
    private int Index =0 ;
    private int n;

    private int startTime;
    public SRTF2(ArrayList<Process> P)
    {
        this.Processes = P;
        n = Processes.size();
        Processes.sort(Comparator.comparingInt(Process::GetArrivalTime));
        finishedProcesses = new ArrayList<Process>();
    }

    public void  Start() throws InterruptedException {
        Process currProcessOnCPU= null;
        int ProcessOrder=1;
        String LastExecutedProcess = "";
        // Starts at the lowest first arrival time
        currTime = Processes.get(0).arrivalTime;
        startTime= currTime ;
        CheckArrivedProcesses(currTime);
        currProcessOnCPU = waitingQueue.poll();
        while(Index < n || !waitingQueue.isEmpty()) {
            if(!Objects.equals(LastExecutedProcess, currProcessOnCPU.Name))
            {// For Printing  Purposes Only
                System.out.print(currProcessOnCPU.Name+" From time: " + currTime + " to: ");
                startTime=currTime;
                LastExecutedProcess = currProcessOnCPU.Name;
            }
            if(IsBurstFinished(currProcessOnCPU))
            {
                currProcessOnCPU.createduration(startTime,currTime);
                currProcessOnCPU.Order =ProcessOrder;
                currProcessOnCPU.EndTheProcess(currTime);

                currProcessOnCPU = waitingQueue.poll();
                System.out.println(currTime);
                ProcessOrder++;
                continue;
            }
            if(CheckLowerBurstFound(currProcessOnCPU))
            {
                currProcessOnCPU.createduration(startTime,currTime);
                waitingQueue.add(currProcessOnCPU);
                currProcessOnCPU = waitingQueue.poll();
                System.out.println(currTime);
                continue;
            }
            currProcessOnCPU.BurstRemaining--;
            currTime++;
            CheckArrivedProcesses(currTime);
        }
        // Last Process May be not processed yet
        if(currProcessOnCPU.BurstRemaining!=0)
        {
            startTime = currTime;
            System.out.print(currProcessOnCPU.Name+" From time: " + currTime + " to: ");
            currTime += currProcessOnCPU.BurstRemaining;
            currProcessOnCPU.createduration(startTime,currTime);
            currProcessOnCPU.EndTheProcess(currTime);
            currProcessOnCPU.Order =ProcessOrder;
            System.out.println(currTime);
        }
        PrintFinalDetails();
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
    private void PrintFinalDetails()
    {
        double TotalWaitingTime=0;
        double TotalTurnAroundTime=0;

        for(Process p :Processes)
        {
            TotalTurnAroundTime+= p.turnAroundTime;
            TotalWaitingTime+= p.waitingTime;
            p.PrintProcessDetails();
            finishedProcesses.add(p);
        }
        System.out.println("Average Waiting Time is: " + TotalWaitingTime / n);
        System.out.println("Average TurnAround Time is: " + TotalTurnAroundTime / n);
    }
    private boolean CheckLowerBurstFound(Process p){
        Process Lowest = null;
        if(!waitingQueue.isEmpty())
        {
            Lowest = waitingQueue.peek();
        }
        if(Lowest == null || Lowest.equals(p) || p.BurstRemaining <= Lowest.BurstRemaining)
            return false;

        return true;

    }

    private boolean IsBurstFinished(Process P){
        if (P.BurstRemaining == 0) {
            return true;
        }
        return false;
    }

    public void ResetPocesses(){
        for (Process P : Processes)
            P.Reset();
    }
}
