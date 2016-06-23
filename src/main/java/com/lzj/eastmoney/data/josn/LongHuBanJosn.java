package com.lzj.eastmoney.data.josn;

public class LongHuBanJosn {
	
	private String SalesCode;
	private String SalesName;//营业部名称
	private String SumActBMoney;//买入额(万)
	private String SumActSMoney;//卖出额(万)
	private String SumActMoney;//龙虎榜成交金额(万)
	private int BCount;//买入次数 
	private int SCount;//卖出次数
	private int UpCount;//上榜次数
	public String getSalesCode() {
		return SalesCode;
	}
	public void setSalesCode(String salesCode) {
		this.SalesCode = salesCode;
	}
	public String getSalesName() {
		return SalesName;
	}
	public void setSalesName(String salesName) {
		this.SalesName = salesName;
	}
	public String getSumActBMoney() {
		return SumActBMoney;
	}
	public void setSumActBMoney(String sumActBMoney) {
		SumActBMoney = sumActBMoney;
	}
	public String getSumActSMoney() {
		return SumActSMoney;
	}
	public void setSumActSMoney(String sumActSMoney) {
		SumActSMoney = sumActSMoney;
	}
	public String getSumActMoney() {
		return SumActMoney;
	}
	public void setSumActMoney(String sumActMoney) {
		SumActMoney = sumActMoney;
	}
	public int getBCount() {
		return BCount;
	}
	public void setBCount(int bCount) {
		BCount = bCount;
	}
	public int getSCount() {
		return SCount;
	}
	public void setSCount(int sCount) {
		SCount = sCount;
	}
	public int getUpCount() {
		return UpCount;
	}
	public void setUpCount(int upCount) {
		UpCount = upCount;
	}
	@Override
	public String toString() {
		return "LongHuBanJosn [salesCode=" + SalesCode + ", salesName=" + SalesName + ", SumActBMoney=" + SumActBMoney
				+ ", SumActSMoney=" + SumActSMoney + ", SumActMoney=" + SumActMoney + ", BCount=" + BCount + ", SCount="
				+ SCount + ", UpCount=" + UpCount + "]";
	}
	
	
}
