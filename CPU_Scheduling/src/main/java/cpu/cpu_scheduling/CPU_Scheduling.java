/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cpu.cpu_scheduling;

import java.awt.Color;
import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
/**
 *
 * @author Nour eldeen
 */
public class CPU_Scheduling {
    private static int pNumbers;
    private static int Quantum = 4;
    private static int ContextSwitching;

    public static void main(String[] args) throws InterruptedException {

       ArrayList <Process> Processes = new ArrayList<>();
        // GUI.startGUI();
//        System.out.println("Enter the number of Processes");
//        pNumbers = scanner.nextInt();
//
//        System.out.println("Enter Round Robin Time Quantum");
//        Quantum = scanner.nextInt();
//
//        System.out.println("Enter context switching time");
//        ContextSwitching = scanner.nextInt();
//
//         SJF Exapmle-1 From the slides
//        Processes.add(new Process("P1","Red" ,0 , 7 , 4,Quantum,20));
//        Processes.add(new Process("P2","Red" ,2 , 4 , 4,Quantum,17));
//        Processes.add(new Process("P3","Red" ,4 , 1 , 4,Quantum,16));
//        Processes.add(new Process("P4","Red" ,5 , 4 , 4,Quantum,43));
//
//        // AG Schedule Example -- For Testing Purposes Only
//        Processes.add(new Process("P1","Red" ,0 , 17 , 4,Quantum,20));
//        Processes.add(new Process("P2","Red" ,3 , 6 , 4,Quantum,17));
//        Processes.add(new Process("P3","Red" ,4 , 10 , 4,Quantum,16));
//        Processes.add(new Process("P4","Red" ,29 , 4 , 4,Quantum,43));
//
//         SRTF Exapmle-1 From the slides
//        Processes.add(new Process("P1",Color.RED ,0 , 7 , 4,Quantum,20));
//        Processes.add(new Process("P2",Color.RED ,2 , 4 , 4,Quantum,17));
//        Processes.add(new Process("P3",Color.RED ,4 , 1 , 4,Quantum,16));
//        Processes.add(new Process("P4",Color.RED ,5 , 4 , 4,Quantum,43));
////
////         SRTF Exapmle-2 From the slides
        Processes.add(new Process("P1",Color.RED ,0 , 7 , 4,Quantum,20));
        Processes.add(new Process("P2",Color.RED ,2 , 16 , 4,Quantum,17));
        Processes.add(new Process("P3",Color.RED ,4 , 13 , 4,Quantum,16));
        Processes.add(new Process("P4",Color.RED ,6 , 10 , 4,Quantum,43));
//
//      Priority Scheduling Example frpm the slides
//        Processes.add(new Process("P1",Color.red ,0 , 10 , 3,Quantum,20));
//        Processes.add(new Process("P2",Color.CYAN ,0 , 1 , 1,Quantum,17));
//        Processes.add(new Process("P3",Color.PINK ,0 , 2 , 4,Quantum,16));
//        Processes.add(new Process("P4",Color.black ,0 , 1 , 5,Quantum,43));
//        Processes.add(new Process("P5",Color.MAGENTA ,0 , 5 , 2,Quantum,43));
//        Processes.add( new Process("P0", Color.RED, 0, 8, 49,9,38));
//        Processes.add( new Process("P1", Color.BLUE, 17, 5, 9,24,37));
//        Processes.add(new Process("P2", Color.GREEN, 22, 8,9,32,36));
//        Processes.add(new Process("P3", Color.YELLOW, 7, 6, 9,5,25));
//        Processes.add(new Process("P4", Color.ORANGE, 10, 9, 9,72,21));
//        Processes.add( new Process("P5", Color.BLACK, 25, 9, 9,87,40));
//        Processes.add( new Process("P6", Color.WHITE, 10, 7, 9,126,39));
//        Processes.add(new Process("P7", Color.CYAN, 26, 7, 9,42,19));
//        Processes.add(new Process("P8", Color.GRAY, 3, 10,9 ,71,24));
//        Processes.add(new Process("P9", Color.MAGENTA, 3, 10,9 ,24,45));
                
        

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
////
//        AGSchedule AG = new AGSchedule(Processes);
//        AG.AGStart();
//        AG.ResetPocesses();
//        GanttChartExample example = new GanttChartExample("SJF", AG.finishedProcesses);
//        PriorityScheduling s = new PriorityScheduling();
//        s.startPriorityScheduling(Processes);

//        PriorityScheduling2 PP = new PriorityScheduling2(Processes);
//        PP.Start();
//        PP.ResetProcesses();


//        SRTF2 srtf2 = new SRTF2(Processes);
//        srtf2.Start();
////        srtf2.ResetPocesses();
//        SRTF srtf = new SRTF();
//        srtf.startSRTF(Processes);

//        ShortestJobFirst sh = new ShortestJobFirst(Processes,0);
//        sh.SJF();

//SJF TESTING
//        ShortestJobFirst pd = new ShortestJobFirst(Processes);
//        pd.SJF();
//        GanttChartExample example = new GanttChartExample("SJF", pd.finishedProcesses);
        

//Priorityscheduling TESTING
//        PriorityScheduling2 pd = new PriorityScheduling2(Processes);
//        pd.Start();
//        GanttChartExample example = new GanttChartExample("Priority Schedule", pd.finishedProcesses);


//STRF TESTING
        SRTF2 pd = new SRTF2(Processes);
        pd.Start();
        GanttChartExample example = new GanttChartExample("Priority Schedule", pd.finishedProcesses);
        
//SJF TESTING
//        ShortestJobFirst pd = new ShortestJobFirst(Processes,2);
 //       pd.SJF();
//        GanttChartExample example = new GanttChartExample("ShortestJobFirst", AG.finishedProcesses);
        
//        example.simulate();
    }
}
