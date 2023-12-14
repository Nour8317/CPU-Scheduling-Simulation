package cpu.cpu_scheduling;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.lang.Math.ceil;
import static java.lang.Thread.sleep;

public class AGSchedule {
    private List<Process> processes;
    private List<Process> waitingQueue = new ArrayList<>();
    private int currTime;
    private int index;
    private int n;

    private int startTime;


    // used for final outputs
    private ArrayList<Integer> TimeForProcesses;
    private ArrayList<Process> ProcessesOrder;

    private ArrayList<ArrayList<String>> FinalResult;

    private int AGFactorGeneration() {
        Random random = new Random();
        return random.nextInt(21);
    }

    public AGSchedule(List<Process> processes) {
        TimeForProcesses=new ArrayList<>();
        ProcessesOrder = new ArrayList<>();
        FinalResult= new ArrayList<>();
        this.processes = processes;
        processes.sort(Comparator.comparingInt(Process::GetArrivalTime));
//        for (Process P : processes)
//            P.SetAGFactor(AGFactorGeneration());
        n = processes.size();
    }

    public void AGStart() throws InterruptedException {
        Process currProcessOnCPU;
        currTime = processes.get(0).arrivalTime;
        CheckArrivedProcesses(currTime);
        currProcessOnCPU = FirstItemAtQueue();
        while (!waitingQueue.isEmpty() || index < n) {
            if (!waitingQueue.isEmpty() && waitingQueue.get(0) == currProcessOnCPU) {
                // When currProcessOnCPU is the first process on the queue . Remove it from the queue
                waitingQueue.remove(0);
            }
            if(IsBurstFinished(currProcessOnCPU))
            {
                currProcessOnCPU = FirstItemAtQueue();
                continue;
            }
            System.out.println("Curr Time is: " + currTime);
            TimeForProcesses.add(currTime);
            ProcessesOrder.add(currProcessOnCPU );
            PrintQuantumList();
            CheckArrivedProcesses(currTime);
            System.out.println("currProcess is: " + currProcessOnCPU.Name);

            if (!ExecuteProcess(currProcessOnCPU)) {
                // It means that we need to move one second by one, then check  whether there is interrupton or quantum is finished;
                while (true) {
                    if (IsProcessInterrupted(currProcessOnCPU)) {
                        currProcessOnCPU = FindMinAGFactor(currProcessOnCPU);
                        break;
                    }

                    currTime++;
                    CheckArrivedProcesses(currTime);
                    currProcessOnCPU.RemainingQuantum -= 1;
                    currProcessOnCPU.burstDone += 1;

                    if (IsBurstFinished(currProcessOnCPU)) {
                        currProcessOnCPU = FirstItemAtQueue();
                        break;
                    } else {
                        if (IsQuantumFinished(currProcessOnCPU)) {
                            currProcessOnCPU = FirstItemAtQueue();
                            break;
                        }
                    }
                }
            } else {
                currProcessOnCPU = FirstItemAtQueue();
            }
        }
        // Last Process is not Finished yet
        PrintQuantumList();
        System.out.println("currProcess is: " + currProcessOnCPU.Name);
        TimeForProcesses.add(currTime);


        currTime+= currProcessOnCPU.burstTime - currProcessOnCPU.burstDone;
        TimeForProcesses.add(currTime);
        ProcessesOrder.add(currProcessOnCPU);
        currProcessOnCPU.Quantum = 0;
        PrintQuantumList();
        System.out.println("Last completion Time is " + currTime);
        PrintFinalResult();
        for (Process P : processes)
            System.out.println( P.Name +" " +P.printDurations());
    }

    private void CheckArrivedProcesses(int time) {
        if (index >= n)
            return;
        Process currProcessInArray = processes.get(index);

        while (time >= currProcessInArray.arrivalTime) {
            waitingQueue.add(currProcessInArray);
            index++;
            if (index == n)
                break;
            currProcessInArray = processes.get(index);
        }
    }
    private boolean IsBurstFinished(Process P){
        if (P.burstDone == P.burstTime) {
            P.Quantum = 0;
            return true;
        }
        return false;
    }
    private boolean IsQuantumFinished(Process P)
    {
        if (P.RemainingQuantum == 0) {
//                            System.out.println("**Process: " + currProcessOnCPU.Name + " is added to the queue");
//                            System.out.println("Befor Adding Quantum was:" + currProcessOnCPU.Quantum);
            waitingQueue.add(P);
            P.Quantum += (int) Math.ceil(0.1 * QuantumMean());
            P.RemainingQuantum = P.Quantum;
//                            System.out.println("WE have added Mean * 0.1: " + (int) Math.ceil(0.1 * QuantumMean()));
            return true;
        }
        return false;
    }
    private boolean IsProcessInterrupted(Process P)
    {
        if ((!waitingQueue.isEmpty() && FindMinAGFactor(P).AGFactor < P.AGFactor && index < n)) {
            P.Quantum += P.RemainingQuantum;
            P.RemainingQuantum = P.Quantum;
            waitingQueue.add(P);
            return true;
        }
        return false;
    }

    private boolean ExecuteProcess(Process P) {
        // Process is already finished
        if(IsBurstFinished(P))
            return true;

        int CeilOfQuantum = (int) ceil(P.Quantum / 2.0);

        if (P.burstTime - P.burstDone < CeilOfQuantum) {
            currTime += P.burstTime - P.burstDone;
            P.Quantum = 0;
            P.burstDone = P.burstTime;
            return true;
        }

        P.RemainingQuantum -= CeilOfQuantum;
        P.burstDone += CeilOfQuantum;
        currTime += CeilOfQuantum;
        CheckArrivedProcesses(currTime);

        if (P.RemainingQuantum < 0) {
            P.RemainingQuantum = 0;
        }

        if(IsBurstFinished(P))
            return true;
        else {
            if (IsQuantumFinished(P)) {
                return true;
            }
        }
        return false;
    }

    private int QuantumMean() {
        int mean = 0;
        for (Process P : processes)
            mean += P.Quantum;
        return (mean / n);
    }

    private void PrintQuantumList() {
        for (Process P : processes)
            System.out.print(P.Quantum + " ");
    }

    private Process FindMinAGFactor(Process CurrentProcessOnCPU) {
        Process min = new Process("Dymmu", "Dummy", 0, 0, 0, 0, 0);
        min.AGFactor = 9999999;
        for (Process P : waitingQueue) {
            if (CurrentProcessOnCPU != null && CurrentProcessOnCPU.equals(P))
                continue;
            if (min.AGFactor > P.AGFactor)
                min = P;
        }
//        System.out.println("the min is: " + min.Name);
        return min;
    }



    private Process FirstItemAtQueue() {
        Process p = waitingQueue.get(0);
        waitingQueue.remove(p);
        return p;
    }


    private void PrintFinalResult(){
        for (int i = 0; i < ProcessesOrder.size(); i++) {
            System.out.print(ProcessesOrder.get(i).Name + " -->");
            System.out.println(TimeForProcesses.get(i) + "  " + TimeForProcesses.get(i+1));
            ProcessesOrder.get(i).createduration(TimeForProcesses.get(i),TimeForProcesses.get(i+1));
        }
    }

    public void ResetPocesses(){
        for (Process p : processes)
        {
            p.Reset();
        }
    }





}
