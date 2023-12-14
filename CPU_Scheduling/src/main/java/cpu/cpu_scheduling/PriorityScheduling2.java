package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Thread.sleep;

public class PriorityScheduling2 {
    private ArrayList<Process> Processes;
    private PriorityQueue<Process> waitingQueue ;
    private int currTime;
    private int Index =0 ;
    int ProcessOrder=1;
    private int n;
    public PriorityScheduling2(ArrayList<Process> P)
    {
        this.Processes = P;
        n = Processes.size();
        this.waitingQueue = new PriorityQueue<>(Comparator.comparingInt(process -> process.Priority));
        Processes.sort(Comparator.comparingInt(Process::GetArrivalTime));
    }

    public void  Start() throws InterruptedException {
        Process currProcessOnCPU= null;
        // Starts at the lowest first arrival time
        currTime = Processes.get(0).arrivalTime;
        CheckArrivedProcesses(currTime);
        while(Index < n || !waitingQueue.isEmpty()) {
            StarvationSolver(currTime);
            currProcessOnCPU = waitingQueue.poll();
            System.out.print(currProcessOnCPU.Name+" From time: " + currTime + " to: ");
            ExecuteProcess(currProcessOnCPU);
            System.out.println(currTime);
        }
        PrintFinalDetails();
    }
    private void ExecuteProcess(Process P){
//        System.out.println("Process: " + P.Name+ " Is being Processed");
        currTime+= P.burstTime;
        P.EndTheProcess(currTime);
        P.Order = ProcessOrder;
        ProcessOrder++;
        CheckArrivedProcesses(currTime);
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
            p.burstDone = 0;
        }
        System.out.println("Average Waiting Time is: " + TotalWaitingTime / n);
        System.out.println("Average TurnAround Time is: " + TotalTurnAroundTime / n);
    }

    private void StarvationSolver(int currTime){
        // loop over the found processes in the waiting queue
        // if process found to be waited more than 10 unit time
        // increase its priority
        for (Process P : waitingQueue)
        {
            if(currTime - P.arrivalTime >=10)
            {
                P.Priority--;
            }
        }
    }

}
