<template>
	<div id="index" class="main-containers">
        <div class="index_top">
<div class="top-header">
    <div class="projectName">{{projectName}}</div>


                <el-button class="notice-btn" @click="noticeDialogVisible=true">
                    <span class="iconfont "></span>
                    系统公告
                </el-button>

				<div class="chat" style="cursor: pointer;position: relative;" @click="chatClick">
					<i class="iconfont icon-xiaoxi4"></i>
					<span>智能客服</span>
                    <div class="chat-badge" v-if="unread.length">
                        {{unread.length}}
                    </div>
				</div>


				<el-button v-if="!Token" class="login" @click="loginClick">
                    <el-icon><User/></el-icon>登录
				</el-button>

				<div class="user" v-if="Token">
					<el-dropdown class="avatar-container" trigger="hover">
						<div class="avatar-wrapper">
							<img class="user-avatar" :src="store.getters['user/avatar']" style="width: 50px">
							<div class="nickname">{{store.getters['user/showName']}}</div>
							<el-icon class="el-icon-arrow-down">
								<arrow-down />
							</el-icon>
						</div>
						<template #dropdown>
							<el-dropdown-menu class="user-dropDown" slot="dropdown">
								<el-dropdown-item @click="menuHandler('center')" class="center">
									<span>个人中心</span>
								</el-dropdown-item>
								<el-dropdown-item @click="loginOut" class="loginOut">
									<span>退出登录</span>
								</el-dropdown-item>
							</el-dropdown-menu>
						</template>
					</el-dropdown>
				</div>

