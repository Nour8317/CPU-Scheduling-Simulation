package cpu.cpu_scheduling;

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

    private int AGFactorGeneration() {
        Random random = new Random();
        return random.nextInt(21);
    }

    public AGSchedule(List<Process> processes) {
        this.processes = processes;
        processes.sort(Comparator.comparingInt(Process::GetArrivalTime));
        n = processes.size();
    }

    public void AGStart() throws InterruptedException {
        Process currProcessOnCPU;
        currTime = processes.get(0).arrivalTime;
        CheckArrivedProcesses(currTime);
        currProcessOnCPU = FirstItemAtQueue();

        while (!waitingQueue.isEmpty() || index < n) {
            if (!waitingQueue.isEmpty() && waitingQueue.get(0) == currProcessOnCPU) {
                waitingQueue.remove(0);
            }
//            System.out.println("Curr Time is: " + currTime);
            PrintQuantumList();
            CheckArrivedProcesses(currTime);
            System.out.println("currProcess is: " + currProcessOnCPU.Name);

            if (!ExecuteProcess(currProcessOnCPU)) {
//                System.out.println("Returned False ");
                while (true) {
                    if ((!waitingQueue.isEmpty() && FindMinAGFactor(currProcessOnCPU).AGFactor < currProcessOnCPU.AGFactor && index < n)) {
                        currProcessOnCPU.Quantum += currProcessOnCPU.RemainingQuantum;
                        currProcessOnCPU.RemainingQuantum = currProcessOnCPU.Quantum;
//                        System.out.println("I have Added " + currProcessOnCPU.Name);
                        waitingQueue.add(currProcessOnCPU);
                        currProcessOnCPU = FindMinAGFactor(currProcessOnCPU);
//                        System.out.println("Current Process " + currProcessOnCPU.Name + " will be executed 3afya lowe FActor");
//                        Tarteb();
                        break;
                    }

                    currTime++;
//                    System.out.println("currTime: " + currTime);
                    CheckArrivedProcesses(currTime);
                    currProcessOnCPU.RemainingQuantum -= 1;
                    currProcessOnCPU.burstDone += 1;

                    if (currProcessOnCPU.burstDone == currProcessOnCPU.burstTime) {
                        currProcessOnCPU.Quantum = 0;
                        currProcessOnCPU = FirstItemAtQueue();
                        break;
                    } else {
                        if (currProcessOnCPU.RemainingQuantum == 0) {
//                            System.out.println("**Process: " + currProcessOnCPU.Name + " is added to the queue");
//                            System.out.println("Befor Adding Quantum was:" + currProcessOnCPU.Quantum);
                            waitingQueue.add(currProcessOnCPU);
                            currProcessOnCPU.Quantum += (int) Math.ceil(0.1 * QuantumMean());
                            currProcessOnCPU.RemainingQuantum = currProcessOnCPU.Quantum;
//                            System.out.println("WE have added Mean * 0.1: " + (int) Math.ceil(0.1 * QuantumMean()));
//                            System.out.println("**WE will take first item at the queue");
                            currProcessOnCPU = FirstItemAtQueue();
                            break;
                        }
                    }
//                    sleep(2000);
                }
            } else {
//                System.out.println("returned True");
                currProcessOnCPU = FirstItemAtQueue();
            }
//            Tarteb();
//            sleep(2000);
        }
        currTime+= currProcessOnCPU.burstTime - currProcessOnCPU.burstDone;
        currProcessOnCPU.Quantum = 0;
        PrintQuantumList();
        System.out.println("Last completion Time is " + currTime);

    }

    private void CheckArrivedProcesses(int time) {
        if (index >= n)
            return;
        Process currProcessInArray = processes.get(index);

        while (time >= currProcessInArray.arrivalTime) {
//            System.out.println("AT time: " + currTime + " we Added " + currProcessInArray.Name);
            waitingQueue.add(currProcessInArray);
            index++;
            if (index == n)
                break;
            currProcessInArray = processes.get(index);
        }
    }

    private boolean ExecuteProcess(Process P) {
        if (P.burstDone == P.burstTime) {
            P.Quantum = 0;
            return true;
        }

        int CeilOfQuantum = (int) ceil(P.Quantum / 2.0);
//        System.out.println("Half Quantum: " + CeilOfQuantum);

        if (P.burstTime - P.burstDone < CeilOfQuantum) {
//            System.out.println("We don't need all of the ceilOFQuantum");
            currTime += P.burstTime - P.burstDone;
            P.Quantum = 0;
            P.burstDone = P.burstTime;
            return true;
        }

//        System.out.println("remaining: " + P.RemainingQuantum);
        P.RemainingQuantum -= CeilOfQuantum;
        P.burstDone += CeilOfQuantum;
        currTime += CeilOfQuantum;
        CheckArrivedProcesses(currTime);

        if (P.RemainingQuantum < 0) {
            P.RemainingQuantum = 0;
        }

        if (P.burstDone == P.burstTime) {
            P.Quantum = 0;
            return true;
        } else {
            if (P.RemainingQuantum == 0) {
//                System.out.println("Process: " + P.Name + " is added to the queue");
//                System.out.println("WE have added Mean * 0.1: " + 0.1 * QuantumMean());
                waitingQueue.add(P);
                P.Quantum += 0.1 * QuantumMean();
                P.RemainingQuantum = P.Quantum;
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

    private Process FindandRemoveMinAGFactor(Process CurrentProcessOnCPU) {
        Process min = new Process("Dymmu", "Dummy", 0, 0, 0, 0, 0);
        min.AGFactor = 9999999;
        for (Process P : waitingQueue) {
            if (CurrentProcessOnCPU != null && CurrentProcessOnCPU.equals(P))
                continue;
            if (min.AGFactor > P.AGFactor)
                min = P;
        }
//        System.out.println("we have returned min: " + min.Name);
        waitingQueue.remove(min);
//        System.out.println("now there are " + waitingQueue.size() + " in queue");
        return min;
    }

    private Process FirstItemAtQueue() {
        Process p = waitingQueue.get(0);
        waitingQueue.remove(p);
        return p;
    }

    private void Tarteb() {
        System.out.println("-------------------------------------");
        for (int i = 0; i < waitingQueue.size(); i++) {
            System.out.println(waitingQueue.get(i).Name);
        }
        System.out.println("-------------------------------------");
    }

    private void CheckAfterExecution() {
        // Not sure what needs to be checked after execution
    }
}
