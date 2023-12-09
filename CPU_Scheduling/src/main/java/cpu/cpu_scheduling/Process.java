/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.cpu_scheduling;

import java.awt.Color;

/**
 *
 * @author Nour eldeen
 */
public class Process {
    private String name;
    private Color color;
    private int arrivalTime;
    private int burstTime;
    private int priorityNumber;
    private int quantam;
    private int contextSwitch;

    public Process() {
        
    }

    public Process(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int quantam, int contextSwitch) {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.quantam = quantam;
        this.contextSwitch = contextSwitch;
    }

  
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public void setQuantam(int quantam) {
        this.quantam = quantam;
    }

    public void setContextSwitch(int contextSwitch) {
        this.contextSwitch = contextSwitch;
    }


    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public int getQuantam() {
        return quantam;
    }

    public int getContextSwitch() {
        return contextSwitch;
    }
}
