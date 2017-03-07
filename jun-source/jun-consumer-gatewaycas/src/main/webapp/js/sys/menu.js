$(function () {
    $("#jqGrid").jqGrid({
        url: 'getAllMenuList',
        datatype: "json",
        colModel: [			
			{ label: '菜单ID', name: 'id', width: 40, key: true,hidden:true },
			{ label: '菜单名称', name: 'name', width: 60 },
			{ label: '上级菜单', name: 'parentName', width: 60 },
			{ label: '菜单图标', name: 'icon', width: 50, formatter: function(value, options, row){
				return value == null ? '' : '<i class="'+value+' fa-lg"></i>';
			}},
			{ label: '菜单URL', name: 'url', width: 100 },
			{ label: '授权标识', name: 'perms', width: 100 },
			{ label: '类型', name: 'type', width: 50, formatter: function(value, options, row){
				if(value == 1){
					return '<span class="label label-primary">目录</span>';
				}
				if(value == 2){
					return '<span class="label label-success">菜单</span>';
				}
				if(value == 3){
					return '<span class="label label-warning">按钮</span>';
				}
			}},
			{ label: '排序号', name: 'order_num', width: 50}                   
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "pageInfo.list",
            page: "pageInfo.pageNum",
            total: "pageInfo.pages",
            records: "pageInfo.total"
        },
        prmNames : {
            page:"page", 
            rows:"rows", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		name:null
	},
	methods: {
		update: function (event) {
			var menuId = getSelectedRow();
			if(menuId == null){
				return ;
			}
			location.href = "menuAdd?menuId="+menuId;
		},
		query:function(event){
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'name': vm.name},
                page:1 
            }).trigger("reloadGrid");
		},
		del: function (event) {
			var menuIds = getSelectedRows();
			if(menuIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "delete",
				    data: JSON.stringify(menuIds),
				    success: function(r){
				    	if(r.resCode == '200'){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.resMsg);
						}
					}
				});
			});
		}
	}
});