<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="12">
						<el-form-item label="运动名称" prop="yundongmingcheng">
							<el-input class="list_inp" v-model="form.yundongmingcheng" placeholder="运动名称"
                                type="text"
								:readonly="!isAdd||disabledForm.yundongmingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="时长" prop="shizhang">
							<el-input class="list_inp" v-model="form.shizhang" placeholder="时长"
                                type="text"
								:readonly="!isAdd||disabledForm.shizhang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="心率" prop="xinlv">
							<el-input class="list_inp" v-model="form.xinlv" placeholder="心率"
                                type="text"
								:readonly="!isAdd||disabledForm.xinlv?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="消耗卡路里" prop="xiaohaokaluli">
							<el-input class="list_inp" v-model="form.xiaohaokaluli" placeholder="消耗卡路里"
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
							<el-input class="list_inp" v-model="form.yonghuzhanghao" placeholder="用户账号"
                                type="text"
								:readonly="!isAdd||disabledForm.yonghuzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="用户姓名" prop="yonghuxingming">
							<el-input class="list_inp" v-model="form.yonghuxingming" placeholder="用户姓名"
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
								type="date"
								:readonly="!isAdd||disabledForm.jilushijian?true:false"
								placeholder="请选择记录时间" />
						</el-form-item>
					</el-col>

				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
	const moment = window.moment
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
    const isAdmin = localStorage.getItem('isAdmin')||context.$toolUtil.storageGet("sessionTable")=='users'
	//基础信息
	const tableName = 'yundongshuju'
	const formName = '运动数据'
	//基础信息
	//form表单
	const form = ref({})
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
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
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
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//训练视频上传回调
	const xunlianshipinUploadSuccess=(e)=>{
		form.value.xunlianshipin = e
	}
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yundongmingcheng: '',
			shizhang: '',
			xinlv: '',
			xiaohaokaluli: '',
			xunlianshipin: '',
			yonghuzhanghao: '',
			yonghuxingming: '',
			jilushijian: '',

		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
			form.value.jilushijian = context?.$toolUtil.getCurDate()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
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
			formVisible.value = true
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('yonghuzhanghao')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.yonghuzhanghao = json.yonghuzhanghao
				disabledForm.value.yonghuzhanghao = true;
			}
			if(json.hasOwnProperty('yonghuxingming')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.yonghuxingming = json.yonghuxingming
				disabledForm.value.yonghuxingming = true;
			}
		})
	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
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
							crossUserId = user.value.id
							crossRefId = objcross['id']
							crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
						}
					}
				}
				if(crossUserId&&crossRefId){
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
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('请完善信息')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row,key)=>{
        if(type.value == 'cross'){
			let data = row
			if(key){	//如果有指定key，则只更新key属性
				data = {
					id:row.id,
				}
				data[key] = row[key]
			}
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: data
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
		:deep(.el-form-item) {
			.el-form-item__content {
				//文件上传样式
				.upload-demo {
					width: 100%;
				}
			}
		}
	}
</style>
