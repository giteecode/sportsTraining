<template>
    <div class="login_view">
<div class="backgif">

    <div class="form" style="z-index: 1;">
    <div class="logo"></div>
    <div class="projectName">{{projectName}}</div>
    <div class="tip">请登录以继续</div>
     

 
				<div class="form-item userName" v-if="loginType==1">
					<div class="label">
						账号：
					</div>
					<el-input class="item-input" v-model="loginForm.username" placeholder="请输入账号" name="username" />
				</div>

   
				<div class="form-item password" v-if="loginType==1">
					<div class="label">
						密码：
					</div>
					<el-input class="item-input" v-model="loginForm.password" type="password" show-password placeholder="请输入密码" @keydown.enter.native="handleLogin"  />
				</div>



				<div class="form-item roles" v-if="userList.length>1">
				  <el-radio class="role" v-for="(item,index) in userList" :key="index" v-model="loginForm.role"
					:label="item.roleName">{{item.roleName}}</el-radio>
				</div>

				<el-button class="login" v-if="loginType==1" @click="handleLogin">登录</el-button>



    <div class="forget-row">
        <div class="register-row">
                    <el-button class="register" @click="handleRegister('yonghu')">注册用户</el-button>

        </div>
       
    </div>
    <div class="form-circle1"></div>
    <div class="form-circle2"></div>
</div>
    <div class="gifdiv">
        
    </div>
<div class="circle1"></div>
<div class="circle2"></div>

</div>

    </div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
	} from "vue";
	import {
		useStore
	} from 'vuex';
	const store = useStore()
    const projectName = ref(`基于Springboot的体育运动训练与成绩管理系统`)
	import menu from '@/utils/menu'
	const userList = ref([])
	const menus = ref([])
	const loginForm = ref({
		role: '',
		username: '',
		password: ''
	})
	const tableName = ref('')
	const loginType = ref(1)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//注册
    const handleRegister = (tableName) => {
    	context?.$router.push(`/${tableName}Register`)
    }
	const handleLogin = () => {
		if (!loginForm.value.username) {
			context?.$toolUtil.message('请输入用户名', 'error')
			return;
		}
		if (!loginForm.value.password) {
			context?.$toolUtil.message('请输入密码', 'error')
			return;
		}
		if (userList.value.length > 1) {
			if (!loginForm.value.role) {
				context?.$toolUtil.message('请选择角色', 'error')
				verifySlider.reset()
				return;
			}
			for (let i = 0; i < menus.value.length; i++) {
				if (menus.value[i].roleName == loginForm.value.role) {
					tableName.value = menus.value[i].pathName||menus.value[i].tableName;
				}
			}
		} else {
			tableName.value = userList.value[0].pathName||userList.value[0].tableName;
			loginForm.value.role = userList.value[0].roleName;
		}
		login()
	}
	const login = () => {
		context?.$http({
			url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
			method: 'post'
		}).then(res => {
			context?.$toolUtil.storageSet("frontToken", res.data.token);
			context?.$toolUtil.storageSet("frontRole", loginForm.value.role);
			context?.$toolUtil.storageSet("frontSessionTable", tableName.value);
			store.dispatch('user/getSession')
			let path = context?.$toolUtil.storageGet('toPath')
			if (path && path!='/login') {
				context?.$router.replace(path)
				context?.$toolUtil.storageRemove('toPath')
				return
			}
			context?.$router.replace(`/index/${tableName.value}Center`)
		},err=>{
		})
	}
	//获取菜单
	const getMenu= async ()=> {
		let arr = menu.list()
		if(!arr){
			let res = await context?.$http.get("menu/list")
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
			arr = JSON.parse(res.data.data.list[0].menujson)
		}
		menus.value = arr
		for (let i = 0; i < menus.value.length; i++) {
			if (menus.value[i].hasFrontLogin=='是') {
				userList.value.push(menus.value[i])
			}
		}
    }
	//初始化
	const init = async () => {
		await getMenu();
		loginForm.value.role = userList.value[0].roleName
	}
	onMounted(()=>{
		init()

	})
