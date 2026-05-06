package com.se1020.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "global_config")
public class GlobalConfig {

    @Id
    private Long id = 1L; // Always 1 for singleton configuration

    private int minBookingHours = 2;
    private int maxBookingHours = 3;

    // Area Booking Durations
    private int areaMinBookingHours = 3;
    private int areaMaxBookingHours = 12;
    // New constraints
    private int tableCancelHours = 24;
    private int areaCancelHours = 48;
    private int restaurantOpenTime = 10;
    private int restaurantCloseTime = 23;

    // Advance Booking Limits
    private int tableAdvanceBookingDays = 7;
    private int areaAdvanceBookingDays = 30;
    private int areaMinAdvanceBookingDays = 7;


    public GlobalConfig() {
    }

    public GlobalConfig(int minBookingHours, int maxBookingHours) {
        this.id = 1L;
        this.minBookingHours = minBookingHours;
        this.maxBookingHours = maxBookingHours;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinBookingHours() {
        return minBookingHours;
    }

    public void setMinBookingHours(int minBookingHours) {
        this.minBookingHours = minBookingHours;
    }

    public int getMaxBookingHours() {
        return maxBookingHours;
    }

    public void setMaxBookingHours(int maxBookingHours) {
        this.maxBookingHours = maxBookingHours;
    }

    public int getTableCancelHours() {
        return tableCancelHours;
    }

    public void setTableCancelHours(int tableCancelHours) {
        this.tableCancelHours = tableCancelHours;
    }

    public int getAreaCancelHours() {
        return areaCancelHours;
    }

    public void setAreaCancelHours(int areaCancelHours) {
        this.areaCancelHours = areaCancelHours;
    }

    public int getRestaurantOpenTime() {
        return restaurantOpenTime;
    }

    public void setRestaurantOpenTime(int restaurantOpenTime) {
        this.restaurantOpenTime = restaurantOpenTime;
    }

    public int getRestaurantCloseTime() {
        return restaurantCloseTime;
    }

    public void setRestaurantCloseTime(int restaurantCloseTime) {
        this.restaurantCloseTime = restaurantCloseTime;
    }
    public int getTableAdvanceBookingDays() {
        return tableAdvanceBookingDays;
    }

    public void setTableAdvanceBookingDays(int tableAdvanceBookingDays) {
        this.tableAdvanceBookingDays = tableAdvanceBookingDays;
    }

    public int getAreaAdvanceBookingDays() {
        return areaAdvanceBookingDays;
    }

    public void setAreaAdvanceBookingDays(int areaAdvanceBookingDays) {
        this.areaAdvanceBookingDays = areaAdvanceBookingDays;
    }

    public int getAreaMinAdvanceBookingDays() {
        return areaMinAdvanceBookingDays;
    }

    public void setAreaMinAdvanceBookingDays(int areaMinAdvanceBookingDays) {
        this.areaMinAdvanceBookingDays = areaMinAdvanceBookingDays;
    }
    public int getAreaMinBookingHours() {
        return areaMinBookingHours;
    }

    public void setAreaMinBookingHours(int areaMinBookingHours) {
        this.areaMinBookingHours = areaMinBookingHours;
    }

    public int getAreaMaxBookingHours() {
        return areaMaxBookingHours;
    }

    public void setAreaMaxBookingHours(int areaMaxBookingHours) {
        this.areaMaxBookingHours = areaMaxBookingHours;
    }
}
