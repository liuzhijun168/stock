package com.lzj.bean;

import java.util.Date;

public class StockDataDay {
	private int id;
	private String b;
	private String c;
	private double d;
	private double e;
	private double r;
	private double p;
	private double q;
	private double s;
	private double o;
	private double m;
	private double m5;
	private double m10;
	private double m20;
	private double m30;
	private double m60;
	private double m120;
	private double m250;
	private String content;
	private Date createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getE() {
		return e;
	}

	public void setE(double e) {
		this.e = e;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getQ() {
		return q;
	}

	public void setQ(double q) {
		this.q = q;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public double getO() {
		return o;
	}

	public void setO(double o) {
		this.o = o;
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getM5() {
		return m5;
	}

	public void setM5(double m5) {
		this.m5 = m5;
	}

	public double getM10() {
		return m10;
	}

	public void setM10(double m10) {
		this.m10 = m10;
	}

	public double getM20() {
		return m20;
	}

	public void setM20(double m20) {
		this.m20 = m20;
	}

	public double getM30() {
		return m30;
	}

	public void setM30(double m30) {
		this.m30 = m30;
	}

	public double getM60() {
		return m60;
	}

	public void setM60(double m60) {
		this.m60 = m60;
	}

	public double getM120() {
		return m120;
	}

	public void setM120(double m120) {
		this.m120 = m120;
	}

	public double getM250() {
		return m250;
	}

	public void setM250(double m250) {
		this.m250 = m250;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "StockDataDay [id=" + id + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", r=" + r + ", p=" + p
				+ ", q=" + q + ", s=" + s + ", o=" + o + ", m=" + m + ", m5=" + m5 + ", m10=" + m10 + ", m20=" + m20
				+ ", m30=" + m30 + ", m60=" + m60 + ", m120=" + m120 + ", m250=" + m250 + ", content=" + content
				+ ", createDate=" + createDate + "]";
	}

}
