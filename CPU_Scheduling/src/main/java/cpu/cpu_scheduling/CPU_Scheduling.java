/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cpu.cpu_scheduling;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import org.jfree.chart.JFreeChart;
/**
 *
 * @author Nour eldeen
 */
public class CPU_Scheduling {
    private static int pNumbers;
    private static int Quantum = 4;
    private static int ContextSwitching;
    public ArrayList<Process> finishedProcesses;
    
    
    public static void main(String[] args) throws InterruptedException {
        startgui program = new startgui();
        program.run();

    }

    public CPU_Scheduling() {
        finishedProcesses = new ArrayList<Process>();
    }
    public void setFinishProcess(ArrayList<Process> v){
        finishedProcesses = v;
    }
}
