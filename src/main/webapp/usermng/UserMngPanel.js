Ext.namespace("com.test.cmp");
com.test.cmp.UserMngPanel=Ext.extend(Ext.Panel,{
	border:false,
	layout:'border',
	initComponent: function(config){
		this.items=[
			{
				xtype:'usertree',
				region:'west',
				width:220,
				split:true,
				collapsible:true,
				listeners:{
					"click":function(node,e){
						if(node.text.indexOf("日报表")!=-1){
							
							var dataGrid=Ext.getCmp("userDataGrid");
							var clmMode = dataGrid.getColumnModel();
							clmMode.setHidden(clmMode.findColumnIndex("fudongkuiyin_d"),false);
							clmMode.setHidden(clmMode.findColumnIndex("fudongkuiyinbili_d"),false);
							dataGrid.removeAllData();
							dataGrid.loadData('/stock/ReportServlet?m=getData');
						}else if(node.text.indexOf("周报表")!=-1){
							var dataGrid=Ext.getCmp("userDataGrid");
							/*var mainPanel = dataGrid.getBubbleTarget();
							mainPanel.remove("userDataGrid",true);
							var items = {
									xtype:'userWeekgrid',
									id:'userDataGrid',
									region:'center'
							};
							dataGrid.add(items);
							alert(1);
							dataGrid.doLayout();
							alert(2);*/
							//component/box/container/panel/usermngpanel
							//component/box/container/panel/grid/usergrid
							var clmMode = dataGrid.getColumnModel();
						/*	dataGrid.colModel = new Ext.grid.ColumnModel([
                                { header: "Ticker", width: 60, sortable: true},
                                { header: "Company Name", width: 150, sortable: true},
                                { header: "Market Cap.", width: 100, sortable: true},
                                { header: "$ Sales", width: 100, sortable: true},
                                { header: "Employees", width: 100, sortable: true, resizable: false}
                             ]);*/
							clmMode.setHidden(clmMode.findColumnIndex("fudongkuiyin_d"),true);
							clmMode.setHidden(clmMode.findColumnIndex("fudongkuiyinbili_d"),true);
							dataGrid.doLayout();
							//alert(dataGrid.getXTypes() );
							//alert(clmMode.getColumnCount());
							dataGrid=Ext.getCmp("userDataGrid");
							dataGrid.loadData('/stock/ReportServlet?m=getWData');
						}
					}
				}
			},
			{
				xtype:'usergrid',
				id:'userDataGrid',
				region:'center'
			}
		];
		com.test.cmp.UserMngPanel.superclass.initComponent.call(this);   
	}
});
Ext.reg("usermngpanel",com.test.cmp.UserMngPanel);