</div>
            <div class="menu-wrapper">
                <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
                    <el-menu :unique-opened="true" :default-active="menuIndex"
                         class="menu_view" mode="horizontal" @select="menuChange"
                         :key="menuIndex"  :ellipsis="false">
                        <el-menu-item class="first-item" index="/index/home" @click="menuHandler('/')">
                            <i class="iconfont icon-zhuye2"></i>
                            <template #title>
                                <span>首页</span>
                            </template>
                        </el-menu-item>
                        <template v-for="(menu,index) in menuList" :key="menu.menu">
                            <el-sub-menu v-if="menu.child.length>1" :index="menu.name" class="first-item" :teleported="true">
                                <template #title>
                                    <i class="iconfont" :class="menu.icon"></i>
                                    <span>{{ menu.name }}</span>
                                </template>
                                <el-menu-item class="second-item" v-for=" (child,index1) in menu.child" :key="index1"
                                              :index="child.url" @click="menuHandler(child.url)">{{ child.name }}
                                </el-menu-item>
                            </el-sub-menu>
                            <el-menu-item v-else-if="menu.child[0].url=='chat'" index="chat" class="first-item" @click="chatClick">
                                <i class="iconfont" :class="menu.icon"></i>
                                <template #title>
                                    <span>{{menu.name}}</span>
                                </template>
                            </el-menu-item>
                            <el-menu-item v-else :index="menu.child[0].url" class="first-item" @click="menuHandler(menu.child[0].url)">
                                <i class="iconfont" :class="menu.icon"></i>
                                <template #title>
                                    <span>{{menu.child[0].name}}</span>
                                </template>
                            </el-menu-item>
                        </template>
                        <el-menu-item v-if="Token" :index="`/index/${context.$toolUtil.storageGet('frontSessionTable')}Center`" class="first-item" @click="menuHandler('center')">
                            <i class="iconfont icon-user1"></i>
                            <template #title>
                                <span>个人中心</span>
                            </template>
                        </el-menu-item>
                    </el-menu>
                </el-scrollbar>
            </div>

			<div class="rotation_view" >
				<mySwiper :type="3" :data="rotationList" :autoHeight="false" :autoplay="true"
					:loop="false" :navigation="true" :pagination="true"
					paginationType="1" :scrollbar="false" slidesPerView="1"
					spaceBetween="20" :centeredSlides="false"
					:freeMode="false" effectType="0"
					direction="horizontal">
					<template #default="scope">
						<img :src="scope.row.value?baseUrl + scope.row.value:''" @click="carouselClick(scope.row.url)">
					</template>
				</mySwiper>
			</div>

        </div>
		<el-scrollbar class="contain_view body-containers">
			<router-view :key="routerKey"/>
			<el-backtop :right="100" :bottom="100" />
			<div class="bottom_view ql-snow ql-editor">
                
			</div>
		</el-scrollbar>
		<el-dialog v-model="chatVisible" title="智能客服" width="70%" destroy-on-close @close="chatClose" class="chat-dialog">
			<div class="chat_view">
				<div class="chat-content">
					<div class="chat-item" :key="item.id" v-for="(item,index) in chatList">
						<div class="chat_time" v-if="item.showTime">{{item.addtime}}</div>
						<div v-if="item.ask" class="right-content">
                            <div class="user">
                                <span>{{store.getters['user/username']}}</span>
                                <img :src="store.getters['user/avatar']" style="width: 50px;height: 50px;">
                            </div>
                            <div class="content">
                                <el-alert v-if="item.type==1" class="text-content" :title="item.ask" :closable="false"
                                          type="success"></el-alert>
                                <el-image v-else-if="item.type==2" :src="baseUrl + item.ask"
                                          class="chat_img" fit="scale-down"
                                          :preview-src-list="[baseUrl + item.ask]"></el-image>
                                <video v-else-if="item.type==3" controls style="width: 200px;height: 160px">
                                    <source  :src="baseUrl + item.ask">
                                </video>
                                <audio v-else-if="item.type==5" controls style="height: 40px;">
                                    <source :src="baseUrl + item.content">
                                </audio>
                                <div v-else @click="downloadFile(item.content)" class="chat-file"
                                     style="display: flex;align-items: center;gap: 10px;border: 1px solid #999;padding: 10px;cursor: pointer;">
                                    <el-icon style="font-size: 30px;"><Document /></el-icon><span>点击下载</span>
                                </div>
                            </div>
						</div>
						<div v-else class="left-content">
                            <div class="user">
                                <span>在线客服</span>
                                <img src="@/assets/service_avatar.webp" style="width: 50px;height: 50px;">
                            </div>
                            <div class="content">
                                <el-alert v-if="item.type==1" class="text-content" :title="item.reply" :closable="false"
                                          type="warning"></el-alert>
                                <el-image v-else-if="item.type==2" :src="baseUrl + item.reply"
                                          class="chat_img" fit="scale-down"
                                          :preview-src-list="[baseUrl + item.reply]"></el-image>
                                <video v-else-if="item.type==3" controls style="width: 200px;height: 160px">
                                    <source  :src="baseUrl + item.reply">
                                </video>
                                <audio v-else-if="item.type==5" controls style="height: 40px;">
                                    <source :src="baseUrl + item.content">
                                </audio>
                                <div v-else @click="downloadFile(item.content)" class="chat-file"
                                     style="display: flex;align-items: center;gap: 10px;border: 1px solid #999;padding: 10px;cursor: pointer;">
                                    <el-icon style="font-size: 30px;"><Document /></el-icon><span>点击下载</span>
                                </div>
                            </div>
						</div>
						<div class="clear-float"></div>
					</div>
                    <div v-if="askType==1&&aiLoading" style="text-align: center;color: #999">AI思考中...</div>
				</div>
                <div class="input_box">
                    <div class="actionBar">
                        <el-popover
                            title="表情"
                            width="auto"
                            trigger="click"
                            placement="top"
                        >
                            <template #reference>
                                <i class="iconfont icon-xiaolian expression-icon-btn" style="font-size: 24px;"></i>
                            </template>
                            <template #default>
                                <Picker
                                    :data="emojiIndex"
                                    :emojiSize="24"
                                    :showPreview="showEmoji"
                                    :infiniteScroll="false"
                                    set="apple"
                                    @select="handleEmoji"
                                    :showSearch="false"
                                    :i18n="emojiI18n"
                                />
                            </template>
                        </el-popover>
                        <el-upload class="upload" :action="action" :on-success="uploadSuccess"
                                   :show-file-list="false" accept="image/*,.mp4,.mp3">
                            <el-icon style="font-size: 28px"><FolderAdd /></el-icon>
                        </el-upload>
                    </div>
                    <div class="input-row">
                        <el-input class="textarea" type="textarea" v-model="chatForm.ask"></el-input>
                    </div>
                    <div class="send-box">
					    <el-button class="change_btn" @click="askType=askType==1?2:1">{{askType==1?'智能回复':'人工回复'}}</el-button>
                        <el-button class="send" type="primary" @click="addChat">发送</el-button>
                    </div>
                </div>
			</div>
		</el-dialog>
		<el-dialog v-model="noticeDialogVisible" title="系统公告">
			<div>
				<div v-html="store.state.system.notice.content"></div>
			</div>
		</el-dialog>
	</div>
