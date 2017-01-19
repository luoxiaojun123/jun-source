<!DOCTYPE html>
<html>
<head>
  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <%
	String path = request.getContextPath();
	String CONTEXT = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("CONTEXT",CONTEXT);
  %>  
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>后台管理系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${CONTEXT}statics/css/bootstrap.min.css">
  <link rel="stylesheet" href="${CONTEXT}statics/css/font-awesome.min.css">
  <link rel="stylesheet" href="${CONTEXT}statics/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${CONTEXT}statics/css/all-skins.min.css">
  <link rel="stylesheet" href="${CONTEXT}statics/css/main.css">
</head>
<body class="hold-transition login-page">
<div class="login-box" id="rrapp" v-cloak>
  <div class="login-logo">
    <b>后台管理系统</b>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
      <p class="login-box-msg">管理员登录</p>
      <div v-if="error" class="alert alert-danger alert-dismissible">
        <h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle"></i> {{errorMsg}}</h4>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" v-model="username" placeholder="账号">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" v-model="password" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" v-model="captcha" @keyup.enter="login" placeholder="验证码">
        <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <img alt="如果看不清楚，请单击图片刷新！" class="pointer" :src="src" @click="refreshCode">
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" @click="refreshCode">点击刷新</a>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="button" class="btn btn-primary btn-block btn-flat" @click="login">登录</button>
        </div>
        <!-- /.col -->
      </div>
    <!-- /.social-auth-links -->

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<script src="${CONTEXT}statics/libs/jquery.min.js"></script>
<script src="${CONTEXT}statics/libs/vue.min.js"></script>
<script src="${CONTEXT}statics/libs/bootstrap.min.js"></script>
<script src="${CONTEXT}statics/libs/jquery.slimscroll.min.js"></script>
<script src="${CONTEXT}statics/libs/fastclick.min.js"></script>
<script src="${CONTEXT}statics/libs/app.js"></script>
<script type="text/javascript">
var vm = new Vue({
	el:'#rrapp',
	data:{
		username: '',
		password: '',
		captcha: '',
		error: false,
		errorMsg: '',
		src: 'captcha.jpg'
	},
	beforeCreate: function(){
		if(self != top){
			top.location.href = self.location.href;
		}
	},
	methods: {
		refreshCode: function(){
			this.src = "captcha.jpg?t=" + $.now();
		},
		login: function (event) {
			var data = "username="+vm.username+"&password="+vm.password+"&captcha="+vm.captcha;
			$.ajax({
				type: "POST",
			    url: "login",
			    data: data,
			    dataType: "json",
			    success: function(result){
					if(result.resCode == '200'){//登录成功
						parent.location.href ='index';
					}else{
						vm.error = true;
						vm.errorMsg = result.resMsg;
						vm.refreshCode();
					}
				}
			});
		}
	}
});
</script>
</body>
</html>
