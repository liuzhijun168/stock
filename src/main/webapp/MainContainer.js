Ext.namespace("com.test.cmp");
var userMngPanel;
var productTree;
/**
 * 主Viewport
 * 全局单例
 * @class com.test.cmp.MainContainer
 * @extends Ext.Viewport
 */
com.test.cmp.MainContainer=Ext.extend(Ext.Viewport,{
	//缓存所有一级模块
	firstModules:new Ext.util.MixedCollection(),
	layout:"border",
	id:"viewport",
	initComponent: function(config){
		this.items=[
			{
				xtype:'panel',
				region:'north',
				height:100,
				layout:'fit',
				frame:true,
				html:'<div class="app-top-div"></div>',
				defaults:{
					border:false
				},
				bbar:[
					{
						text:"图表管理",
						handler:this.switchModule.createDelegate(this,["chartmngpanel","图表管理"])
					},"|",
					{
						text:"报表查询",
						handler:this.switchModule.createDelegate(this,["usermngpanel","报表查询"])
					},"|",
					{
						text:"股票管理",
						handler:this.switchModule.createDelegate(this,["productmngpanel","股票管理"])
					},"|",
					{
						text:"配置管理",
						handler:this.switchModule.createDelegate(this,["productmng","配置管理"])
					},
					'->',
					{
						xtype:"label",
						id:"currentModuleLabel"
					},'|',
					{
						text:"首选项"
					},
					{
						text:"退出"
					}
				]
			},
			{
				xtype:'panel',
				id:'leftContainer',
				region:'center',
				layout:'card',
				border:false
			}
		];
		com.test.cmp.MainContainer.superclass.initComponent.call(this);   
	},
	//切换一级模块
	switchModule:function(moduleId,moduleName){
		var leftContainer=Ext.getCmp("leftContainer");
		var targetModle=this.firstModules.get(moduleId);
		var currModuleLabel=Ext.getCmp("currentModuleLabel");
		if(!targetModle){
			switch(moduleId){
				case "usermngpanel":
					targetModle=new com.test.cmp.UserMngPanel({
						id:moduleId
					});
					leftContainer.add(targetModle);
					break;
				case "chartmngpanel":
					targetModle=new com.test.cmp.ChartMngPanel({
						id:moduleId
					});
					leftContainer.add(targetModle);
					break;
				case "productmngpanel":
					targetModle=new com.test.cmp.ProductMngPanel({
						id:moduleId
					});
					leftContainer.add(targetModle);
					break;
				default:
					break;
			}
		}
		currModuleLabel.setText("<font color='#ff0000'>当前模块>"+moduleName+"</font>",false);
		this.firstModules.add(moduleId,targetModle);
		leftContainer.getLayout().setActiveItem(targetModle);
		leftContainer.doLayout();
	}
});
Ext.reg("maincontainer",com.test.cmp.MainContainer);

Ext.onReady(function(){
	var mainCon = new com.test.cmp.MainContainer();
	var currModuleLabel=Ext.getCmp("currentModuleLabel");
	var leftContainer=Ext.getCmp("leftContainer");
	var targetModle = new com.test.cmp.UserMngPanel({
		id:"usermngpanel"
	});
	leftContainer.add(targetModle);
	currModuleLabel.setText("<font color='#ff0000'>当前模块>报表查询</font>",false);
	mainCon.firstModules.add("usermngpanel",targetModle);
	leftContainer.getLayout().setActiveItem(targetModle);
	leftContainer.doLayout();
	var dataGrid=Ext.getCmp("userDataGrid");
	dataGrid.removeAllData();
	dataGrid.loadData('/stock/ReportServlet?m=getData');
});