</template>
<script setup>
	import menu from '@/utils/menu'
	import axios from 'axios'
    const moment = window.moment
	import {
		ref,
		onBeforeUnmount,
        onMounted,
		getCurrentInstance,
		nextTick,
		computed,
		watch,
        provide
	} from 'vue';
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const router = useRouter()
	const route = useRoute();
	const context = getCurrentInstance()?.appContext.config.globalProperties;
    const baseUrl = ref(context.$config.url)
    const projectName = ref(`基于Springboot的体育运动训练与成绩管理系统`)
	const Token = ref('')
	if(localStorage.getItem('frontToken') && !store.getters['user/session'].id){
		store.dispatch('user/getSession')
	}
	//获取通知公告
	const noticeDialogVisible = ref(false)
	store.dispatch('system/getSystemNotice')
	onBeforeUnmount(() => {
        clearInterval(chatTimer.value)
	})
	// 获取默认菜单index
	const setMenuIndex = (path)=>{
        if(path){
            menuIndex.value = path.replace('Detail','List')
        }else{
            menuIndex.value = ''
        }
	}
	// 默认菜单index
	const menuIndex = ref('')
    const routerKey = ref(Math.random())
	watch(() => router.currentRoute.value,(value, oldValue) => {
		Token.value = context?.$toolUtil.storageGet('frontToken')
        if(value.path==oldValue?.path){
            //改变路由query时重新加载router-view
            routerKey.value = Math.random()
        }
        if(value.query.centerType){
            setMenuIndex()
        }else{
            setMenuIndex(value.path)
        }
	},{
		immediate:true
	})
	const init = () => {
		// 获取菜单
		getMenu()
		// 赋值token
		Token.value = context?.$toolUtil.storageGet('frontToken')
		// 轮播图
		getRotationList()
		if(Token.value){
			getSession()
		}
	}
	// 切换菜单保存index
	const menuChange = (e)=>{
		if(e=='chat')return
	}
	// 轮播图
	const rotationList = ref([])
	const getRotationList = () => {
		context?.$http({
			url: 'config/list',
			method: 'get',
			params: {
				page: 1,
				limit: 3
			}
		}).then(res => {
			rotationList.value = res.data.data.list
		})
	}

    // 轮播图跳转
    const carouselClick = (url)=>{
        if (url) {
            if (url.startsWith('http')) {
                window.open(url)
            } else {
                context.$router.push(url)
            }
        }
    }
	const menuList = ref([])
	const role = ref('')
	const getMenu = () => {
		let params = {
			page: 1,
			limit: 1,
			sort: 'id',
		}
		context?.$http({
			url: "menu/list",
			method: "get",
			params: params
		}).then(res => {
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
		})
		menuList.value = context?.$config.menuList
	}
	const loginClick = () => {
		context?.$toolUtil.storageSet('toPath',window.history.state.current)
		router.push('/login')
	}
	const loginOut = () => {
        store.dispatch('user/loginOut')
		context?.$toolUtil.message('退出成功', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
		Token.value = ''
        clearInterval(chatTimer.value)
	}
	//智能客服
    import emojiData from 'emoji-mart-vue-fast/data/all.json'
	import 'emoji-mart-vue-fast/css/emoji-mart.css'
	import { Picker, EmojiIndex } from 'emoji-mart-vue-fast/src'
	const emojiIndex = new EmojiIndex(emojiData)
	const emojiI18n = {
		categories: {
			search: '搜索结果',
			recent: '经常使用',
			smileys: '表情与情感',
			people: '人物与身体',
			nature: '动物与自然',
			foods: '食物与饮料',
			activity: '活动',
			places: '旅行与地理',
			objects: '物品',
			symbols: '符号标志',
			flags: '旗帜',
			custom: 'Custom',
			joy: '哭笑'
		}
	}
	const showEmoji = ref(false)
	// 选中emoji
	const handleEmoji = (e) => {
        chatForm.value.ask += e.native;
		showEmoji.value = false
	}
	const chatVisible = ref(false)
	const chatForm = ref({
        ask:'',
    })
	const chatList = ref([])
    const scrollFlag = ref(true)
    const chatTimer = ref(null)
	//智能客服弹窗
	const chatClick = () => {
		getChatList()
		chatVisible.value = true
        scrollFlag.value = true
	}
	//智能客服聊天列表
	const getChatList = () => {
		context?.$http({
			url: 'chat/list',
			method: 'get',
			params: {
				limit: 1000,
				sort: 'addtime',
				order: 'asc',
				userid: context?.$toolUtil.storageGet('userid')
			}
		}).then(res => {
			for (let x in res.data.data.list) {
				if(x==0){
					res.data.data.list[x].showTime = 1
				}else{
					let jian = new Date(res.data.data.list[x].addtime).getTime() - new Date(res.data.data.list[x-1].addtime).getTime()
					if(jian>18000){
						res.data.data.list[x].showTime = 1
					}else{
						res.data.data.list[x].showTime = 0
					}
				}
			}
			chatList.value = res.data.data.list
			nextTick(() => {
				let div = document.getElementsByClassName('chat-content')[0]
				setTimeout(() => {
                    if (div){
                        if(div.scrollTop+div.clientHeight==div.scrollHeight || scrollFlag.value){
                            div.scrollTop = div.scrollHeight
                            scrollFlag.value = false
                        }
                    }
				}, 100)
			})
		})
	}
    const unread = ref([])
    const getUnread = ()=>{
        context.$http.get('chat/list',{
            params:{
                limit: 100,
                sort: 'addtime',
                order: 'asc',
                userid: context?.$toolUtil.storageGet('userid'),
                isRead:0,
            }
        }).then(res=>{
            if(res.data.code==0){
                unread.value = res.data.data.list.filter(item=>{
                    return item.reply
                })
            }
        }).catch(e=>{})
    }
    if(Token.value){
        chatTimer.value = setInterval(() => {
            if(chatVisible.value){
                getChatList()
                unread.value = []
            }else{
                getUnread()
            }
        }, 3000)
    }
	const chatClose = ()=>{
		setMenuIndex()
	}
	//图片上传
	const action = ref(`${context?.$config.name}/file/upload`)
	const uploadSuccess = (e) => {
        let type;
        if(/(\.png|\.jpg|\.webp)$/i.test(e.file)){    //图片
            type = 2
        }else if(/(\.mp4)$/i.test(e.file)){   //mp4视频
            type = 3
        }else if(/(\.mp3)$/i.test(e.file)){   //mp3音频
            type = 5
        }else{  //文件
            type = 4
        }
		let params = {
			ask: 'file/' + e.file,
            type:type,
			userid: context.$toolUtil.storageGet('userid'),
            userImage:context.$toolUtil.storageGet('headportrait'),
            userName:context.$toolUtil.storageGet('frontName')
		}
		context?.$http({
			url: 'chat/add',
			method: 'post',
			data: params
		}).then(res => {
			context?.$toolUtil.message('发送成功', 'success', () => {
				getChatList()
				chatForm.value.ask = ''
			})
		})
	}
    const downloadFile = (url)=>{
        if(!url){
            return false
        }
        window.open(baseUrl.value + 'file/download?fileName=' + url.replace("file/",''))
    }
	//发送信息
	const addChat = () => {
		let params = JSON.parse(JSON.stringify(chatForm.value))
        params.type = 1
		params.userid = context.$toolUtil.storageGet('userid')
        params.userTableName = context.$toolUtil.storageGet('frontSessionTable')
        params.userImage = context.$toolUtil.storageGet('headportrait')
        params.userName = context.$toolUtil.storageGet('frontName')
		context.$http({
			url: 'chat/add',
			method: 'post',
			data: params
		}).then(res => {
			context?.$toolUtil.message('发送成功', 'success')
			if (askType.value == 1) {
				setTimeout(()=>{
					getChathelper(params.ask)
				},1000)
			}
			chatForm.value.ask = ''
            scrollFlag.value = true
			getChatList()
		})
	}
	//智能回复
	const askType = ref(1)
    const aiLoading = ref(false)
	//获取自动回复
	const getChathelper = (ask) => {
        context.$http({
            url: 'chathelper/page',
            method: 'get',
            params: {
                ask: "%"+ask.trim()+"%",
                limit: 1
            }
        }).then(res => {
            if (res.data.data.list.length) {
                saveChathelper(res.data.data.list[0].reply)
                scrollFlag.value = true
                aiLoading.value = false
            } else {
                aiLoading.value = true
                let url = `baidu/askai?ask=${ask}`
                context.$http.get(url).then(res => {
                    if (res.data.code == 0) {
                        saveChathelper(res.data.data)
                    } else {
                        saveChathelper('主人，我还不够聪明，无法理解您的意思！')
                    }
                    scrollFlag.value = true
                    aiLoading.value = false
                })
            }
        })
	}
	//保存自动回复
	const saveChathelper = (reply) => {
		context?.$http({
			url: 'chat/save',
			method: 'post',
			data: {
				reply: reply,
				userid: context?.$toolUtil.storageGet('userid'),
                type:1,
                userName:"智能助手"
			}
		}).then(res => {
			chatForm.value.ask = ''
			getChatList()
		})
	}
	//菜单跳转
	const menuHandler = (name) => {
		if(name=='chat')return chatClick()
		if (name == 'center') {
			name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
			menuChange('center')
		}
		router.push(name)
	}
	// 获取用户信息
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu'){
				context?.$toolUtil.storageSet("frontName", res.data.data.yonghuzhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang||'')
			}
		})
	}
	init()
