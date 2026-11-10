const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '论坛交流',
					icon: '',
					child:[
						{
							name:'论坛交流',
                            url:'/index/forumList'

						},
					]
				},

				{
					name: '新闻资讯管理',
					icon: '',
					child:[
						{
							name:'运动资讯',
                            url:'/index/newsList'

						},
					]
				},

			]
        }
    },
    getProjectName(){
        return {
            projectName: `基于Springboot的体育运动训练与成绩管理系统`
        } 
    }
}
export default config
