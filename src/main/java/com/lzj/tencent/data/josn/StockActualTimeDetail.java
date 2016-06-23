package com.lzj.tencent.data.josn;

public class StockActualTimeDetail {
	/*     0: 未知  
     1: 名字  
     2: 代码  
     3: 当前价格  
     4: 昨收  
     5: 今开  
     6: 成交量（手）  
     7: 外盘  
     8: 内盘  
     9: 买一  
    10: 买一量（手）  
    11-18: 买二 买五  
    19: 卖一  
    20: 卖一量  
    21-28: 卖二 卖五  
    29: 最近逐笔成交  
    30: 时间  
    31: 涨跌  
    32: 涨跌%  
    33: 最高  
    34: 最低  
    35: 价格/成交量（手）/成交额  
    36: 成交量（手）  
    37: 成交额（万）  
    38: 换手率  
    39: 市盈率  
    40:   
    41: 最高  
    42: 最低  
    43: 振幅  
    44: 流通市值  
    45: 总市值  
    46: 市净率  
    47: 涨停价  
    48: 跌停价  */
	private String name;//名字  
	private String code;//代码 
	private String close;//昨收  
	private String open;//今开  
	private String price;//当前价格
	private String chg;//涨跌% 
	private String minPrice;//最低 
	private String maxPrice;//最高  
	private String turnoverRate;//换手率
	private String peRatio;//市盈率
	private String amplitude;//振幅 
	private String minStopPrice;//跌停价
	private String maxStopPrice;//涨停价 
	private String updatedDate;//时间
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMinStopPrice() {
		return minStopPrice;
	}
	public void setMinStopPrice(String minStopPrice) {
		this.minStopPrice = minStopPrice;
	}
	public String getMaxStopPrice() {
		return maxStopPrice;
	}
	public void setMaxStopPrice(String maxStopPrice) {
		this.maxStopPrice = maxStopPrice;
	}
	public String getChg() {
		return chg;
	}
	public void setChg(String chg) {
		this.chg = chg;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(String turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
	public String getPeRatio() {
		return peRatio;
	}
	public void setPeRatio(String peRatio) {
		this.peRatio = peRatio;
	}
	public String getAmplitude() {
		return amplitude;
	}
	public void setAmplitude(String amplitude) {
		this.amplitude = amplitude;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
}