</script>
<style lang="scss">
    .el-aside {
        transition: width 0.15s;
        -webkit-transition: width 0.15s;
        -moz-transition: width 0.15s;
        -webkit-transition: width 0.15s;
        -o-transition: width 0.15s;
    }
    .chat-badge{
        position: absolute;
        background: red;
        color: rgb(255, 255, 255);
        font-size: 12px;
        border-radius: 14px;
        padding:0 4px;
        height: 14px;
        display: flex;
        justify-content: center;
        align-items: center;
        top: -4px;
        right: -10px;
    }
.index_top .top-header {
    width: 100%;
    padding: 30px 7%;
    background: #FFF7E8;
    margin:0 auto;
    display: flex;
    align-items: center;
    color: #000000;
    border-radius: 0px 0px 0 0;
    font-size: 16px;
}
.index_top .projectName {
    margin-right: auto;
    font-size: 24px;
    color: #000000;
    font-weight: 500;
    white-space: nowrap;
}
.index_top .weather {
    color: #000000;
    white-space: nowrap;
    margin: 5px 0 0;
}
.index_top .notice-btn{
    background: none;
    padding: 0 0 0 30px;
    border: none;
    color: #000000;
    margin: 0 20px;
}
.index_top .login{
    background: none;
    border: 0px solid #000000;
    color: #000000;
    font-size: 16px;
    margin: 0 20px 0 0;
}

