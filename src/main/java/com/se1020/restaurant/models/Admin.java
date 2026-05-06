package com.se1020.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    private String adminSecurityClearanceLevel;

    public String getAdminSecurityClearanceLevel() { return adminSecurityClearanceLevel; }
    public void setAdminSecurityClearanceLevel(String adminSecurityClearanceLevel) { this.adminSecurityClearanceLevel = adminSecurityClearanceLevel; }
}
