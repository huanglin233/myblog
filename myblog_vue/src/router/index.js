import Vue        from 'vue'
import Router     from 'vue-router'
import index      from '@/views/index'
import blog       from '@/views/blog'
import type       from '@/views/type'
import tag        from '@/views/tag'
import archive    from '@/views/archive'
import about      from '@/views/about'
import adminIndex from '@/views/admin/index'
import adminBlogs from '@/views/admin/blogs'
import editBlog   from '@/views/admin/editBlog'
import tags       from '@/views/admin/tags'
import editTag    from '@/views/admin/editTag'
import types      from '@/views/admin/types'
import editType   from '@/views/admin/editType'
import login      from '@/views/admin/login'
import blog404    from '@/views/error/blog404'
import blog500    from '@/views/error/blog500'
import error      from '@/views/error/error'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/blog',
      name: 'blog',
      component: blog
    },
    {
      path: '/type',
      name: 'type',
      component: type
    },
    {
      path: '/tag',
      name: 'tag',
      component: tag
    },
    {
      path: '/archive',
      name: 'archive',
      component: archive
    },
    {
      path: '/about',
      name: 'about',
      component: about
    },
    {
      path: '/admin/index',
      name: 'adminIndex',
      component: adminIndex,
      meta: {
        needLogin: true
      }
    },
    {
      path: '/admin/blogs',
      name: 'adminBlogs',
      component: adminBlogs,
      meta: {
        needLogin: true
      }
    } ,
    {
      path: '/admin/editBlog',
      name: 'editBlog',
      component: editBlog,
      meta: {
        needLogin: true
      }
    },
    {
      path:'/admin/tags',
      name: 'tags',
      component:tags,
      meta: {
        needLogin: true
      }
    },
    {
      path:'/admin/editTag',
      name: 'editTag',
      component: editTag,
      meta: {
        needLogin: true
      }
    },
    {
      path: '/admin/types',
      name: 'types',
      component: types,
      meta: {
        needLogin: true
      }
    },
    {
      path: '/admin/editType',
      name: 'editType',
      component: editType,
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