.index_top .avatar-wrapper {
    display: flex;
    align-items: center;
    gap: 6px;
}
.index_top img.user-avatar {
    width: 40px!important;
    height: 40px;
}
.index_top .avatar-wrapper .nickname{
    color: #000000;
    white-space: nowrap;
}
.index_top .avatar-wrapper .el-icon{
    color: #000000;   
}
.index_top .chat{
    white-space: nowrap;
    margin: 0 20px 0 0;
}
.index_top .cart{
    white-space: nowrap;
    margin: 0 20px 0 0;
}

li.center.el-dropdown-menu__item {
    display: none;
}
.bottom_view {
    width: 100%;
    padding: 20px 7%;
    margin: 0 auto;
    min-height: 100px;
background: #3B432C;
    color: #fff;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    align-content: center;
    justify-content: center;
    border-radius: 0 0 0px 0px;
}


.el-backtop {
    right:40px !important;
}
 .main-containers{
    background:#f5f7fa;
}
 #index {
    background: #f5f7fa;
}
.menu-wrapper{
    
    width: 100%;
    padding:10px 7%;
    margin: 0px auto 0px;
  background:#FFF7E8;
    border-top: 0px solid #f0f0f0;
    font-size: 16px;
    border: 1px solid #E5DCCD;
    border-radius: 0px;
    overflow-y: hidden;
}
.menu-wrapper .el-scrollbar__view{
}
.menu-wrapper ul.el-menu.el-menu--horizontal.menu_view {
    font-size: 16px;
    background: none;
    height: 50px;
    display: flex;
    justify-content: center;
}
.menu-wrapper .el-menu--horizontal.el-menu {
    border-bottom:0px;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item {
    align-items: center;
    border-bottom: 0px solid transparent;
    color: #000000;
    font-size: 16px;
    margin-right:20px;
    border-radius: 30px;
    padding: 0;
}
.menu-wrapper .el-menu--horizontal>.el-menu-item i{
    margin: 0 3px 0 0;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active{
    background: #000000 !important; 
    color: var(--theme) !important; 
    border-bottom: 0px solid var(--theme);
}


.menu-wrapper .menu_view .first-item{
    position: relative;
    margin-right:20px;
    padding: 0;
}
.menu-wrapper .menu_view .first-item .el-sub-menu__title{
    color: #000000;
    font-size: 16px;
    border-radius: 30px;
    padding: 0 20px 0 0;
}

.menu-wrapper .menu_view .first-item .el-sub-menu__title i{
    margin: 0 3px 0 0;
}

.menu-wrapper .el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title{
    background: #000000; 
    border-bottom: none;
    border-bottom: 0px solid var(--theme);
    border-radius: 30px;
}
.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active,.menu-wrapper .el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title {
    background: #000000;  
    color: var(--theme)!important;
    font-size: inherit;
    border-radius: 30px;
    border-bottom: 0px solid var(--theme);
}




.menu-wrapper .el-sub-menu .el-sub-menu__icon-arrow {
    font-size: 12px;
    margin-top: -6px !important;
    position: absolute;
    top: 50%;
    right: 0;
}
.menu-wrapper .menu_wrapper {
    --el-menu-bg-color: none;
    --el-menu-active-color: var(--theme-dark);
}
.menu-wrapper .el-menu--horizontal>.el-menu-item .el-sub-menu__icon-more{
    color: #000000;
}
.menu-wrapper .el-menu--horizontal>.el-sub-menu .el-sub-menu__title{
    border: 0;
}



.menu-wrapper .el-menu--popup {
    border: none;
    border-radius: var(--el-border-radius-small);
    box-shadow: var(--el-box-shadow-light);
    min-width: 200px;
    padding: 10px;
    z-index: 100;
}
.menu-wrapper .el-menu--popup .second-item{
    border-radius: 30px;
}


.menu-wrapper i.el-icon.el-sub-menu__icon-more {
    color: #0949f7 !important;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active{
background: #000000!important;
border-radius: 5px 5px 5px 5px;
height: 30px;
line-height:30px;
margin-top:auto;
margin-bottom:auto;
padding:10px;
color:#000000!important;
}

.menu-wrapper li.el-sub-menu.is-active.first-item {
background: #000000!important;
border-radius: 5px 5px 5px 5px;
height: 30px;
line-height:30px;
margin-top:auto;
margin-bottom:auto;
padding:10px;
color:#000000!important;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active, .menu-wrapper .el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title {
    background:none;
    color:#FFFFFF!important;
}


.menu-wrapper i.el-icon.el-sub-menu__icon-arrow {
    display:none;
}


.menu-wrapper .el-menu--horizontal .el-menu-item:not(.is-disabled):focus,.menu-wrapper  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
    background:none;
}
.menu-wrapper .el-menu--horizontal .el-menu-item:not(.is-disabled):focus,.menu-wrapper  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
    color:#000000
}
.menu-wrapper .el-menu--horizontal>.el-sub-menu:hover .el-sub-menu__title{
    background:none;
}

