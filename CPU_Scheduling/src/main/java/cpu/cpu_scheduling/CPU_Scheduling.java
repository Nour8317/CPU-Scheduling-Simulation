/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
/**
 *
 * @author Nour eldeen
 */
public class CPU_Scheduling {
    private static int pNumbers;
    private static int Quantum;
    private static int ContextSwitching;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //ArrayList<Process> Processes = new ArrayList<>();
        Vector<Process> Processes = new Vector();
//        GUI.startGUI();
        // AG Schedule Example -- For Testing Purposes Only
        Processes.add(new Process("P1","Red" ,0 , 11 , 2,0,0));
        Processes.add(new Process("P2","Red" ,5 , 20 , 0,0,0));
        Processes.add(new Process("P3","Red" ,12 , 2 , 3,0,0));
        Processes.add(new Process("P4","Red" ,2 , 10 , 1,0,0));
        Processes.add(new Process("P5","Red" ,9 , 16 , 4,0,0));
        

//        for (int i = 0; i < pNumbers; i++) {
//            System.out.println("Enter details for Process " + (i + 1));
//            System.out.print("Name: ");
//            String processName = scanner.next();
//
//            System.out.print("Color: ");
//            String processColor = scanner.next();
//
//            System.out.print("Arrival Time: ");
//            int arrivalTime = scanner.nextInt();
//
//            System.out.print("Burst Time: ");
//            int burstTime = scanner.nextInt();
//
//            System.out.print("Priority Number: ");
//            int priority = scanner.nextInt();
//
//            Processes.add(new Process(processName, processColor, arrivalTime, burstTime, priority, Quantum));
//        }

//        ShortestJobFirst sjf = new ShortestJobFirst(Processes , ContextSwitching) ;
//        sjf.SJF();

//        AGSchedule AG = new AGSchedule(Processes);
//        AG.AGStart();
          priorityScheduling ps = new priorityScheduling();
          ps.prioritySchedulingStart(Processes);

    }
}
