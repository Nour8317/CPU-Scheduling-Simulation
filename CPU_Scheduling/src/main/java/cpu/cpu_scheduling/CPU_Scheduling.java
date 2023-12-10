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
    private static int Context;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Process> Processes = new ArrayList<>();
//        GUI.startGUI();
        System.out.println("Enter the number of Processes");
        pNumbers = scanner.nextInt();

        System.out.println("Enter Round Robin Time Quantum");
        Quantum = scanner.nextInt();

        System.out.println("Enter context switching time");
        Quantum = scanner.nextInt();


        for (int i = 0; i < pNumbers; i++) {
            System.out.println("Enter details for Process " + (i + 1));
            System.out.print("Name: ");
            String processName = scanner.next();

            System.out.print("Color: ");
            String processColor = scanner.next();

            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();

            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();

            System.out.print("Priority Number: ");
            int priority = scanner.nextInt();

            Processes.add(new Process(processName, processColor, arrivalTime, burstTime, priority));
        }

        ShortestJobFirst sjf = new ShortestJobFirst(Processes) ;
        sjf.SJF();


    }
}
