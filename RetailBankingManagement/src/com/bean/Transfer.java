package com.bean;

import java.sql.Date;
import java.sql.Time;

public class Transfer {
private int source;
private int target;
private double amount;
private Date date;
private Time time;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public Time getTime() {
	return time;
}
public void setTime(Time time) {
	this.time = time;
}
public Transfer(int source, int target, double amount, Date date,Time time) {
	super();
	this.source = source;
	this.target = target;
	this.amount = amount;
	this.date = date;
	this.time=time;
}
public Transfer(int source, int target, double amount) {
	super();
	this.source = source;
	this.target = target;
	this.amount = amount;
}
public int getSource() {
	return source;
}
public void setSource(int source) {
	this.source = source;
}
public int getTarget() {
	return target;
}
public void setTarget(int target) {
	this.target = target;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}

}
