import {
	createRouter,
	createWebHashHistory
} from 'vue-router'
import login from '@/views/login.vue'
import index from '@/views/index.vue'
import HomeView from '@/views/HomeView.vue'
export const routes = [{
		path: '/login',
		name: 'login',
        meta: { title: '登录' },
		component: login
	},{
		path: '/',
		name: '首页',
        meta: { title: '首页' },
		component: index,
		children: [{
			path: '/',
			name: 'home',
			component: HomeView,
			meta: {
				affix: true,
                title: '首页'
			}
		}, {
			path: '/updatepassword',
			name: 'updatepassword',
            meta: { title: '修改密码' },
			component: () => import('../views/updatepassword.vue')
		}

		,{
			path: '/usersCenter',
			name: 'usersCenter',
            meta: { title: '管理员个人中心' },
			component: ()=>import('@/views/users/center')
		}
		,{
			path: '/forum',
			name: 'forum',
            meta: { title: '论坛交流' },
			component: ()=>import('@/views/forum/list')
		}
		,{
			path: '/news',
			name: 'news',
            meta: { title: '运动资讯' },
			component: ()=>import('@/views/news/list')
		}
		,{
			path: '/xunlianjihua',
			name: 'xunlianjihua',
            meta: { title: '训练计划' },
			component: ()=>import('@/views/xunlianjihua/list')
		}
		,{
			path: '/xunlianchengji',
			name: 'xunlianchengji',
            meta: { title: '训练成绩' },
			component: ()=>import('@/views/xunlianchengji/list')
		}
		,{
			path: '/yonghu',
			name: 'yonghu',
            meta: { title: '用户' },
			component: ()=>import('@/views/yonghu/list')
		}
		,{
			path: '/systemNotice',
			name: 'systemNotice',
            meta: { title: '系统公告' },
			component: ()=>import('@/views/systemNotice/list')
		}
		,{
			path: '/forumType',
			name: 'forumType',
            meta: { title: '论坛类型' },
			component: ()=>import('@/views/forumType/list')
		}
		,{
			path: '/forumReport',
			name: 'forumReport',
            meta: { title: '论坛举报' },
			component: ()=>import('@/views/forumReport/list')
		}
		,{
			path: '/yundongshuju',
			name: 'yundongshuju',
            meta: { title: '运动数据' },
			component: ()=>import('@/views/yundongshuju/list')
		}
		,{
			path: '/syslog',
			name: 'syslog',
            meta: { title: '操作日志' },
			component: ()=>import('@/views/syslog/list')
		}
		,{
			path: '/config',
			name: 'config',
            meta: { title: '轮播图' },
			component: ()=>import('@/views/config/list')
		}
		,{
			path: '/users',
			name: 'users',
            meta: { title: '管理员' },
			component: ()=>import('@/views/users/list')
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
