$(function () {
    $("#jqGrid").jqGrid({
        url: 'getUserList',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'id', width: 45, key: true,hidden:true },
			{ label: '用户名', name: 'username', width: 75 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value == 2 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
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
		userName:null
	},
	methods: {
		update: function (event) {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			location.href = "userAdd?userId="+userId;
		},
		query:function(event){
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'userName': vm.userName},
                page:1 
            }).trigger("reloadGrid");
		},
		del: function (event) {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "delete",
				    data: JSON.stringify(userIds),
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