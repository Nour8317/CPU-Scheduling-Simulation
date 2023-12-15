package cpu.cpu_scheduling;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.gantt.GanttCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GanttChartExample extends JFrame {

    private List<Process> processes;

    public GanttChartExample(String title, List<Process> processes) {
        super(title);
        this.processes = processes;

        // Create dataset
        GanttCategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = createGanttChart(dataset);

        // Create chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        setContentPane(chartPanel);
        SwingUtilities.invokeLater(() -> {
            this.setSize(800, 600);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setVisible(true);
        });
    }

    private GanttCategoryDataset createDataset() {
        TaskSeries series = new TaskSeries("Processes");

        for (Process process : processes) {
            Task mainTask = new Task(process.Name, date(0), date(20)); // Creating a dummy task
            int subtaskId = 1;
            for (duration D : process.durations) {
                System.out.println(process.Name + " from " + D.start + " to " + D.end);
                Task subtask = new Task(process.Name + " Subtask " + subtaskId, date(D.start), date(D.end));

                mainTask.addSubtask(subtask);
                subtaskId++;
            }

            series.add(mainTask);
        }

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(series);

        return dataset;
    }

    private JFreeChart createGanttChart(GanttCategoryDataset dataset) {

        double TotalWaitingTime=0;
        double TotalTurnAroundTime=0;
        int n = processes.size();
        for(Process p :processes)
        {
            TotalTurnAroundTime+= p.turnAroundTime;
            TotalWaitingTime+= p.waitingTime;
            p.PrintProcessDetails();
            p.burstDone = 0;
        }
        System.out.println("Average Waiting Time is: " + TotalWaitingTime / n);
        System.out.println("Average TurnAround Time is: " + TotalTurnAroundTime / n);
        String X ="Average Waiting Time is: " + (TotalWaitingTime / n) +"\n" ;
        X+= "Average TurnAround Time is: " + TotalTurnAroundTime / n;
        JFreeChart chart = ChartFactory.createGanttChart(
                X,
                "Processes",
                "Time",
                dataset,
                true,
                true,
                false
        );

        // Use a CategoryAxis for the time axis
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin(0.2); // Adjust the margin as needed

        return chart;
    }

    private Date date(int hours) {
        return new Date(1000L * 60 * 60 * hours);
    }

    public void simulate() {
        SwingUtilities.invokeLater(() -> {
            GanttChartExample example = new GanttChartExample("Gantt Chart Example", processes);
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
       });
    }
}