.menu-wrapper ul.el-menu.el-menu--popup.el-menu--popup-bottom-start {
    
background:#FFF7E8;
border-radius: 5px 5px 5px 5px;
}


.menu-wrapper ul.el-menu.el-menu--popup.el-menu--popup-bottom-start li.el-menu-item.second-item {
    background:none;
    color:#000000;
    border:none;
    box-shadow:none;
}

/* .el-menu{
    background:none;
} */

ul.el-menu.el-menu--popup.el-menu--popup-bottom-start {
   background:#FFF7E8;
border-radius: 5px 5px 5px 5px;
border:none;
   box-shadow:none;
   padding:10px;
}
.el-menu--horizontal .el-menu .el-menu-item, .el-menu--horizontal .el-menu .el-sub-menu__title{
   background:none;
   color:#000000
}
.el-menu--horizontal .el-menu .el-menu-item.is-active, .el-menu--horizontal .el-menu .el-menu-item.is-active:hover, .el-menu--horizontal .el-menu .el-sub-menu.is-active>.el-sub-menu__title, .el-menu--horizontal .el-menu .el-sub-menu.is-active>.el-sub-menu__title:hover{
   background: #000000;
   color:#FFFFFF;
border-radius: 5px 5px 5px 5px;
}
.el-menu--horizontal .el-menu-item:not(.is-disabled):focus, .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
 background: #000000;
   color:#FFFFFF;
border-radius: 5px 5px 5px 5px;
}

/* .el-popper.is-light, .el-popper.is-light>.el-popper__arrow:before{
    background: none;
    border:none;
    box-shadow:none;
} */
.rotation_view{
    width: 100%;
    padding: 0;
    margin: 0px auto;
}
.rotation_view .swiper{
    height:450px;
}
.rotation_view .swiper-slide img {
    width: 100%;
    height:100%;
    object-fit: cover;
    border-radius: 0px; 
}
.swiper-pagination-bullet-active {
    background: var(--theme);
}


