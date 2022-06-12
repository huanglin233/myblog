import Vue from 'vue'
import Router from 'vue-router'
import login from '@/views/admin/login'
import blog404 from '@/views/error/blog404'
import blog500 from '@/views/error/blog500'
import error from '@/views/error/error'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [{
            path: '/',
            name: 'index',
            component: (resolve) => require(['@/views/index'], resolve)
        },
        {
            path: '/blog',
            name: 'blog',
            component: (resolve) => require(['@/views/blog'], resolve)
        },
        {
            path: '/type',
            name: 'type',
            component: (resolve) => require(['@/views/type'], resolve)
        },
        {
            path: '/tag',
            name: 'tag',
            component: (resolve) => require(['@/views/tag'], resolve)
        },
        {
            path: '/leetcode',
            name: 'leetcode',
            component: (resolve) => require(['@/views/leetcodeBlog'], resolve)
        },
        {
            path: '/archive',
            name: 'archive',
            component: (resolve) => require(['@/views/archive'], resolve)
        },
        {
            path: '/about',
            name: 'about',
            component: (resolve) => require(['@/views/about'], resolve)
        },
        {
            path: '/bj',
            name: 'bj',
            component: (resolve) => require(['@/views/notes'], resolve)
        },
        {
            path: '/admin/index',
            name: 'adminIndex',
            component: (resolve) => require(['@/views/admin/index'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/blogs',
            name: 'adminBlogs',
            component: (resolve) => require(['@/views/admin/blogs'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/editBlog',
            name: 'editBlog',
            component: (resolve) => require(['@/views/admin/editBlog'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/tags',
            name: 'tags',
            component: (resolve) => require(['@/views/admin/tags'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/editTag',
            name: 'editTag',
            component: (resolve) => require(['@/views/admin/editTag'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/types',
            name: 'types',
            component: (resolve) => require(['@/views/admin/types'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/editType',
            name: 'editType',
            component: (resolve) => require(['@/views/admin/editType'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/notes',
            name: 'notes',
            component: (resolve) => require(['@/views/admin/notes'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/admin/editNotes',
            name: 'editNotes',
            component: (resolve) => require(['@/views/admin/editNotes'], resolve),
            meta: {
                needLogin: true
            }
        },
        {
            path: '/login',
            name: 'login',
            component: login
        },
        {
            path: '/blog404',
            name: 'blog404',
            component: blog404
        },
        {
            path: '/blog500',
            name: 'blog500',
            component: blog500
        },
        {
            path: '/error',
            name: 'error',
            component: error
        }
    ]
})