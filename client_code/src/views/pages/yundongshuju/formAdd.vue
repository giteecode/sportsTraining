<template>
	<div class="edit_view">
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form ref="formRef" :model="form" class="add_form" label-width="120px" :rules="rules">
			<el-row>
				<el-col :span="12">
					<el-form-item label="运动名称" prop="yundongmingcheng">
						<el-input class="list_inp"
                                  v-model="form.yundongmingcheng"
                                  placeholder="运动名称"
                                  type="text"
							      :readonly="!isAdd||disabledForm.yundongmingcheng?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="时长" prop="shizhang">
						<el-input class="list_inp"
                                  v-model="form.shizhang"
                                  placeholder="时长"
                                  type="text"
							      :readonly="!isAdd||disabledForm.shizhang?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="心率" prop="xinlv">
						<el-input class="list_inp"
                                  v-model="form.xinlv"
                                  placeholder="心率"
                                  type="text"
							      :readonly="!isAdd||disabledForm.xinlv?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="消耗卡路里" prop="xiaohaokaluli">
						<el-input class="list_inp"
                                  v-model="form.xiaohaokaluli"
                                  placeholder="消耗卡路里"
                                  type="text"
							      :readonly="!isAdd||disabledForm.xiaohaokaluli?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="训练视频" prop="xunlianshipin">
						<uploads
							:disabled="!isAdd||disabledForm.xunlianshipin?true:false"
							type="file"
							action="file/upload" 
							tip="请上传训练视频" 
							:limit="1" 
							style="width: 100%;text-align: left;"
							:fileUrls="form.xunlianshipin?form.xunlianshipin:''" 
							@change="xunlianshipinUploadSuccess">
						</uploads>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="用户账号" prop="yonghuzhanghao">
						<el-input class="list_inp"
                                  v-model="form.yonghuzhanghao"
                                  placeholder="用户账号"
                                  type="text"
							      :readonly="!isAdd||disabledForm.yonghuzhanghao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="用户姓名" prop="yonghuxingming">
						<el-input class="list_inp"
                                  v-model="form.yonghuxingming"
                                  placeholder="用户姓名"
                                  type="text"
							      :readonly="!isAdd||disabledForm.yonghuxingming?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="记录时间" prop="jilushijian">
						<el-date-picker
							class="list_date"
							v-model="form.jilushijian"
							format="YYYY 年 MM 月 DD 日"
							value-format="YYYY-MM-DD"
							type="datetime"
							:readonly="!isAdd||disabledForm.jilushijian?true:false"
							placeholder="请选择记录时间"
							style="width: 100%;" />
					</el-form-item>
				</el-col>
			</el-row>
			<div class="formModel_btn_box">
				<el-button class="formModel_cancel" @click="backClick">取消</el-button>
				<el-button class="formModel_confirm"
                           @click="save"
                           type="success"
				>
					保存
				</el-button>
			</div>
		</el-form>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
    const moment = window.moment
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'yundongshuju'
	const formName = '运动数据'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//form表单
	const form = ref({
		yundongmingcheng: '',
		shizhang: '',
		xinlv: '',
		xiaohaokaluli: '',
		xunlianshipin: '',
		yonghuzhanghao: '',
		yonghuxingming: '',
		jilushijian: '',
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	const disabledForm = ref({
		yundongmingcheng : false,
		shizhang : false,
		xinlv : false,
		xiaohaokaluli : false,
		xunlianshipin : false,
		yonghuzhanghao : false,
		yonghuxingming : false,
		jilushijian : false,
	})
	const isAdd = ref(false)
	//表单验证
	const rules = ref({
		yundongmingcheng: [
		],
		shizhang: [
		],
		xinlv: [
		],
		xiaohaokaluli: [
		],
		xunlianshipin: [
		],
		yonghuzhanghao: [
		],
		yonghuxingming: [
		],
		jilushijian: [
		],
	})
	//训练视频上传回调
	const xunlianshipinUploadSuccess=(e)=>{
		form.value.xunlianshipin = e
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
        form.value.jilushijian = context?.$toolUtil.getCurDate()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			// getInfo()
			for(let x in row){
				if(x=='yundongmingcheng'){
					form.value.yundongmingcheng = row[x];
					disabledForm.value.yundongmingcheng = true;
					continue;
				}
				if(x=='shizhang'){
					form.value.shizhang = row[x];
					disabledForm.value.shizhang = true;
					continue;
				}
				if(x=='xinlv'){
					form.value.xinlv = row[x];
					disabledForm.value.xinlv = true;
					continue;
				}
				if(x=='xiaohaokaluli'){
					form.value.xiaohaokaluli = row[x];
					disabledForm.value.xiaohaokaluli = true;
					continue;
				}
				if(x=='xunlianshipin'){
					form.value.xunlianshipin = row[x];
					disabledForm.value.xunlianshipin = true;
					continue;
				}
				if(x=='yonghuzhanghao'){
					form.value.yonghuzhanghao = row[x];
					disabledForm.value.yonghuzhanghao = true;
					continue;
				}
				if(x=='yonghuxingming'){
					form.value.yonghuxingming = row[x];
					disabledForm.value.yonghuxingming = true;
					continue;
				}
				if(x=='jilushijian'){
					form.value.jilushijian = row[x];
					disabledForm.value.jilushijian = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('yonghuzhanghao') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.yonghuzhanghao = json.yonghuzhanghao
				disabledForm.value.yonghuzhanghao = true;
			}
			if(json.hasOwnProperty('yonghuxingming') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.yonghuxingming = json.yonghuxingming
				disabledForm.value.yonghuxingming = true;
			}
            if (localStorage.getItem('autoSave')) {
                localStorage.removeItem('autoSave')
                save()
            }
		})
	}
	//初始化
	//取消
	const backClick = () => {
		history.back()
	}
	//提交
	const save=()=>{
		if(form.value.xunlianshipin!=null) {
			form.value.xunlianshipin = form.value.xunlianshipin.replace(new RegExp(context?.$config.url,"g"),"");
		}
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		formRef.value.validate(async (valid)=>{
			if(valid){
                if(type.value == 'cross'){
                    if(crossColumnName.value!=''){
                        if(!crossColumnName.value.startsWith('[')){
                            for(let o in objcross){
                                if(o == crossColumnName.value){
                                    objcross[o] = crossColumnValue.value
                                }
                            }
                            //修改跨表数据
                            await changeCrossData(objcross)
                        }else{
                            crossUserId = context?.$toolUtil.storageGet('userid')
                            crossRefId = objcross['id']
                            crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
                        }
                    }
                }
				if(crossUserId&&crossRefId){    //限制用户操作次数
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(async (res)=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(async (res)=>{
                                context?.$toolUtil.message(`操作成功`,'success')
                                history.back()
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
                        context?.$toolUtil.message(`操作成功`,'success')
                        history.back()
					})
				}
			}
		})
	}
	//修改跨表数据
	const changeCrossData=(row,key)=>{
        if(type.value == 'cross'){
            let data = row
            if(key){	//如果有指定key，则只更新key属性
                data = {
                    id:row.id,
                }
                data[key] = row[key]
            }
            context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: data
            }).then(res=>{})
        }
	}
	onMounted(()=>{
		type.value = route.query.type?route.query.type:'add'
		let row = null
		let table = null
		let statusColumnName = null
		let tips = null
		let statusColumnValue = null
		if(type.value == 'cross'){
			row = context?.$toolUtil.storageGet('crossObj')?JSON.parse(context?.$toolUtil.storageGet('crossObj')):{}
			table = context?.$toolUtil.storageGet('crossTable')
			statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
			tips = context?.$toolUtil.storageGet('crossTips')
			statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
		}
		init(route.query.id?route.query.id:null, type.value,'', row, table, statusColumnName, tips, statusColumnValue)
	})
    onUnmounted(()=>{
        Object.keys(localStorage).map(item=>{
            if(item.startsWith('cross')){
                localStorage.removeItem(item)
            }
        })
    })