.rotation_view .swiper-button-prev{
    margin-left:40px !important; 
}
.rotation_view .swiper-button-next{
    margin-right:40px !important; 
}
.rotation_view .swiper-button-next:after,.rotation_view .swiper-button-prev:after {
    background:rgba(255,255,255,.5);
    font-size: 20px;
    color: #fff;
    font-weight: 600;
    padding: 0 20px;
    width: 50px !important;
    height: 50px !important;
    line-height: 50px !important;
    text-align: center;
    border-radius: 100%;
}

.breadcrumb-wrapper {
    width: 100%;
    padding: 0px;
    margin: 0px auto!important;
    display: flex;
    justify-content: space-between;
    align-items: center;
background: #F6F9FE;
border-radius: 0px 0px 0px 0px;
    height:50px;
}

.breadcrumb-wrapper .el-breadcrumb{
   width: 100%;
   font-size: 16px; 
}

.breadcrumb-wrapper .el-breadcrumb__separator{
color: #000000;
}

.breadcrumb-wrapper .el-breadcrumb span{
color: #9E9E9E!important; 
}

.breadcrumb-wrapper .el-breadcrumb span {
    display:flex;
    align-items:center;

border-radius: 0px 0px 0px 0px;
height:100%!important;
padding:0px 5px;
    background: #FFF7E8;
border-radius: 0px 0px 0px 0px;
}

.breadcrumb-wrapper .bread_view {
    height:100%;
  
}

.breadcrumb-wrapper .el-breadcrumb {
    height:100%;
}

.breadcrumb-wrapper span.el-breadcrumb__item.first_breadcrumb {
background: #3B432C;
border-radius: 0px 0px 0px 0px;
    color:#FFFFFF;
}
.breadcrumb-wrapper span.el-breadcrumb__item.first_breadcrumb span{
background: #3B432C;
border-radius: 0px 0px 0px 0px;
    color:#FFFFFF;
}

.breadcrumb-wrapper span.el-breadcrumb__item.first_breadcrumb span.el-breadcrumb__inner a {
    color:#FFFFFF;
}


.chat_view {
    display: flex;
    flex-direction: column;
    margin: 30px 0px;
    padding: 30px;
    border: 0px solid rgb(221, 221, 221);
    border-radius: 4px;
    font-size: 16px;
    background:#FFF7E8;
border-radius: 0px;
    gap:10px;
}

.chat_view .chat-content{
    margin: 0px 0px 30px;
    padding: 0px 0px 30px;
    max-height: 400px;
    height: 400px;
    overflow-y: scroll;
    border-bottom: 2px solid #9CA3AF !important;
    border-radius: 0px;
    background: none!important;
      scrollbar-width: none; 
  -ms-overflow-style: none; 
}

.chat_view .chat-content .chat-item .chat_time{
    width: 100%;
    text-align: center;
    font-size: 16px;
    color: rgb(153, 153, 153);
    padding: 6px 0px;
}

.chat_view .chat-content .chat-item .left-content{
    margin: 0px 0px 10px;
    padding: 10px;
    display: inline-flex;
    max-width: 80%;
}

.chat_view .chat-content .chat-item .left-content .text-content{
    background-color: rgb(240, 249, 235);
    color: rgb(103, 194, 58);
}

.chat_view .chat-content .chat-item .left-content .chat_img{
    width: 120px;
    margin: 0 10px;
}

.chat_view .chat-content .chat-item .right-content{
    margin: 0px 0px 10px;
    padding: 10px;
    display: inline-flex;
    margin-left: auto;
    max-width: 80%;
}

.chat_view .chat-content .chat-item .right-content .text-content{
    background-color: rgb(253, 246, 236);
    color: #000;
}

.chat_view .chat-content .chat-item .right-content .chat_img{
    width: 120px;
    margin: 0 10px;
}


.chat_view .input_box{
    display: flex;
    align-items: center;
}

.chat_view .input_box .el-textarea{
    position: relative;
    display: inline-block;
    width: 100%;
    vertical-align: bottom;
    font-size: 14px;
}



.chat_view .save_box{
width: 100%;
    display: flex;
    margin: 10px 0px 0px;
}

