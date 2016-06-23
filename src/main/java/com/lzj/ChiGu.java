package com.lzj;

public class ChiGu {
	//证券名称        证券数量        可卖数量        成本价        浮动盈亏        盈亏比例(%)        最新市值        当前价        今买数量        今卖数量        证券代码        股东代码          
	private String name;
	private int amount;
	private int kemaiAmount;
	private float chenben;
	private float fudongyinkui;
	private float yinkuibili;
	private float zuixinshizhi;
	private float dangqianjia;
	private int jinBuyAmount;
	private int jinSaleAmount;
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getKemaiAmount() {
		return kemaiAmount;
	}
	public void setKemaiAmount(int kemaiAmount) {
		this.kemaiAmount = kemaiAmount;
	}
	public float getChenben() {
		return chenben;
	}
	public void setChenben(float chenben) {
		this.chenben = chenben;
	}
	public float getFudongyinkui() {
		return fudongyinkui;
	}
	public void setFudongyinkui(float fudongyinkui) {
		this.fudongyinkui = fudongyinkui;
	}
	public float getYinkuibili() {
		return yinkuibili;
	}
	public void setYinkuibili(float yinkuibili) {
		this.yinkuibili = yinkuibili;
	}
	public float getZuixinshizhi() {
		return zuixinshizhi;
	}
	public void setZuixinshizhi(float zuixinshizhi) {
		this.zuixinshizhi = zuixinshizhi;
	}
	public float getDangqianjia() {
		return dangqianjia;
	}
	public void setDangqianjia(float dangqianjia) {
		this.dangqianjia = dangqianjia;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getJinBuyAmount() {
		return jinBuyAmount;
	}
	public void setJinBuyAmount(int jinBuyAmount) {
		this.jinBuyAmount = jinBuyAmount;
	}
	public int getJinSaleAmount() {
		return jinSaleAmount;
	}
	public void setJinSaleAmount(int jinSaleAmount) {
		this.jinSaleAmount = jinSaleAmount;
	}
	
	@Override
	public String toString() {
		return "ChiGu [name=" + name + ", amount=" + amount + ", kemaiAmount="
				+ kemaiAmount + ", chenben=" + chenben + ", fudongyinkui="
				+ fudongyinkui + ", yinkuibili=" + yinkuibili
				+ ", zuixinshizhi=" + zuixinshizhi + ", dangqianjia="
				+ dangqianjia + ", jinBuyAmount=" + jinBuyAmount
				+ ", jinSaleAmount=" + jinSaleAmount + ", code=" + code + "]";
	}
	
	
}
