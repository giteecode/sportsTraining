<template>
    <div class="forget_view">
<div class="backgif">
<div class="form" style="z-index: 1;">
    <div class="pageTitle">{{pageTitle}}</div>
				<div class="tab_view">
					<div class="tab" :class="pageType==1?'tab_active':''">输入账号</div>
					<div class="tab" :class="pageType==2?'tab_active tab_active1':''"> 输入密保</div>
					<div class="tab" :class="pageType==3?'tab_active tab_active1 tab_active2':''">重置密码</div>
				</div>

            <div class="form-box">
            </div>

     
					<el-button v-if="pageType==1" class="get_btn" @click="getSecurity">获取密保</el-button>
					<el-button v-if="pageType==2" class="valid_btn" @click="validateSecurity">确认密保</el-button>
					<el-button v-if="pageType==3" class="update_btn" @click="updatePassword">重置密码</el-button>

   <div class="back" @click="close">已有账号，直接登录</div>
    <div class="form-circle1"></div>
    <div class="form-circle2"></div>
</div>
         <div class="gifdiv">
        
    </div>
</div>
<div class="circle1"></div>
<div class="circle2"></div>
    </div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
        onMounted,
		computed,
	} from 'vue';
    import { useRoute } from 'vue-router'
    const route = useRoute()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
    onMounted(()=>{
	})
	const pageType = ref(1)
	const pageTitle = computed(()=>{
		return pageType.value==1?'输入账号':pageType.value==2?'输入密保':'重置密码'
	})
	const forgetForm = ref({})
	const userForm = ref({})
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
</script>

<style lang="scss">
	.forget_view {
        background-image: url("http://localhost:8080/sportsTraining/file/ab4d645a36394460bd4a1dcb03c19174.png")!important;
	}
.forget_view {
  background-color:none;
    
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    overflow: hidden;
      background: #F1FAFF;
    background-size: cover;
  background-position: center;
  background-attachment: fixed;
 height:100vh ;
}

.forget_view .form {
    background: #fcfcf4;
    width: 600px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    padding: 50px 60px 30px 30px;
    border-radius: 24px;
    position: relative;
    overflow: hidden;
    text-align: center;
}

.forget_view .projectName {
    font-size: 24px;
    font-weight: 700;
    width: 100%;
    text-align: center;
    margin-top: 20px;
}
.forget_view .logo {
    background: url(http://localhost:8080/sportsTraining/file/889d208e5ddd4107bce6600713cdf6d1.webp);
    background-size: 100% 100%;
    width: 80px;
    height: 80px;
    animation: float 3s ease-in-out infinite;
    margin: 0 auto;
}
@keyframes float{
    0%, 100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-10px);
    }
}

