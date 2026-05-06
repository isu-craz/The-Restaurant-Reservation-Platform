package com.se1020.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "waiters")
public class Waiter extends User {
    private String employeeId;
    private String shiftTiming;
    private String assignedSection;

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getShiftTiming() { return shiftTiming; }
    public void setShiftTiming(String shiftTiming) { this.shiftTiming = shiftTiming; }

    public String getAssignedSection() { return assignedSection; }
    public void setAssignedSection(String assignedSection) { this.assignedSection = assignedSection; }
}
