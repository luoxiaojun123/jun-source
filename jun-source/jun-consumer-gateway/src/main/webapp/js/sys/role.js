$(function () {
    $("#jqGrid").jqGrid({
        url: 'getPageRoleList',
        datatype: "json",
        colModel: [			
			{ label: '角色ID', name: 'id', width: 45, key: true,hidden:true },
			{ label: '角色名称', name: 'roleName', width: 75 },
			{ label: '备注', name: 'remark', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 80}                   
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
		roleName:null
	},
	methods: {
		update: function (event) {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}
			location.href = "roleAdd?roleId="+roleId;
		},
		query:function(event){
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'roleName': vm.roleName},
                page:1 
            }).trigger("reloadGrid");
		},
		del: function (event) {
			var roleIds = getSelectedRows();
			if(roleIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "delete",
				    data: JSON.stringify(roleIds),
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