</script>

<style lang="scss">
	.login_view {
        background-image: url("http://localhost:8080/sportsTraining/file/a3f7bd5b156b4029821cff6fc65ec515.png")!important;
	}
.login_view {
    background: #F1FAFF;
    height: 100vh;
    display: flex;
   
    justify-content: center;
    position: relative;
    overflow: hidden;
    align-items:center;
    background-size:100% 100%;
}

.login_view .form {
    background: red;
   width:600px!important;
height:580px!important;
    box-shadow: none;
    padding-bottom:30px;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    position: relative;
    overflow: hidden;
}

.login_view .projectName {
   
    font-weight: 700;
    width: 100%;
    text-align: center;
    margin-top: 20px;
}

.login_view .tip {
    font-size: 14px;
    width: 100%;
    text-align: center;
    margin-top: 10px;
    color: #666;
}

.login_view -item {
    display: flex;
    border: 1px solid #eee;
    padding: 0px;
    border-radius: 6px;
}

.login_view .el-input__wrapper {
    border: none;
    outline: none;
    box-shadow: none!important;
}





.login_view .form-item.code {
    margin-right: 140px;
    position: relative;
}

.login_view .form-item.roles {
    position: relative;
    background: none;
    border: none;
    display: flex;
    gap: 20px;

    margin-top: 50px;
}

.login_view .el-radio.role {
    background: none;
    border: 0px solid #eee;
    padding: 10px;
    flex: 1;
    margin-right: 0;
    border-radius: 8px;
}

.login_view .form-item.roles:before {
    content: '选择角色';
    position: absolute;
    top: -40px;
    left: 0;
}

.login_view .login {
    width: 100%;
   
    height: 50px;
    background: var(--theme);
    border: none;
    color: #fff;
    font-size: 18px;
}
.login_view .el-button.face{
    width: 100%;
    margin-top: 20px;
    height: 50px;
    background: var(--theme);
    border: none;
    color: #fff;
    font-size: 18px;
    margin-left: 0;
}

