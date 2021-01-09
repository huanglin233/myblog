<template>
    <div id="HeaderComponent">
        <!-- 导航-->
        <nav class="ui attached segment padded-tb-mini m-shadow-samll">
            <div class="ui container">
                <div class="ui secondary stackable menu">
                    <h2 class="ui teal header item">HLBlog</h2>
                    <a class="m-item item m-mobile-hide" v-bind:class="{active : header == 'index'}" @click="rest('index')"><i class="mini home icon m-padded-ri-min"></i>首页</a>
                    <a class="m-item item m-mobile-hide" v-bind:class="{active : header == 'type'}" @click="rest('type')"><i class="mini idea icon m-padded-ri-min"></i>分类</a>
                    <a class="m-item item m-mobile-hide" v-bind:class="{active : header == 'tag'}" @click="rest('tag')"><i class="mini tags icon m-padded-ri-min"></i>标签</a>
                    <a class="m-item item m-mobile-hide" v-bind:class="{active : header == 'archive'}" @click="rest('archive')"><i class="mini clone icon m-padded-ri-min"></i>归档</a>
                    <a class="m-item item m-mobile-hide" v-bind:class="{active : header == 'about'}" @click="rest('about')"><i class="mini info icon m-padded-ri-min"></i>关于我</a>
                    <router-link to="/admin/index" class="m-item item m-mobile-hide"><i class="mini info icon m-padded-ri-min"></i>后台</router-link>
                    <div class="right m-item item m-mobile-hide">
                        <form name="search" action="#" method="post" target="_blank">
                            <div class="ui teal icon transparent input m-margin-tb-tiny">
                                <input type="text" name="query" v-model="searchText" placeholder="Search.....">
                                <i class="search teal link icon" @click="search()"></i>
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
            searchText : undefined
        }
    },
    created() {
        this.searchText = null;
    },
    mounted() {
    },
    methods: {
        togglessCalss : function() {
           $('.m-item').toggleClass("m-mobile-hide");
        },

        rest : function(tag) {
            if(this.header == tag) {
                this.$emit("rest");
            } else {
                this.$router.push({path : '/' + (tag != 'index' ? tag : '')})
            }
        },

        search : function() {
            if(this.header == 'index') {
                this.$emit('search', this.searchText);
            } else {
                this.$router.push({path : '/', query : {title : this.searchText}});
            }
        }
    },
}
</script>