</script>
<style lang="scss" scoped>
	// 表单
	.add_form{
		// form item
		:deep(.el-form-item) {
			// 内容盒子
			.el-form-item__content {
				//文件上传样式
				.upload-demo {
					width: 100%;
				}
			}
		}
	}
</style>
<style lang="scss">
.edit_view {
    width: 100%;
    padding: 0 7%;
    margin: 0 auto;
    position: relative;
    font-size:16px;
    color:#666;
    background: #FFFFFF;
}
.edit_view .add_form{
    width: 100%;
    margin: 30px auto;
  background: #FFF7E8;
border-radius: 20px 20px 20px 20px;
    padding: 50px 100px;
border: 0px solid #A8A8A8;
}
.edit_view .add_form .el-form-item{
    margin: 0px 0px 20px;
    display: flex;
}
.edit_view .add_form .el-form-item .el-form-item__label{
    width: 150px;
    background: none;
    text-align: right;
    display: block;
    font-size: 16px;
    color: #585858;
    font-weight: 500;
}
.edit_view .add_form .el-form-item .el-form-item__content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% - 120px);
}
.edit_view .list_inp .el-input__wrapper{
    height: 36px;
}


.edit_view .list_date{
    line-height: 36px;
}






.edit_view .add_form .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger{
    background-color: rgb(255, 255, 255);
    
    border-radius: 0px;
    box-sizing: border-box;
    width: 360px;
    height: 180px;
    text-align: center;
    cursor: pointer;
    overflow: hidden;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger .el-icon--upload{
    font-size: 67px;
    color: var(--theme);
    line-height: 50px;
}

.edit_view .add_form .el-form-item .el-form-item__content .upload-demo .el-upload__tip{
    font-size: 15px;
    color: #666;
    margin: 0px 0 0;
    line-height:1;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger em{
    color: var(--theme);
    font-size: 15px;
}


</style>