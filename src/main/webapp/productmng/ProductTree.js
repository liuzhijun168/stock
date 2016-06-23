Ext.namespace("com.test.cmp");
com.test.cmp.ProductTree=Ext.extend(Ext.Panel,{
	title:'产品管理',
	bbar:[
		{text:"test"}
	]
});
Ext.reg("producttree",com.test.cmp.ProductTree)
