package com.lzj.eastmoney.data.josn;

public class YingYeBuJson {
	
	private String Province;
	private String SalesCode;
	private String SalesName;
	private String SumActBMoney;
	private String SumActSMoney;
	private String SumActMoney;
	private String BCount;
	private String SCount;
	private String UpCount;
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getSalesCode() {
		return SalesCode;
	}
	public void setSalesCode(String salesCode) {
		SalesCode = salesCode;
	}
	public String getSalesName() {
		return SalesName;
	}
	public void setSalesName(String salesName) {
		SalesName = salesName;
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
	public String getBCount() {
		return BCount;
	}
	public void setBCount(String bCount) {
		BCount = bCount;
	}
	public String getSCount() {
		return SCount;
	}
	public void setSCount(String sCount) {
		SCount = sCount;
	}
	public String getUpCount() {
		return UpCount;
	}
	public void setUpCount(String upCount) {
		UpCount = upCount;
	}
	
	@Override
	public String toString() {
		return "YingYeBuJson [Province=" + Province + ", SalesCode=" + SalesCode + ", SalesName=" + SalesName
				+ ", SumActBMoney=" + SumActBMoney + ", SumActSMoney=" + SumActSMoney + ", SumActMoney=" + SumActMoney
				+ ", BCount=" + BCount + ", SCount=" + SCount + ", UpCount=" + UpCount + "]";
	}
}
