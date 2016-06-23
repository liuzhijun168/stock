Ext.namespace("com.test.cmp");
com.test.cmp.UserWeekGrid = Ext.extend(Ext.grid.GridPanel, {
	title : '数据列表',
	viewConfig : {
		forceFit : true
	},
	bodyStyle : 'width:100%',
	width : '100%',
	height : '100%',
	autoWidth : true,
	autoExpandColumn : "memory", //自动扩展宽度的列  
	loadMask : {
		msg : "数据加载中，请稍等..."
	},
	userDetailWindow : null,
	/**
	 * initComponent
	 * @param {} config
	 */
	initComponent : function(config) {
		window.delUser = function(userId) {
			alert("开始删除" + userId);
		}

		window.showDetail = function(userId) {
			alert("显示详情" + userId);
		}

		this.ageRenderer = function(val) {
			if (val > 25) {
				return "<font color='#ff0000'>" + val + "</font>";
			}
			return "<font color='#0000ff'>" + val + "</font>";
		};

		this.numFmtRenderer = function(val) {
			val = val.toFixed(0);
			if (val > 0) {
				return "<font color='red'>" + val + "</font>";
			} else if (val < 0) {
				return "<font color='green'>" + val + "</font>";
			} else {
				return val;
			}

		};

		this.numFmtRenderer1 = function(val) {
			val = val.toFixed(2);
			if (val > 0) {
				return "<font color='red'>" + val + "%</font>";
			} else if (val < 0) {
				return "<font color='green'>" + val + "%</font>";
			} else {
				return val;
			}
		};

		this.actionRenderer = function(val) {
			var htmlStr = '<a href="#" onclick="showDetail(\'' + val
					+ '\')">详情</a>';
			htmlStr += "&nbsp;&nbsp;&nbsp;&nbsp;";
			htmlStr += '<a href="#" onclick="delUser(\'' + val + '\')">删除</a>';
			return htmlStr;
		};

		this.store = new Ext.data.JsonStore({
			url : 'test.jsp',
			autoLoad : false,
			totalProperty : 'totalCount',
			root : "users",
			fields : [ {
				name : 'createDate'
			}, {
				name : 'benjin'
			}, {
				name : 'szzs'
			}, {
				name : 'cangwei'
			}, {
				name : 'chenben'
			}, {
				name : 'fudongkuiyin_d'
			}, {
				name : 'fudongkuiyin_w'
			}, {
				name : 'fudongkuiyin_m'
			}, {
				name : 'fudongkuiyin_y'
			}, {
				name : 'fudongkuiyin_t'
			}, {
				name : 'fudongkuiyinbili_d'
			}, {
				name : 'fudongkuiyinbili_w'
			}, {
				name : 'fudongkuiyinbili_m'
			}, {
				name : 'fudongkuiyinbili_y'
			}, {
				name : 'fudongkuiyinbili_t'
			}, {
				name : 'szzsbili'
			}, {
				name : 'szzsbili_w'
			}, {
				name : 'szzsbili_t'
			}

			]
		});
		this.columns = [ new Ext.grid.RowNumberer(), {
			header : '日期',
			dataIndex : 'createDate'
		}, {
			header : '上证指数',
			dataIndex : 'szzs'
		}, {
			header : '日幅%',
			dataIndex : 'szzsbili',
			renderer : this.numFmtRenderer1
		}, {
			header : '周幅%',
			dataIndex : 'szzsbili_w',
			renderer : this.numFmtRenderer1
		}, {
			header : '累计幅%',
			dataIndex : 'szzsbili_t',
			renderer : this.numFmtRenderer1
		}, {
			header : '仓位%',
			dataIndex : 'cangwei',
			renderer : this.numFmtRenderer
		}, {
			header : '成本',
			dataIndex : 'benjin',
			renderer : this.numFmtRenderer
		}, {
			header : '市值',
			dataIndex : 'chenben',
			renderer : this.numFmtRenderer
		}, {
			header : '日浮亏',
			dataIndex : 'fudongkuiyin_d',
			renderer : this.numFmtRenderer
		}, {
			header : '日盈亏%',
			dataIndex : 'fudongkuiyinbili_d',
			renderer : this.numFmtRenderer1
		}, {
			header : '周盈亏',
			dataIndex : 'fudongkuiyin_w',
			renderer : this.numFmtRenderer
		}, {
			header : '周盈亏%',
			dataIndex : 'fudongkuiyinbili_w',
			renderer : this.numFmtRenderer1
		}, {
			header : '月盈亏 	',
			dataIndex : 'fudongkuiyin_m',
			renderer : this.numFmtRenderer
		}, {
			header : '月盈亏%',
			dataIndex : 'fudongkuiyinbili_m',
			renderer : this.numFmtRenderer1
		}, {
			header : '年盈亏',
			dataIndex : 'fudongkuiyin_y',
			renderer : this.numFmtRenderer
		}, {
			header : '年盈亏%',
			dataIndex : 'fudongkuiyinbili_y',
			renderer : this.numFmtRenderer1
		}, {
			header : '总盈亏',
			dataIndex : 'fudongkuiyin_t',
			renderer : this.numFmtRenderer
		}];
		this.bbar = new Ext.PagingToolbar({
			pageSize : 20,
			store : this.store,
			displayInfo : true,
			buttons : [ '-', {
				text : "添加",
				handler : function() {
					var add_winForm = new Ext.form.FormPanel({
						width : 360,
						height : 700,
						plain : true,
						layout : "form",
						defaultType : "textfield",
						labelWidth : 75,
						baseCls : "x-plain",
						fileUpload: true,
						//锚点布局-  
						buttonAlign : "center",
						bodyStyle : "padding:0 0 0 0",
						items : [ {
							fieldLabel : "日期",
							xtype: "datefield",
							width : 160,
							id : "createDate",
							name : "createDate",
							format : "Y-m-d",
							value:Ext.util.Format.date(new Date(), "Y-m-d")
						},{
							fieldLabel : "上证指数",
							width : 160,
							id : "szzs",
							name : "szzs",
							value:2050
						},{
							fieldLabel : "市值",
							width : 160,
							id : "shizhi",
							name : "shizhi",
							value:120000
						},{
							fieldLabel : "资产",
							width : 160,
							id : "zichan",
							name : "zichan",
							value:170000
						},{
				            xtype: 'fileuploadfield',
				            id: 'form-file',
				            emptyText: 'Select a file',
				            fieldLabel: '文件',
				            name: 'photo-path'
				        }]
					});

					var syswin = new Ext.Window({
						title : "新建数据",
						width : 360,
						height : 200,
						plain : true,
						iconCls : "addicon",
						//不可以随意改变大小  
						resizable : false,
						//是否可以拖动  
						//draggable:false,  
						collapsible : true, //允许缩放条  
						closeAction : 'close',
						closable : true,
						//弹出模态窗体  
						modal : 'true',
						buttonAlign : "center",
						bodyStyle : "padding:10px 0 0 10px",
						items : [ add_winForm ],
						buttons : [ {
							text : "上传",
							minWidth : 70,
							handler : function() {
								if (add_winForm.getForm().isValid()) {
									add_winForm.getForm().submit({
										url : '/stock/ReportServlet',
										waitTitle : '请稍等...',
										waitMsg : '正在上传...',
										params : {
											m : "uploadData"
										},
										success : function(fp, o) {
											
										},
										failure : function() {
											Ext.Msg.alert('信息提示', '上传失败！');
										}
									});

								}
							}
						},{
							text : "保 存",
							minWidth : 70,
							handler : function() {
								if (add_winForm.getForm().isValid()) {
									add_winForm.getForm().submit({
										url : '/stock/ReportServlet',
										waitTitle : '请稍等...',
										waitMsg : '正在提交信息...',
										params : {
											m : "addData"
										},
										success : function(fp, o) {
											if (o.result.success == true) {
												syswin.close();
												var dataGrid=Ext.getCmp("userDataGrid");
												dataGrid.removeAllData();
												dataGrid.loadData('/stock/ReportServlet?m=getData');
												Ext.Msg.alert('信息提示', '添加成功！');
											} else {
												Ext.Msg.alert('信息提示', '添加时出现异常！');
											}
										},
										failure : function() {
											Ext.Msg.alert('信息提示', '添加失败！');
										}
									});

								}
							}
						}, {
							text : "关 闭",
							minWidth : 70,
							handler : function() {
								syswin.close();
							}
						} ]

					});

					syswin.show();
				}
			} ]
		});

		com.test.cmp.UserWeekGrid.superclass.initComponent.call(this);
	},
	loadData : function(dataUrl) {
		this.store.proxy.setUrl(dataUrl);
		this.store.load();
	},
	removeAllData : function() {
		this.store.removeAll();
	},
	showUserDetail : function() {
		var selRecord = this.getSelectionModel().getSelected();
		if (!selRecord) {
			Ext.Msg.show({
				title : '提示',
				msg : "请选中一条记录",
				buttons : Ext.Msg.OKCANCEL,
				icon : Ext.Msg.WARNING
			});
			return;
		}
		if (!this.userDetailWindow) {
			this.userDetailWindow = new com.test.cmp.UserDetailWindow();
		}
		this.userDetailWindow.setData(selRecord);
		this.userDetailWindow.show();
	}
});
Ext.reg("userweekgrid", com.test.cmp.UserWeekGrid)