/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cpu.cpu_scheduling;

import java.util.ArrayList;
import java.util.Scanner;
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
        ArrayList<Process> Processes = new ArrayList<>();
//        GUI.startGUI();
        System.out.println("Enter the number of Processes");
        pNumbers = scanner.nextInt();

        System.out.println("Enter Round Robin Time Quantum");
        Quantum = scanner.nextInt();

        System.out.println("Enter context switching time");
        ContextSwitching = scanner.nextInt();

        Processes.add(new Process("P1","Red" ,0 , 17 , 4,Quantum,20));
        Processes.add(new Process("P2","Red" ,3 , 6 , 4,Quantum,17));
        Processes.add(new Process("P3","Red" ,4 , 10 , 4,Quantum,16));
        Processes.add(new Process("P4","Red" ,29 , 4 , 4,Quantum,43));

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

        AGSchedule AG = new AGSchedule(Processes);
        AG.AGStart();


    }
}
