/** 
 *  
 * @author LuoYu 
 * @date 2012-12-04 
 * 基于EXT-3.3.0开发的可以添加动态折线数的EXT.chart.DynamicLineChart插件 
 * 初使化方法可以通过 new Ext.chart.DynamicLineChart(), 
 * 也可以通过xtype的形式,xtype:dynamiclinechart 
 *  
 */  
Ext.chart.DynamicLineChart = Ext.extend(Ext.chart.LineChart,{  
    initComponent : function() {  
        var store = new Ext.data.Store({  
            url : this.loadUrl,  
            reader : new Ext.data.JsonReader()  
        });  
        var config = {  
            store : store,  
            xField: this.xField,  
            series : new Array(),  
            extraStyle:{    
                legend:{  
                    display: 'bottom',    
                    padding: 5,    
                    font:{    
                        family: 'Tahoma',    
                        size: 13    
                    }    
                }    
            }  
        };  
        Ext.apply(this, config);  
        Ext.apply(this.initialConfig, config);  
        Ext.chart.DynamicLineChart.superclass.initComponent.apply(this, arguments);  
    },  
    onRender : function() {  
        Ext.chart.DynamicLineChart.superclass.onRender.apply(this,arguments);  
        this.store.on('load', function() {  
            if (typeof (this.store.reader.jsonData.series) === 'object') {  
                var series = [];  
                Ext.each(this.store.reader.jsonData.series, function(serie) {  
                    series.push(serie);  
                });  
                this.setSeries(series);  
            }  
        }, this);  
        this.store.load();  
    }  
});  
Ext.reg("dynamiclinechart", Ext.chart.DynamicLineChart);