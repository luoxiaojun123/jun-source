<!DOCTYPE html>
<html>
<head>
<title>菜单管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.inc" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
</head>
<body>
<div id="rrapp">
	<div class="grid-btn">
		<div class="form-group col-sm-2">
			<input type="text" class="form-control" v-model="name" @keyup.enter="query" placeholder="菜单名称">
		</div>
	</div>
	<div class="grid-btn">
		<button type="button"  class="btn btn-default" @click="query">查询</button>
		<a class="btn btn-default" href="menuAdd">新增</a>
		<button type="button"  class="btn btn-default" @click="update">修改</button>
		<button type="button" class="btn btn-default" @click="del">删除</button>
	</div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>

<script src="${CONTEXT}/js/sys/menu.js?_${date.systemTime}"></script>
</body>
</html>