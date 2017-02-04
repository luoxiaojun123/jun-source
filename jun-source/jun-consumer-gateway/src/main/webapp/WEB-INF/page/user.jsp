<!DOCTYPE html>
<html>
<head>
<title>管理员列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.inc" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
</head>
<body>
<div id="rrapp">
	<div class="grid-btn">
		<div class="form-group col-sm-2">
			<input type="text" class="form-control" v-model="userName" @keyup.enter="query" placeholder="用户名">
		</div>
	</div>
	<div class="grid-btn">
		<shiro:hasPermission name="sys:user:query">
		<button type="button"  class="btn btn-default" @click="query">查询</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:save">
		<a class="btn btn-default" href="userAdd">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:update">
		<button type="button"  class="btn btn-default" @click="update">修改</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:delete">
		<button type="button" class="btn btn-default" @click="del">删除</button>
		</shiro:hasPermission>
	</div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script src="${CONTEXT}js/sys/user.js?_${date.systemTime}"></script>
</body>
</html>