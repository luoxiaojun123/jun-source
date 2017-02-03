//生成菜单
var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{}},
	template:[
	          '<li>',
	          '<a v-if="item.type === 1" href="javascript:;">',
	          '<i v-if="item.icon != null" :class="item.icon"></i>',
	          '<span>{{item.name}}</span>',
	          '<i class="fa fa-angle-left pull-right"></i>',
	          '</a>',
	          '<ul v-if="item.type === 1" class="treeview-menu">',
	          '<menu-item :item="item" v-for="item in item.list"></menu-item>',
	          '</ul>',
	          '<a v-if="item.type === 2" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
	          '</li>'
	].join('')
});

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		menuList:{},
		main:"main",
		password:'',
		newPassword:'',
		newPassword2:'',
        navTitle:"控制台"
	},
	methods: {
		getMenuList: function (event) {
			$.getJSON("menu/getMenuList?_"+$.now(), function(r){
				vm.menuList = r.result;
			});
		},
		getUser: function(){
			$.getJSON("user/getUserInfo?_"+$.now(), function(r){
				if(r.resCode!='200'){
					layer.alert(r.resMsg);
				}
				vm.user = r.result;
			});
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['620px', '320px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					if(vm.newPassword2!=vm.newPassword){
						vm.newPassword2='';
						layer.alert("新密码输入二次不一致，请重新输入");
						return;
					}
					var data = "oldPassword="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: "user/updatePassword",
					    data: data,
					    dataType: "json",
					    success: function(result){
							if(result.resCode == '200'){
								layer.close(index);
								layer.alert('修改成功', function(index){
									location.reload();
								});
							}else{
								layer.alert(result.resMsg);
							}
						}
					});
	            }
			});
		}
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});



function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 1){
			routerList(router, menu.list);
		}else if(menu.type == 2){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
