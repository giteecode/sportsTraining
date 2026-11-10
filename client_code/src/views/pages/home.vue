<template>
    <div class="home_box">





			<!-- 运动资讯 -->
			<div class="newsList_view">
<div class="tableName"><span>运动资讯</span></div>
<div class="list">
       <template v-for="(item,index) in newsList">
            <div v-if="index==0" class="item" @click="newsDetailClick(item)" style="cursor: pointer;">
                 <div class="leftimg" v-if="index==0">
        <img :src="item.imgUrls[0]" >
    </div>
               <div class="newcenter">
                    <div class="imgbox">
                   <img :src="item.imgUrls[0]" >
                </div>
   <div class="newright">
                     <div class="infobox">
                                  <div class="intonewbtn"> <span>新闻</span></div> 
                    <div class="date">{{moment(item.addtime).format('YYYY-MM-DD')}}</div>
                    <div class="neirong">
                     <div class="title">{{item.title}}</div>
                   <div class="intro text-two-row">{{item.introduction}}</div>
                    </div>  
                </div>
                </div>
               </div>
            </div>
        </template>

     <template v-for="(item,index) in newsList">
            <div v-if="index==1" class="item" @click="newsDetailClick(item)" style="cursor: pointer;">
          
               <div class="newcenter">
                    <div class="imgbox">
                   <img :src="item.imgUrls[0]" >
                </div>
   <div class="newright">
                     <div class="infobox">
                                  <div class="intonewbtn"> <span>新闻</span></div> 
                    <div class="date">{{moment(item.addtime).format('YYYY-MM-DD')}}</div>
                    <div class="neirong">
                     <div class="title">{{item.title}}</div>
                   <div class="intro text-two-row">{{item.introduction}}</div>
                    </div>  
                </div>
                </div>
               </div>
                       <div class="leftimg" v-if="index==1">
        <img :src="item.imgUrls[0]" >
    </div>
            </div>
        </template>
      
    
        <template v-for="(item,index) in newsList">
            <div v-if="index>1" class="item" @click="newsDetailClick(item)" style="cursor: pointer;">
               <div class="newcenter">
                    <div class="imgbox">
                   <img :src="item.imgUrls[0]" >
                </div>
   <div class="newright">
                     <div class="infobox">
                                  <div class="intonewbtn"> <span>新闻</span></div> 
                    <div class="date">{{moment(item.addtime).format('YYYY-MM-DD')}}</div>
                 
                    <div class="neirong">
                     <div class="title">{{item.title}}</div>
                   <div class="intro text-two-row">{{item.introduction}}</div>
                    </div>
                  
                   
                </div>
          
                </div>
                
                   
               </div>
              
             
            </div>
        </template>
        <div class="more" @click="moreClick('news')" style="cursor: pointer"><span>查看更多 ></span></div>
</div>

			</div>



    </div>
    <formModel ref="newsFormModelRef"></formModel>
</template>

<script setup>
	import {
		ref,
        computed,
		getCurrentInstance
	} from 'vue';
    const moment = window.moment
	import {
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
    const baseUrl = ref(context.$config.url)
	//运动资讯弹窗
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	//运动资讯
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 9,
                sort:'id',
                order:'desc',
			}
		}).then(res=>{
			newsList.value = res.data.data.list
			newsList.value.forEach(item=>{
                let urls = item.picture.split(',')
                item.imgUrls = urls.map(url=>{
                    if(isHttp(url)){
                        return url
                    }else{
                        return baseUrl.value+url
                    }
                })
			})
		})
	}
	const newsDetailClick = (item) => {
		if (item && item.id){
			newsFormModelRef.value.init(item.id)
		}
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//运动资讯
		getNewsList()
	}
	init()
</script>

<style lang="scss">
.home_box {
    width: 100%;
    margin: 0 auto;
    padding: 20px 0px 50px;
    display: flex;
    flex-wrap: wrap;
    background:#FFFFFF;  
}
.newsList_view {
    width: 100%;
    padding:0 7%;
    margin-top: 30px;
    margin-left:auto;
    margin-right:auto;
    position: relative;
    order: 7;
    background:#3B432C;
}


.newsList_view .list {
    width: 100%;
    margin: 30px 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
  gap: 30px;
}


.newsList_view .item .imgbox {
    margin: 0 auto;
    width:30%;
    display:none;
}

.newsList_view .item .infobox {
    flex:1;
    display:flex;
    flex-wrap: wrap;
    width: 100%;
}
.newsList_view .item .infobox .title {
    width: 100%;
    font-size: 16px;
    color: #000;
    font-weight: 600;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}
.newsList_view .item .infobox .intro{
    margin-top:5px;
    font-size: 14px;
    color: #999;
    line-height: 24px;
    padding-right: 10px;
}
.newsList_view .item .infobox .date {
    margin-top:5px;
    margin-bottom: 30px;
    text-align: left;
    color: #000;
    width: 100%;
}
.newsList_view .item .infobox .date span{
    display: block;
    float: right;
    color: #b54043;
    padding-right: 10px;
}
.newsList_view .el-carousel {
    height: 100%;
}
.newsList_view .el-carousel__container {
    height: 100%;
}

/* nf13 */


.newsList_view .item .imgbox img{
    width: 100%;
    height: 160px;
    object-fit: cover;
    border-radius: 20px;
}






/* nf14 */

.newsList_view .intonewbtn{
    width:100%;
    text-align:left;
}

.newsList_view .intonewbtn span{
    display:block;
    width:50px;
height: 30px;
background: var(--theme);
border-radius: 5px 5px 5px 5px;
    color:#FFFFFF;
    text-align:center;
    line-height:30px;
}

/* nf20 */
.newsList_view .newright {
    display:flex;
    flex-direction:column;
  
}

.newsList_view .newcenter {
    display:flex;
    flex:1 1 60%;
    background:#FFFFFF;
    padding:20px;
    border-radius: 20px 20px 20px 20px;
}
.newsList_view .item {
    flex: 1 1 calc(50% - 15px); /* 基础大小50%，可以伸缩 */
    min-width: 300px; /* 设置最小宽度，防止过小 */
    display: flex;
    padding: 20px;
    box-sizing: border-box;
      gap:20px;
    display: flex;
    flex-wrap:nowrap;
    justify-content:flex-start;
   

border-radius: 20px 20px 20px 20px;


}
 

.newsList_view .more {
    width:100%;
    text-align:center;
    margin:30px;
}



/* nf21 */
.newsList_view .tableName{
    font-size: 26px;
    color: #FFFFFF;
    text-align: left;
    font-weight: 500;
    height:90px;
    line-height: 60px;
    margin-bottom:20px;
}
/* 商品推荐 */
.newsList_view .tableName span:before{
content:"NewsCenter";
position:absolute;
top:18px;
left:0px;
}
.newsList_view .tableName span{
    position:relative;
}

.newsList_view .more{
    position:absolute;
    top:0px;
    left:30%;
    
}

.newsList_view .more span{
   font-weight: 400;
font-size: 16px;
color:#FFFFFF;
    border-radius:10px;
    padding:5px 10px;
    font-weight: 400;
font-size: 20px;
    padding:10px;
    width: 130px;
height: 40px;
background: #FF6F2C;
border-radius: 20px 20px 20px 20px;
}
.newsList_view img {}

.newsList_view .leftimg {
    width:300px;
    height:300px;
}

.newsList_view .leftimg  img {
    width:100%;
    height:100%;
    object-fit:cover;
    border-radius:20px;
}


</style>