.chat_view .save_box .el-button--primary{
    margin: 0px 10px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 36px;
    font-size: 14px;
    color: rgb(255, 255, 255);
    border-radius: 4px;
    border: 0px solid rgb(252, 203, 130);
    background: var(--theme);
    cursor: pointer;
    min-width: 110px;
}

.chat_view .input_box {
    display: flex;
    flex-wrap: wrap;
}

.chat_view .actionBar {
    width: 100%;
    display: flex;
    align-items: center;
    gap: 10px;
}
.chat_view .upload {
    display: flex;
}

.chat_view .input-row {
    width: 100%;
}

.chat_view .send-box {
    margin-left: auto;
}

.chat_view .input_box {
    background: #fff;
    padding: 14px 20px;
    border: 1px solid #ddd;
}
.chat_view .el-textarea__inner {
    border: none!important;
    box-shadow: none!important;
    resize: none!important;
}
.chat_view .el-textarea__inner:focus {
    outline: none!important;
    box-shadow: none!important;
}
.chat_view .chat-content {
    margin-bottom:0px!important;

}

.chat_view .input_box {
    height:200px;
    max-height:200px;
}

.chat_view button.el-button.el-button--primary.send {
    width: 120px;
    height: 40px;
    background: #0949F7;
    border-radius: 10px 10px 10px 10px;
    font-size:18px;
}
.chat_view .el-alert.el-alert--success.is-light.text-content {
    width: 460px;
    height:auto;
  min-height:70px;  
    background: #FFFFFF!important;
    border-radius: 0px 30px 30px 30px;
    border: 1px solid #B3B3B3;
}

.chat_view .el-alert.el-alert--warning.is-light.text-content {
    width: 460px;
   height:auto;
  min-height:70px;  
    background: #0949F7!important;
    border-radius: 30px 0px 30px 30px;
    color:#fff!important;
}

.chat_view .chat-item {
    display: flex;
    padding-top: 24px;
    position: relative;
}

.chat_view .right-content .user {
    order: 2;
    margin-left: 10px;
}
.chat_view .left-content .user {
    order: 0;
    margin-right: 10px;
}
.chat_view .user span {
    display: none;
}
.chat_view .user img {
    width: 40px !important;
    height: 40px !important;
}


.chat_view .content .el-alert.el-alert--warning.is-light.text-content {
  position: relative!important;
  background: #F8F8F8!important;
  border-radius: 10px;
  border: 1px solid #3B432C;
  padding-left: 20px!important;
  overflow: visible !important;
  color:#000!important;
}


.chat_view .content .el-alert.el-alert--warning.is-light.text-content::before {
  content: "";
  position: absolute;
  left: -10px;
  top: 50%;
  transform: translateY(-50%);
  width: 0;
  height: 0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-right: 10px solid #3B432C; 
  z-index: 1;
}


.chat_view .content .el-alert.el-alert--warning.is-light.text-content::after {
  content: "";
  position: absolute;
  left: -9px; 
  top: 50%;
  transform: translateY(-50%);
  width: 0;
  height: 0;
  border-top: 9px solid transparent; 
  border-bottom: 9px solid transparent;
  border-right: 9px solid #F8F8F8; 
  z-index: 2;
}
.chat_view .el-alert.el-alert--success.is-light.text-content  {
   position: relative!important;
  background: #FFFFFF!important;
  border-radius: 10px;
  border: 1px solid #3B432C;
  padding-right: 20px!important; 
  overflow: visible !important;
}


.chat_view .el-alert.el-alert--success.is-light.text-content ::before {
  content: "";
  position: absolute;
  right: -10px; 
  top: 50%;
  transform: translateY(-50%);
  width: 0;
  height: 0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 10px solid #3B432C; 
  z-index: 1;
}


.chat_view .el-alert.el-alert--success.is-light.text-content ::after {
 content: "";
  position: absolute;
  right: -9px; 
  top: 50%;
  transform: translateY(-50%);
  width: 0;
  height: 0;
  border-top: 9px solid transparent; 
  border-bottom: 9px solid transparent;
  border-left: 9px solid #FFFFFF; 
  z-index: 2;
}

.chat_view .user img {
  border-radius:50%;
}

.chat_view button.el-button.el-button--primary.send {
background: #FF6F2C;
border-radius: 10px 10px 10px 10px;
}

.chat_view .chat-item {
    flex-wrap:wrap;
}

.el-dialog.chat-dialog {
    background: #FFF7E8;
border-radius: 0px 0px;
padding:30px;
}
</style>