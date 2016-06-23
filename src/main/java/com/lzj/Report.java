package com.lzj;

import java.text.DecimalFormat;

public class Report {
	private DecimalFormat decimalFormat = new DecimalFormat("####");
	private DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
	
	private int id;
	private String createDate;
	private double szzs;
	private double szzsbili;
	private double szzsbili_w;
	private double szzsbili_t;
	private double cangwei;
	private double benjin;
	private double chenben;
	private double shizhi;
	private double fudongkuiyin_d;
	private double fudongkuiyin_w;
	private double fudongkuiyin_m;
	private double fudongkuiyin_y;
	private double fudongkuiyin_t;
	private double fudongkuiyinbili_d;
	private double fudongkuiyinbili_w;
	private double fudongkuiyinbili_m;
	private double fudongkuiyinbili_y;
	private double fudongkuiyinbili_t;
	private boolean isMd = false;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public double getSzzs() {
		return szzs;
	}
	public void setSzzs(double szzs) {
		this.szzs = szzs;
	}
	public double getChenben() {
		return chenben;
	}
	public void setChenben(double chenben) {
		this.chenben = chenben;
	}
	public double getShizhi() {
		return shizhi;
	}
	public void setShizhi(double shizhi) {
		this.shizhi = shizhi;
	}
	
	public double getFudongkuiyinbili_d() {
		return fudongkuiyinbili_d;
	}
	public void setFudongkuiyinbili_d(double fudongkuiyinbili_d) {
		this.fudongkuiyinbili_d = fudongkuiyinbili_d;
	}
	public double getFudongkuiyinbili_w() {
		return fudongkuiyinbili_w;
	}
	public void setFudongkuiyinbili_w(double fudongkuiyinbili_w) {
		this.fudongkuiyinbili_w = fudongkuiyinbili_w;
	}
	public double getFudongkuiyinbili_m() {
		return fudongkuiyinbili_m;
	}
	public void setFudongkuiyinbili_m(double fudongkuiyinbili_m) {
		this.fudongkuiyinbili_m = fudongkuiyinbili_m;
	}
	public double getFudongkuiyinbili_y() {
		return fudongkuiyinbili_y;
	}
	public void setFudongkuiyinbili_y(double fudongkuiyinbili_y) {
		this.fudongkuiyinbili_y = fudongkuiyinbili_y;
	}
	public double getFudongkuiyinbili_t() {
		return fudongkuiyinbili_t;
	}
	public void setFudongkuiyinbili_t(double fudongkuiyinbili_t) {
		this.fudongkuiyinbili_t = fudongkuiyinbili_t;
	}
	public double getBenjin() {
		return benjin;
	}

	public void setBenjin(double benjin) {
		this.benjin = benjin;
	}
	public double getFudongkuiyin_d() {
		return fudongkuiyin_d;
	}
	public void setFudongkuiyin_d(double fudongkuiyin_d) {
		this.fudongkuiyin_d = fudongkuiyin_d;
	}
	public double getFudongkuiyin_w() {
		return fudongkuiyin_w;
	}
	public void setFudongkuiyin_w(double fudongkuiyin_w) {
		this.fudongkuiyin_w = fudongkuiyin_w;
	}
	public double getFudongkuiyin_m() {
		return fudongkuiyin_m;
	}
	public void setFudongkuiyin_m(double fudongkuiyin_m) {
		this.fudongkuiyin_m = fudongkuiyin_m;
	}
	public double getFudongkuiyin_y() {
		return fudongkuiyin_y;
	}
	public void setFudongkuiyin_y(double fudongkuiyin_y) {
		this.fudongkuiyin_y = fudongkuiyin_y;
	}
	public double getFudongkuiyin_t() {
		return fudongkuiyin_t;
	}
	public void setFudongkuiyin_t(double fudongkuiyin_t) {
		this.fudongkuiyin_t = fudongkuiyin_t;
	}
	public double getSzzsbili_w() {
		return szzsbili_w;
	}
	public void setSzzsbili_w(double szzsbili_w) {
		this.szzsbili_w = szzsbili_w;
	}
	public double getSzzsbili() {
		return szzsbili;
	}
	public void setSzzsbili(double szzsbili) {
		this.szzsbili = szzsbili;
	}
	public double getSzzsbili_t() {
		return szzsbili_t;
	}
	public void setSzzsbili_t(double szzsbili_t) {
		this.szzsbili_t = szzsbili_t;
	}
	public boolean isMd() {
		return isMd;
	}
	public void setMd(boolean isMd) {
		this.isMd = isMd;
	}
	public double getCangwei() {
		return cangwei;
	}
	public void setCangwei(double cangwei) {
		this.cangwei = cangwei;
	}
	
}