.login_view .forget-row {
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.login_view .register {
    background: none;
    border: none;
}

.login_view .forget {

    border: 0;
    width:400px;
    margin:10px auto;
    height: 50px;
    line-height:50px;
background: #FBFD86;
border-radius: 30px 30px 30px 30px;
}

.login_view .form-circle1 {
    position: absolute;
    right: -34px;
    top: -32px;
    background: #dff6e9;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}

.login_view .form-circle2 {
    position: absolute;
    left: -34px;
    bottom: -32px;
    background: #f7efeb;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}

.login_view .logo {
    background: url(http://localhost:8080/sportsTraining/file/889d208e5ddd4107bce6600713cdf6d1.webp);
    background-size: 100% 100%;
    width: 80px;
    height: 80px;
    animation: float 3s ease-in-out infinite;
}

.login_view .circle1 {
    position: absolute;
    left: 100px;
    top: 100px;
    background: #daf4dc;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    z-index: 1;
    animation: float 4s ease-in-out infinite;
}

.login_view .circle2 {
    position: absolute;
    right: 100px;
    bottom: 100px;
    background: #EBEFD9;
    width: 300px;
    height: 300px;
    border-radius: 50%;
    z-index: 0;
    animation: float 5s ease-in-out infinite;
}

@keyframes float{
    0%,100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-10px);
    }
 }


.circle1 {
    display:none;
}

.circle2 {
    display:none;
}


.login_view .form {
    
   width:50%!important;
    
    height:100%!important;
  
    background:#FFFFFF!important;
  
box-shadow: 0px 6px 30px 0px #3B432C;
border-radius: 30px 0px 0px 30px;
       overflow-y: auto; 
  
  scrollbar-width: none; 
  -ms-overflow-style: none; 
  
}
.login_view .logo {
    display:none;
}


.form-circle1 {
        display:none;
}

.form-circle2 {
        display:none;
}

.form-circle2 {
            display:none;
}

.login_view .projectName {
     width: 100%;
height: 25px;
font-family: Source Han Sans, Source Han Sans!important;
font-weight: 700!important;
font-size:24px!important;
max-font-size:28px!important;
min-font-size:20px!important;
color: #000000;
line-height: 20px;
text-align: center;
font-style: normal;
text-transform: none;

}

.login_view .tip {
        display:none
}

.login_view .form-item{
    width:400px!important;
    margin-left:120px;
    align-items: flex-start!important;   
    background: #F8F8F8;
    border: 0px solid #eee!important;
}


.login_view svg {
        width:20px!important;
        
}

.login_view .el-input.item-input {
        height:40px!important;
}



.login_view .form {
 
    overflow: auto; 
    position: relative; 
    
}

.login_view .form-item.userName {
    margin:40px auto;
}




.login_view button.el-button.login {
    margin-top:10px;
    
}

.login_view button.el-button.face {
    margin-top:20px!important;
    
}
.login_view .form-item{
    margin:0px auto!important;
}

.login_view .projectName {
    margin:50px auto 40px auto!important;
}
.login_view .forget-row {
    width:400px!important;
    margin-left:auto;
    margin-right:auto
}



.login_view button.el-button.register {
    padding:0px;
}
.login_view .label {
    background:none!important;
    border: 1px solid #9CA3AF;
    border-right:none!important;
    background: none;
    border-radius: 5px 0 0 5px;
}

.login_view .el-input.item-input {
    height:40px!important;
   border: 1px solid #9CA3AF;
 
      border-radius: 0 5px 5px 0;
}

.login_view .code-info {
      border-radius: 5px 0 0 5px!important;
}
.login_view .wenzi {
    margin:40px auto 5px auto;
   width: 400px;
height: 13px;
font-family: Source Han Sans, Source Han Sans;
font-weight: 400;
font-size: 14px;
color: #9CA3AF;
line-height: 12px;
text-align: left;
font-style: normal;
text-transform: none;
}


.login_view ::before {
 display:none;
}


.login_view .form-item.roles {
 background: #FFFFFF;
border-radius: 5px 5px 5px 5px;

 width:400px!important;
 height:40px !important;

}

.login_view .form-item.roles .label{
 
border:none!important;
}

.login_view .el-select {
 border:none!important;
 margin-top:auto;
 margin-bottom:auto;
}

.login_view .el-select__selection {
  border:none!important;
 margin-top:auto!important;
 margin-bottom:auto
}
.login_view i.el-icon {
color:#0949f7;
}










.login_view button.el-button.login span {
font-family: Source Han Sans, Source Han Sans;
font-weight: 700;
font-size: 20px;
color: #FFFFFF;
line-height: 20px;
text-align: left;
font-style: normal;
text-transform: none;
}

.login_view button.el-button.face span{
font-family: Source Han Sans, Source Han Sans;
font-weight: 700;
font-size: 20px;
color: #FFFFFF;
line-height: 20px;
text-align: left;
font-style: normal;
text-transform: none;
}
.login_view .form-item.roles {
    background: #F8F8F8;
    border-radius: 20px;
    width: 400px !important;
    height: 40px !important;
  justify-content: flex-start;  
 gap:1px;
        align-items:center!important;
}
.login_view .el-select__wrapper {
   
    font-size: 14px;
    gap: 6px;
    line-height: 40px;
    min-height: 40px;
    border:1px solid #E7EAE9;
}
.login_view .form-item.userName {
   background: #F8F8F8;
    border-radius: 20pxpx;
    border: 0px solid var(--theme2)!important;
    width: 400px !important;
    height: 40px !important;
 display:flex;
  justify-content: flex-start;  

 gap:5px;

}

.login_view .form-item.password {
   background: #F8F8F8;
    border-radius: 5px 5px 5px 5px;
    border: 0px solid var(--theme2) !important;
    width: 400px !important;
    height: 40px !important;
    display:flex;
  justify-content: flex-start;  

 gap:5px;

}

.login_view .form-item.code {
   background: #F8F8F8;
    border-radius: 5px 5px 5px 5px;
    border: 0px solid var(--theme2) !important;
    width: 400px !important;
    height: 40px !important;
  display:flex;
  justify-content: flex-start;  

 gap:5px;

    margin-bottom:30px;
}
.login_view .label {
 border:0px!important;
 width:100%!important;
 height:20px!important;
}
.login_view .el-select {
    border: none !important;
  
}

.login_view .el-input.item-input {
    height: 40px !important;
    border: 2px solid #E7EAE9;
    border-radius:5px;
    padding-left:12px;
    padding-right:12px;
}
.login_view .code-info {
    width: 120px;
    background: linear-gradient( 136deg, #F2FFAE 0%, #C2CBCA 100%);
    flex-shrink: 0;
    display: flex
;
    justify-content: space-around;
    font-size: 20px;
    position: absolute;
    top:0px;
    right: -0px !important;
    border-radius: 10px 10px 0px 0px!important;
    height: 40px !important;
    line-height:40px;
}

.login_view button.el-button.forget span {
 color:#3B432C;
}







.login_view .el-select__wrapper{
    background:none!important;
    border:none;
    box-shadow:none;
}

.login_view .el-select__suffix {
    display:none;
}

.login_view .el-input.item-input {
 background:none!important;
    border:none;
    box-shadow:none;
}

.login_view .el-input__wrapper {
   background:none!important;
    padding-left:0px;
}

.login_view .backgif {
    width:1320px;
    height:650px;
 
    background-size:100% 100%;

    display:flex;
    flex-wrap:nowrap;
    align-items:flex-end;
    justify-content:space-between;
   
}

.login_view .gifdiv {
    width:50%;
    height:100%;
     background:url('http://localhost:8080/sportsTraining/file/ed4301675bd844ba968a859889e0679f.png')no-repeat;
        background-size:100% 100%;
}
.login_view .form-item{
border-radius:  20px!important;
margin-bottom:30px!important;
border: 0px solid #017ABC!important;
padding:0px 10px;
}
.login_view .label {
    width:40%!important;
    color:#3B432C;
    line-height:30px;
    background: none!important;
border-radius: 90px 90px 90px 90px;
    height:30px!important;
    margin:auto 0px;
}

.login_view .code-info {
    width: 120px;
    background:#3B432C;
    flex-shrink: 0;
    display: flex;
    justify-content: space-around;
    font-size: 20px;
    position: absolute;
    right: -140px;

     right: -0px!important;
    border-radius:5px!important;
    height:40px!important;
}
.login_view button.el-button.login {
    width: 400px;
height: 60px;
background: #3B432C;
border-radius: 5px 5px 5px 5px;
margin-left:auto!important;
margin-right: auto!important;
    display: block!important;
    
}


.login_view button.el-button.face {
        width: 400px!important;
height: 60px!important;
background: #3B432C;
border-radius: 5px 5px 5px 5px!important;
    margin-left:auto !important;
    margin-right:auto !important;
    display: block!important;
}

.login_view .face {
    width: 400px !important;
    height: 60px !important;
    background: #3B432C;
    border-radius: 5px 5px 5px 5px !important;
    margin-left: auto !important;
    margin-right: auto !important;
    display:flex;
    justify-content:center;
    align-items:center;
        font-family: Source Han Sans, Source Han Sans;
    font-weight: 700;
    font-size: 20px;
    color: #fff;
}

.login_view label.el-radio.role {

 color:#A9A9A9!important;
}
</style>