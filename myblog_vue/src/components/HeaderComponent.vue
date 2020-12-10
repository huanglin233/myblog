<template>
    <div id="HeaderComponent">
        <!-- 导航-->
        <nav class="ui inverted attached segment padded-tb-mini m-shadow-samll">
            <div class="ui container">
                <div class="ui inverted secondary stackable menu">
                    <h2 class="ui teal header item">Blog</h2>
                    <router-link to="/" class="m-item item m-mobile-hide" v-bind:class="{active : header == 'index'}"><i class="mini home icon m-padded-ri-min"></i>首页</router-link>
                    <router-link to="/type" class="m-item item m-mobile-hide" v-bind:class="{active : header == 'type'}"><i class="mini idea icon m-padded-ri-min"></i>分类</router-link>
                    <router-link to="/tag" class="m-item item m-mobile-hide" v-bind:class="{active : header == 'tag'}"><i class="mini tags icon m-padded-ri-min"></i>标签</router-link>
                    <router-link to="/archive" class="m-item item m-mobile-hide" v-bind:class="{active : header == 'archive'}"><i class="mini clone icon m-padded-ri-min"></i>归档</router-link>
                    <router-link to="/about" class="m-item item m-mobile-hide" v-bind:class="{active : header == 'about'}"><i class="mini info icon m-padded-ri-min"></i>关于我</router-link>
                    <div class="right m-item item m-mobile-hide">
                        <form name="search" action="#" method="post" target="_blank">  
                            <div class="ui icon inverted transparent input m-margin-tb-tiny">
                                <input type="text" name="query" placeholder="Search.....">
                                <i class="search link icon"></i>
                            </div>
                        </form>
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
export default {
    name  : 'HeaderComponent',
    props : ['header'],
    date() {
        return {
        }
    },
    mounted() {
       
    },
    methods: {
        togglessCalss : function() {
           $('.m-item').toggleClass("m-mobile-hide");
        }
    },
}
</script>