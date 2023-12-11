package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestJobFirst {

    private ArrayList<Process>Processes;
    private int contextSwitching;
    // Priority queue based on the process's Burst, Lowest BurstTime is on the top
    private PriorityQueue<Process> waitingQueue = new PriorityQueue<>(Comparator.comparingInt(process -> process.burstTime));;
    public ShortestJobFirst(ArrayList<Process>Processes , int contextSwitching)
    {
        this.Processes = Processes;
        this.contextSwitching = contextSwitching;
        // sort the processes according to their ArrivalTime
        Processes.sort(Comparator.comparingInt(Process::GetArrivalTime));

    }


    public void  SJF(){
        Process currProcessOnCPU= null;
        Process CurrProcessInArray = null;
        int n = Processes.size();
        int ProcessOrder=1;
        // Starts at the lowest first arrival time
        int currTime = Processes.get(0).arrivalTime;
        int Index =0 ;
        while(Index < n || !waitingQueue.isEmpty())
        {
            System.out.println("AT time " + currTime);
            if(currProcessOnCPU!= null && currProcessOnCPU.burstTime == currProcessOnCPU.burstDone) // Process Finished
            {
                System.out.println("we finished Process " + currProcessOnCPU.Name + " at time " + (currTime));
                currProcessOnCPU.EndTheProcess(currTime);
                //Execution Order
                currProcessOnCPU.Order = ProcessOrder;
                ProcessOrder += 1;
                currProcessOnCPU = null;
            }
            if(Index < n)
            {
                CurrProcessInArray = Processes.get(Index);
            }

            if(currTime >= CurrProcessInArray.arrivalTime && Index < n)
            {
                System.out.println("AT time: " + currTime + " we Adeed " + CurrProcessInArray.Name);
                waitingQueue.add(CurrProcessInArray);
                Index++;
            }

            if(currProcessOnCPU == null)
            {
                System.out.println(waitingQueue.peek().Name + " arrived and executed ");
                currProcessOnCPU = waitingQueue.poll();
                if(ProcessOrder !=1)
                    currTime += contextSwitching;
            }
           if(currProcessOnCPU!= null)
           {
               currProcessOnCPU.burstDone++;
           }

            currTime++;

            System.out.println("Now Index: " + Index +" > " + n);
            if(Index >= Processes.size() && waitingQueue.isEmpty())
                break;
        }

        System.out.println("the remaining Process is " + currProcessOnCPU.Name);
//         We still have the last Process unfinished;
        currProcessOnCPU.EndTheProcess(currTime + (currProcessOnCPU.burstTime - currProcessOnCPU.burstDone));
        currProcessOnCPU.Order = Processes.size();

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




}
//  System.out.println("CurrTime is " + currTime);
//            if(Index < Processes.size() && CurrProcessInArray == null)
//            {
//                CurrProcessInArray = Processes.get(Index);
//            }
//            if(CurrProcessInArray!= null && currTime >= CurrProcessInArray.arrivalTime)
//            {
//                System.out.println("we ar going to add @ T = "+ (currTime) +"  "+ CurrProcessInArray.Name );
//                waitingQueue.add(CurrProcessInArray);
//                CurrProcessInArray = null;
//                Index++;
//            }
//            if(currProcessOnCPU == null){
//                currProcessOnCPU = waitingQueue.poll();
//                System.out.println(currProcessOnCPU.Name + " is now executed " + (currProcessOnCPU.burstTime - currProcessOnCPU.burstDone));
//
//            }
//
////            System.out.println("CPU has now: " + currProcessOnCPU.Name);
//            currProcessOnCPU.burstDone++;
//            if(currProcessOnCPU.burstTime == currProcessOnCPU.burstDone)//Process is Finished
//            {
////                System.out.println("we finished Process " + currProcessOnCPU.Name + " at time " + (currTime+1));
//                currProcessOnCPU.EndTheProcess(currTime+1);
//                //Execution Order
//                currProcessOnCPU.Order = ProcessOrder++;
//                System.out.println(waitingQueue.peek().Name + " --> " + waitingQueue.peek().burstTime + " is on the front");
//                currProcessOnCPU = null;
//            }
//            currTime++;