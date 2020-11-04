// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue    from 'vue'
import App    from './App'
import router from './router'
import tocbot from 'tocbot'
import axios  from 'axios'

/** 引入全局的js */
import '../static/lib/semantic/semantic.min.js'
import '../static/lib/prism/prism.js'
import '../static/lib/scrollTo/jquery.scrollTo.js'

/** 引入全局的css */
import 'tocbot/dist/tocbot.css'
import '../static/css/me.css'
import '../static/css/typo.css'
import '../static/css/animate.css'
import '../static/lib/semantic/semantic.min.css'
import '../static/lib/prism/prism.css'

/** 引入Token工具类 */
import {getToken} from './utils/token'

/** 引入全局组件 */
import HeaderComponent      from './components/HeaderComponent.vue'
import AdminHeaderComponent from './components/AdminHeaderComponent.vue'
import FooterComponent      from './components/FooterComponent.vue'
import MarkdownEditor       from './components/MarkdownEditor.vue'


Vue.component("HeaderComponent", HeaderComponent)
Vue.component("AdminHeaderComponent", AdminHeaderComponent)
Vue.component("FooterComponent", FooterComponent)
Vue.component("MarkdownEditor", MarkdownEditor)

Vue.config.productionTip = false
Vue.prototype.$axios     = axios
Vue.use(tocbot)

router.beforeEach(function(to, from, next){
  if(to.meta.needLogin) {
    // 页面需要登录
    if(getToken()) {
      next();
    } else {
      //next可以传递一个路由对象作为参数 表示需要跳转到的页面
      next({
        name : 'login'
      });
    }
  } else {
    next();
  }
}) 


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
