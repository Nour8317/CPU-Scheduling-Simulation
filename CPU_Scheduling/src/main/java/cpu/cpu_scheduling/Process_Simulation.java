/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cpu.cpu_scheduling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.xml.ParseException;
import java.util.Date;

/**
 *
 * @author Nour eldeen
 */
public class Process_Simulation extends javax.swing.JFrame {
        
    public ArrayList<Process> finishedProcesses;
    /**
     * Creates new form Process_Simulation
     */
    public Process_Simulation(ArrayList<Process> fProcesses) throws java.text.ParseException {
        this.finishedProcesses = fProcesses;
        initComponents();
        showGanttChart();
    }
    private void showGanttChart() throws java.text.ParseException {
        IntervalCategoryDataset dataset = getCategoryDataset();
        Vector<Color> processesColor = new Vector<>(); // Populate with colors
        String title = "Gantt Chart";
        String Xlabel = "Time";
        String Ylabel = "Processes";

        chart ganttChart = new chart();
        ganttChart.setDataset(dataset, title, Xlabel, Ylabel);
        this.chart1.add(ganttChart);
        
    }
    
    private IntervalCategoryDataset getCategoryDataset() throws java.text.ParseException {
        List<Process> finishedProcesses = this.finishedProcesses;
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date fixedTime;
        fixedTime = dateFormat.parse("00:00:00");
        for (Process process : finishedProcesses) {
            TaskSeries series = getTaskSeries(process, fixedTime);
            dataset.add(series);
        }
        return dataset;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chart1 = new cpu.cpu_scheduling.chart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void startsimulate(ArrayList<Process> finishedProcesses) throws java.text.ParseException {
    java.awt.EventQueue.invokeLater (()->{
        try {      
            new Process_Simulation(finishedProcesses).setVisible(true);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(Process_Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
}   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private cpu.cpu_scheduling.chart chart1;
    // End of variables declaration//GEN-END:variables

    private TaskSeries getTaskSeries(Process process, Date fixedTime) {
        TaskSeries series = new TaskSeries(process.Name);
        Date startTime = new Date(fixedTime.getTime() + process.getArrivalTime());
        Date endTime = new Date(fixedTime.getTime() + process.finishTime);
        Task task = new Task(process.Name, startTime, endTime);
        for (duration duration : process.durations) {
            task.addSubtask(new Task(process.Name,
                    new Date(fixedTime.getTime() + duration.start),
                    new Date(fixedTime.getTime() + duration.end)));
        }
        series.add(task);
        return series;
    }
}
