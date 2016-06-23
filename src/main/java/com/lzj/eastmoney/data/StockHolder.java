package com.lzj.eastmoney.data;

public class StockHolder {

	private int id;
	private String stockCode;
	private String stockholderName;
	private String stockholderType;
	private float stockholderRatio;
	private String addOrSubtract;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockholderName() {
		return stockholderName;
	}
	public void setStockholderName(String stockholderName) {
		this.stockholderName = stockholderName;
	}
	public String getStockholderType() {
		return stockholderType;
	}
	public void setStockholderType(String stockholderType) {
		this.stockholderType = stockholderType;
	}
	public float getStockholderRatio() {
		return stockholderRatio;
	}
	public void setStockholderRatio(float stockholderRatio) {
		this.stockholderRatio = stockholderRatio;
	}
	public String getAddOrSubtract() {
		return addOrSubtract;
	}
	public void setAddOrSubtract(String addOrSubtract) {
		this.addOrSubtract = addOrSubtract;
	}
	
	@Override
	public String toString() {
		return "StockHolder [id=" + id + ", stockCode=" + stockCode + ", stockholderName=" + stockholderName
				+ ", stockholderType=" + stockholderType + ", stockholderRatio=" + stockholderRatio + ", addOrSubtract="
				+ addOrSubtract + "]";
	}
	
}
