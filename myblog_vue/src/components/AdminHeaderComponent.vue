<template>
    <div id="AdminHeaderComponent">
        <nav class="ui inverted attached segment padded-tb-mini m-shadow-samll">
        <div class="ui container">
            <div class="ui inverted secondary stackable menu">
                <h2 class="ui teal header item">博客后台</h2>
                <router-link to="/admin/blogs" class="m-item item m-mobile-hide" v-bind:class="{active : AdminHeader == 'blogs'}"><i class="mini home icon m-padded-ri-min"></i>博客</router-link>
                <router-link to="/admin/types" class="m-item item m-mobile-hide" v-bind:class="{active : AdminHeader == 'types'}"><i class="mini idea icon  m-padded-ri-min"></i>分类</router-link>
                <router-link to="/admin/tags"  class="m-item item m-mobile-hide" v-bind:class="{active : AdminHeader == 'tags'}"><i class="mini tags icon  m-padded-ri-min"></i>标签</router-link>
                <div class="right m-item m-mobile-hide menu">
                    <div class="ui dropdown item">
                        <div class="text">
                            <img class="ui avatar image" src="https://unsplash.it/100/100?image=1005">
                            黄林
                        </div>
                        <i class="dropdown icon"></i>   
                        <div class="menu">
                            <a href="#" class="item" @click="loginOut">注销</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="#" class="ui black icon button m-right-top m-mobile-show" @click="togglessCalss">
            <i class="sidebar icon"></i>
        </a>
    </nav>
    </div>
</template>
<script>
import {loginOut} from '../api/login'
import {removeToken} from '../utils/token'

export default {
    name : 'AdminHeaderComponent',
    props : ['AdminHeader'],
    data() {
        return {

        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            $('.ui.dropdown').dropdown({
                on: 'hover'
            })
        },
        togglessCalss : function() {
           $('.m-item').toggleClass("m-mobile-hide");
        },

        loginOut : function(){
            let ref = this;
            loginOut().then(response => {
                removeToken();
                ref.$router.push('/')
            })
        }
    }
}
</script>