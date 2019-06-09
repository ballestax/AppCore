/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.persistence.JDBC;

import java.util.Date;

/**
 *
 * @author ballestax
 */
public class Update {

    private int id;
    private long lider;
    private int registers;
    private int updates;
    private String status;
    private Date lastTimeUpdate;

    public Update() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegisters() {
        return registers;
    }

    public void setRegisters(int registers) {
        this.registers = registers;
    }

    public int getUpdates() {
        return updates;
    }

    public void setUpdates(int updates) {
        this.updates = updates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastTimeUpdate() {
        return lastTimeUpdate;
    }

    public void setLastTimeUpdate(Date lastTimeUpdate) {
        this.lastTimeUpdate = lastTimeUpdate;
    }

    public long getLider() {
        return lider;
    }

    public void setLider(long lider) {
        this.lider = lider;
    }

}
