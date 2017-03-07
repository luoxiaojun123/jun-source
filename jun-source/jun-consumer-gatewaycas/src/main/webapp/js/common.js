//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

// 工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	// window.location.search 返回当前URL的查询部分
	// 如http://localhost:8080/index.html?id=123 返回?id=123
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};
T.p = url;

// 全局配置
$.ajaxSetup({
	dataType : "json",
	contentType : "application/json",
	cache : false
});

// 重写alert
window.alert = function(msg, callback) {
	parent.layer.alert(msg, function(index) {
		parent.layer.close(index);
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

// 重写confirm式样框
window.confirm = function(msg, callback) {
	parent.layer.confirm(msg, {
		btn : [ '确定', '取消' ]
	}, function() {// 确定事件
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

// 选择一条记录
function getSelectedRow() {
	var grid = $("#jqGrid");
	var rowKey = grid.getGridParam("selrow");
	if (!rowKey) {
		alert("请选择一条记录");
		return;
	}

	var selectedIDs = grid.getGridParam("selarrrow");
	if (selectedIDs.length > 1) {
		alert("只能选择一条记录");
		return;
	}
	var rowData = grid.jqGrid('getRowData', rowKey);
	return rowData.id;
}

// 选择多条记录
function getSelectedRows() {
	var grid = $("#jqGrid");
	var rowKey = grid.getGridParam("selrow");
	if (!rowKey) {
		alert("请选择一条记录");
		return;
	}
	// 获取多行的id
	var selrow = grid.getGridParam('selarrrow');
	// 初始化一个数组
	var columnCodes = [];
	// 选择器index位置  rowKey当前元素
	$(selrow).each(function(index, rowKey) {
		var rowData = grid.jqGrid('getRowData', rowKey);
		columnCodes.push(rowData.id)
	})
	return columnCodes;
}