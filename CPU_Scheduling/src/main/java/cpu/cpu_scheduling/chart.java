/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.cpu_scheduling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.IntervalCategoryDataset;


/**
 *
 * @author Nour eldeen
 */
public class chart extends JPanel{
    public chart() {
        setLayout(new BorderLayout());
        
    }

    public void setDataset(IntervalCategoryDataset dataset, String title, String Xlabel, String Ylabel) {
        removeAll();
        JFreeChart chart = ChartFactory.createGanttChart(title, Ylabel, Xlabel, dataset, true, false, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        DateAxis axis = (DateAxis) plot.getRangeAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("S"));
//        plot.setBackgroundPaint(Color.white);
//        plot.setDomainGridlinesVisible(true);
//        plot.setRangeGridlinePaint(Color.BLACK);
//        GanttRenderer renderer = new GanttRenderer();
//        plot.setRenderer(renderer);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        add(chartPanel);
        repaint();
        
        revalidate();
    }
}

