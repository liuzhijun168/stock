Ext.namespace("com.test.cmp");
com.test.cmp.ChartMngPanel=Ext.extend(Ext.Panel,{
	border:false,
	layout:'border',
	initComponent: function(config){
		var chartSwfUrl = 'ext-3.2.0/resources/charts.swf';
		/*var store = new Ext.data.JsonStore({
	        fields:['name', 'visits', 'views','ttt'],
	        data: [
	            {name:'Jul 07', visits: 245000, views: 3000000,ttt:3200000},
	            {name:'Aug 07', visits: 240000, views: 3500000,ttt:3700000},
	            {name:'Sep 07', visits: 355000, views: 4000000,ttt:4200000},
	            {name:'Oct 07', visits: 375000, views: 4200000,ttt:4900000},
	            {name:'Nov 07', visits: 490000, views: 4500000,ttt:4800000},
	            {name:'Dec 07', visits: 495000, views: 5800000,ttt:5200000},
	            {name:'Jan 08', visits: 520000, views: 6000000,ttt:6200000},
	            {name:'Feb 08', visits: 620000, views: 7500000,ttt:7570000}
	        ]
	    });*/
		
		/*var store = new Ext.data.JsonStore( {
	        proxy :new Ext.data.HttpProxy( {
	        	url:"/stock/ReportServlet?m=getChartData&type=1",  
	        	//url:"/stock/chartmng/data.json?"+new Date(),  
	        	
	            method :'POST',
	            listeners: {
		            "load": function(){
		            }
		        }
	        }),
	        reader :new Ext.data.JsonReader( {
	        	fields: ['name', 'visits'],
	    //        totalProperty :"totalCount",
	            root :"root"
	        }),
	        autoLoad: true     
	    });
		store.load();*/
		var store = new Ext.data.Store({  
			url:"/stock/ReportServlet?m=getChartData&type=1",  
            reader : new Ext.data.JsonReader()  
        });  
		store.load();
		this.items=[
			{
				xtype:'charttree',
				region:'west',
				width:220,
				split:true,
				listeners:{
					"click":function(node,e){
						var chartDataPanel = Ext.getCmp("chartDataPanel");
						chartDataPanel.removeAll();
						if(node.text.indexOf("涨跌幅分布")!=-1 
								|| node.text.indexOf("盈亏图表")!=-1 
								|| node.text.indexOf("资金图表")!=-1 
								|| node.text.indexOf("跑赢大盘图表")!=-1
								|| node.text.indexOf("领先行业")!=-1
								|| node.text.indexOf("流通盘")!=-1){
							var url = "/stock/ReportServlet?m=getChartData&type=1";
							if(node.text.indexOf("资金图表")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=2";
							}else if(node.text.indexOf("跑赢大盘图表")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=3";
							}else if(node.text.indexOf("涨跌幅分布")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=4";
							}else if(node.text.indexOf("领先行业50")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=hangye&num=50";
							}else if(node.text.indexOf("领先行业100")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=hangye&num=100";
							}else if(node.text.indexOf("领先行业150")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=hangye&num=150";
							}else if(node.text.indexOf("领先行业200")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=hangye&num=200";
							}else if(node.text.indexOf("流通盘20")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=liutongpan&num=20";
							}else if(node.text.indexOf("流通盘50")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=liutongpan&num=50";
							}else if(node.text.indexOf("流通盘80")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=liutongpan&num=80";
							}else if(node.text.indexOf("流通盘100")!=-1){
								url = "/stock/ReportServlet?m=getChartData&type=liutongpan&num=100";
							}
							var chartstore = new Ext.data.Store({  
								url:url,  
					            reader : new Ext.data.JsonReader() ,
					            autoLoad:true
					        });  
							var items = {
					            xtype: 'columnchart',
					            id:'chartDataGrid',
					            store: chartstore,
					            url:chartSwfUrl,
					            xField: 'name',
					            extraStyle:
					            {
					                legend:
					                {
					                    display: 'bottom',
					                    padding: 5,
					                    font:
					                    {
					                        family: 'Tahoma',
					                        size: 13
					                    }
					                }
					            },
					            tipRenderer : function(chart, record, index, series){
					                if(series.yField == 'visits'){
					                    return Ext.util.Format.number(record.data.visits, '0,0') + ' visits in ' + record.data.name;
					                }else if(series.yField == 'ttt'){
					                    return Ext.util.Format.number(record.data.visits, '0,0') + ' ttt in ' + record.data.name;
					                }else if(series.yField == 'szzs'){
					                    return Ext.util.Format.number(record.data.szzs, '0.00') + ' % in ' + record.data.name;
					                }else{
					                    return Ext.util.Format.number(record.data.views, '0,0') + ' page views in ' + record.data.name;
					                }
					            }
					        };
							chartDataPanel.add(items);
							chartDataPanel.doLayout();
							chartstore.on('load', function() {  
								var chartDataGrid = Ext.getCmp("chartDataGrid");
					            if (typeof (chartstore.reader.jsonData.series) === 'object') {  
					            	var series = [];  
					                Ext.each(chartstore.reader.jsonData.series, function(serie) {  
					                    series.push(serie);  
					                }); 
									chartDataGrid.setSeries(series);  
					            }  
					        });  
							
						}else if(node.text.indexOf("持仓图表")!=-1 
								|| node.text.indexOf("领先板块")!=-1
								){
							chartDataPanel.removeAll();
							var url = "/stock/ReportServlet?m=getPieChartData";
							if(node.text.indexOf("持仓图表")!=-1){
								url = "/stock/ReportServlet?m=getPieChartData&type=chicang";
							}else if(node.text.indexOf("领先板块20")!=-1){
								url = "/stock/ReportServlet?m=getPieChartData&type=bankuai&num=20";
							}else if(node.text.indexOf("领先板块50")!=-1){
								url = "/stock/ReportServlet?m=getPieChartData&type=bankuai&num=50";
							}else if(node.text.indexOf("领先板块80")!=-1){
								url = "/stock/ReportServlet?m=getPieChartData&type=bankuai&num=80";
							}else if(node.text.indexOf("领先板块100")!=-1){
								url = "/stock/ReportServlet?m=getPieChartData&type=bankuai&num=100";
							}
							var chartStore = new Ext.data.JsonStore({
							   url:url,
							   root:'chartview',
							   fields: ['stock_name', 'shizhi'],
							}); 
							chartStore.on('load', function() { 
								var items = {
							            store: chartStore,
							            xtype: 'piechart',
							            dataField: 'shizhi',
							            categoryField: 'stock_name',
							            url:chartSwfUrl,
							            extraStyle:
							            {
							                legend:
							                {
							                    display: 'bottom',
							                    padding: 5,
							                    font:
							                    {
							                        family: 'Tahoma',
							                        size: 13
							                    }
							                }
							            }
								};
								chartDataPanel.add(items);
								chartDataPanel.doLayout();
					        });  
							chartStore.load();
						}
						
					} 
				}
			},
			{
				xtype:'panel',
			    id:'chartDataPanel',
				region:'center',
				items: {
		            xtype: 'columnchart',
		            id:'chartDataGrid',
		            store: store,
		            url:chartSwfUrl,
		            xField: 'name',
		            tipRenderer : function(chart, record, index, series){
		                if(series.yField == 'visits'){
		                    return Ext.util.Format.number(record.data.visits, '0,0') + ' visits in ' + record.data.name;
		                }else if(series.yField == 'ttt'){
		                    return Ext.util.Format.number(record.data.visits, '0,0') + ' ttt in ' + record.data.name;
		                }else if(series.yField == 'szzs'){
		                    return Ext.util.Format.number(record.data.szzs, '0.00') + ' % in ' + record.data.name;
		                }else{
		                    return Ext.util.Format.number(record.data.views, '0,0') + ' page views in ' + record.data.name;
		                }
		            },
		            extraStyle:
		            {
		                legend:
		                {
		                    display: 'bottom',
		                    padding: 5,
		                    font:
		                    {
		                        family: 'Tahoma',
		                        size: 13
		                    }
		                }
		            },
		            series: [{
		                type:'line',
		                yField: 'visits',
		                displayName: '盈亏'
		            }]
		        }
			}
		];
		this.bbar=new Ext.PagingToolbar({
            pageSize: 20,
            store:this.store,
            displayInfo: true,
            buttons:[
            	'-',
            	{
            		text:"测试",handler:function(){
            			alert(1);
            		}
            	}
            ]
        });
		com.test.cmp.ChartMngPanel.superclass.initComponent.call(this);   
	}
});
Ext.reg("chartmngpanel",com.test.cmp.ChartMngPanel);