.forget_view .circle1 {
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

.forget_view .circle2 {
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
.forget_view .form-circle1 {
    position: absolute;
    right: -34px;
    top: -32px;
    background: #dff6e9;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}

.forget_view .form-circle2 {
    position: absolute;
    left: -34px;
    bottom: -32px;
    background: #f7efeb;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}


.forget_view .register_form {
    text-align: left;
    display: flex;
    flex-direction: column;
    gap: 20px;
    margin-top: 40px;
    padding: 0 50px;
}

.forget_view .list_item {
    display: flex;
   
    padding: 10px;
}

.forget_view .item_label {
    min-width:15%;
    text-align: right;
}

.forget_view .register {
    background: var(--theme);
    border: none;
    color: #fff;
    width: 60%;
    margin-top: 30px;
    height: 40px;
    font-size: 18px;
}

.forget_view .back {
    width: 50%;
    margin: 20px auto;
}

.forget_view .tab {
    width: calc(33.3333%);
    padding: 16px 0px;
    box-sizing: border-box;
    text-align: center;
    font-weight: 700;
font-size: 16px;
color: #000000;
}
.forget_view .tab.tab_active {
    width: calc(33.3333%);
    padding: 16px 0px;
    box-sizing: border-box;
    text-align: center;
    clip-path: polygon(0% 0%, 92% 0%, 100% 50%, 92% 100%, 0px 100%, 8% 50%);
    background: #FF6F2C;
    color: #FFFFFF;
}
.forget_view .tab_view {
    width: 50%;
    display: flex;
    align-items: center;
    padding: 30px 0px;
    font-size: 16px;
}



.forget_view .el-input.list_inp {
    height: 40px;
}

.forget_view .el-select__wrapper {
    height: 40px;
}

.forget_view button.el-button {
    background: #3B432C;
    color: #fff;
    width: 50%;
    border: none;
    height: 40px;
    margin-top: 30px;
    border-radius: 30px;
}

.forget_view  .form {
    display: block;
    overflow: auto;
    position: relative;
}

.forget_view  .form {
    width: 813px !important;
    height: 1080px;
    background: #FFFFFF !important;
}

.forget_view .form {
    display: block;
    overflow: auto;
    position: relative;
}

.forget_view  .form {
    width: 813px !important;
    height: 1080px;
    background: #FFFFFF !important;
}

.circle1 {
 display:none
}

.form-circle1 {
 display:none
 
}

.form-circle2 {
 display:none
 
}


.forget_view .tab {
    clip-path: none !important;
 background: #DCDCDC;
box-shadow:none;
border-radius: 30px!important;
}


.forget_view .tab.tab_active {

box-shadow: none;
border-radius: 30px!important;
}

.forget_view .tab_view {
 width:60%!important;
 margin:auto;
gap:10px;
}

.forget_view .list_item {
     width:90%;
     margin:auto;
}

.forget_view .item_label {
 width:15%;

text-align: left!important;
 margin-bottom:5px;
font-weight: 400;
font-size: 14px;
color: #9CA3AF;
font-style: normal;
text-transform: none;
}



.forget_view button.el-button.get_btn span {
font-weight: 700;
font-size: 24px;
color: #FFFFFF;
line-height: 20px;
text-align: left;
font-style: normal;
text-transform: none;
}

.forget_view .el-input.list_inp {
 width:510px;
}

.forget_view .pageTitle {
 margin-top:20%!important;
}

.forget_view .el-select.list_sel {
 width:510px;
}
.forget_view .form {
       
   width:50%!important;
    
    height:100%!important;
    margin-left: 0px !important;
    margin-top: 10px !important;
    background: #FFFFFF !important;
box-shadow: 0px 6px 30px 0px #3B432C;
border-radius: 30px 0px 0px 30px;
    padding:0px;
  
  scrollbar-width: none; 
  -ms-overflow-style: none; 
}


.forget_view .list_item {
  margin:30px auto!important;
  width:50%!important;
  padding:1px;
  padding-left:17px;
background: #FFFFFF!important;
border-radius: 5px 5px 5px 5px!important;
box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color)) inset;
}








.circle2 {
  display:none;
}

.forget_view .item_label {
min-width:40%!important;
color: #000;
}

.forget_view .el-input__wrapper {
box-shadow:none;
}

.forget_view .el-select__wrapper.el-tooltip__trigger.el-tooltip__trigger {
box-shadow:none;
}


.forget_view button.el-button.get_btn span{
   color: #FFFFFF!important;
}

.forget_view button.el-button.valid_btn {
width:400px;
height:60px;
   
}
.forget_view button.el-button.valid_btn span{
 font-weight: 700;
    font-size: 24px;
    color: #FFFFFF;
    line-height: 20px;
    text-align: left;
    font-style: normal;
    text-transform: none;
}

.forget_view button.el-button.update_btn {
width:400px;
height:60px;
}
.forget_view button.el-button.update_btn span{
 font-weight: 700;
    font-size: 24px;
    color: #FFFFFF;
    line-height: 20px;
    text-align: left;
    font-style: normal;
    text-transform: none;
}


.header_number.header_number1 {
width: 40px;
height: 40px;
background: #000000;
border-radius: 5px 5px 5px 5px;
color:#fff;
line-height:40px;
font-weight: 700;
font-size: 28px;
}
 
.header_number.header_number2 {
width: 40px;
height: 40px;
background: #FEBE54;
border-radius: 5px 5px 5px 5px;
line-height:40px;
font-weight: 700;
font-size: 28px;
color: #FFFFFF;
}

.forget_view .tab_view {

justify-content: space-between;
}

.forget_view .header_number {
width: 40px;
height: 40px;
background: #000000;
border-radius: 5px 5px 5px 5px;
color:#fff;
line-height:40px;
font-weight: 700;
font-size: 28px;
}






.forget_view button.el-button.get_btn {
    width: 400px!important;
    height: 60px!important;
}
.forget_view .el-input.list_inp {
    width:100%;
    border-radius: 5px 5px 5px 5px;
border: 1px solid #E7EAE9;
}

.forget_view .el-select.list_sel {
        width:100%;
      border-radius: 5px 5px 5px 5px;
border: 1px solid #E7EAE9;
}
.forget_view .list_item{
    box-shadow:none;
}



.forget_view .rightimg {
    width:450px;
    height:550px;
    border-radius:20px;
    background:url(http://localhost:8080/sportsTraining/file/e13654edfb0c408e94a59ffc9e43dce0.png) no-repeat;
    background-size:cover;
   
}





.forget_view  .el-input__wrapper {
   background:none;
   box-shadow:none!important;
   border:none!important;
}

.forget_view .el-input.list_inp{
 background:none;
   box-shadow:none!important;
   border:none!important;
}
.forget_view .el-select.list_sel{
   border:none!important;
}
.forget_view .el-select__wrapper{
    background:none;
}



.forget_view .header_number {
   background: #6F808A!important;
   border-radius:50%!important;
   color:#FFFFFF!important;
}

.header_number.header_number1 {
  background: var(--theme)!important;
   border-radius:50%!important;
   color:#FFFFFF!important;
}

.header_number.header_number2 {
  background: var(--theme)!important;
   border-radius:50%!important;
   color:#FFFFFF!important;
}
  .forget_view .back{
width: 400px;
height: 50px;
background: #FBFD86;
line-height:50px;
   font-weight: 400;
font-size: 20px;
color: #3B432C;
border-radius:30px;
}

.forget_view   .rightimg {
    width: 550px;
    height: 673px;
    border-radius: 20px;
  
    background-size: cover;
}

.forget_view  .backgif {
    width:1320px;
    height:650px;
   
    background-size:100% 100%;

    display:flex;
    flex-wrap:nowrap;
    align-items:flex-end;
    justify-content:space-between;
}

.forget_view  .gifdiv {
    width:50%;
    height:100%;
     background:url('http://localhost:8080/sportsTraining/file/ed4301675bd844ba968a859889e0679f.png')no-repeat;
        background-size:100% 100%;
}
.forget_view .pageTitle {
    font-size: 24px;
    font-weight: 700;
    width: 100%;
    text-align: center;
    margin-top: 20px;
    color:#000000;
    
}
.forget_view .tab_view {
margin-top:20px!important;

border-radius: 10px 10px 0px 0px;

padding:10px!important;
}

.forget_view .list_item {
   background:#F8F8F8!important;
border-radius: 20px!important; 
    border: 0px solid #017ABC;
}

.forget_view .item_label{
   background: none;
border-radius: 90px 90px 90px 90px;
color:#3B432C;
   height:30px;
   line-height:30px;
   margin:auto
}

</style>