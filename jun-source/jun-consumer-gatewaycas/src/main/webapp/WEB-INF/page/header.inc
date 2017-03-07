 <%
	String path = request.getContextPath();
	String CONTEXT = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("CONTEXT",CONTEXT);
 %>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${CONTEXT}statics/css/bootstrap.min.css">
<link rel="stylesheet" href="${CONTEXT}statics/css/font-awesome.min.css">
<link rel="stylesheet" href="${CONTEXT}statics/plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="${CONTEXT}statics/plugins/ztree/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="${CONTEXT}statics/css/main.css">
<script src="${CONTEXT}statics/libs/jquery.min.js"></script>
<script src="${CONTEXT}statics/plugins/layer/layer.js"></script>
<script src="${CONTEXT}statics/libs/bootstrap.min.js"></script>
<script src="${CONTEXT}statics/libs/vue.min.js"></script>
<script src="${CONTEXT}statics/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="${CONTEXT}statics/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="${CONTEXT}statics/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="${CONTEXT}js/common.js"></script>