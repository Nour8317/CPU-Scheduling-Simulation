package cpu.cpu_scheduling;

public class Process implements Comparable<Process> {
    String Name;
    String Color;
    int arrivalTime;
    int burstTime;

    int  Priority;

    int burstDone = 0;

    int turnAroundTime;

    int waitingTime;

    int completionTime;
    int Order;

    public Process(String name, String color , int arrivalTime , int burstTime , int priority )
    {
        this.Name = name ;
        this.Color = color ;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.Priority  = priority;
    }

    public int GetArrivalTime()
    {
        return arrivalTime;
    }


    public void EndTheProcess(int CompletionTime){
        this.completionTime = CompletionTime;
        this.turnAroundTime = CompletionTime - this.arrivalTime;
        this.waitingTime = turnAroundTime - burstTime;
    }


    @Override
    public int compareTo(Process otherProcess) {
        return Integer.compare(this.arrivalTime, otherProcess.arrivalTime);
    }

    public void PrintProcessDetails(){
        System.out.println("Name: " +this.Name + "\nOrder: "+this.Order+ "\nWaiting Time: "+ waitingTime + "\nTrunAroundTime: "+ this.turnAroundTime);
        System.out.println("---------------------------------------------------------------");
    }
}
