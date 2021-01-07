<template>
    <div id="archive">
        <HeaderComponent header="archive"></HeaderComponent>

        <div class="m-container m-padded-tb-big">
            <div class="ui container">
                <!--header-->
                <div class="ui top attached padded segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <h3 class="ui teal header">归档</h3>
                        </div>
                        <div class="right aligned column">
                            共<h2 class="ui orange header m-inline-block m-text-thin">{{count}}</h2>篇
                        </div>
                    </div>
                </div>
                <!--content-->
                <div >

                </div>
                <div v-for="(value, key) in blogs" :key="key">
                    <h3 class="ui center aligned header m-padded">{{key}}</h3>
                    <div class="ui fluid vertical menu">
                        <router-link :to="'/blog?blogId=' + item.id"  target="_blank" class="item" v-for="(item, index) in value" :key="index">
                            <span>
                                <i class="mini teal circle icon"></i>
                                <span>{{item.title}}</span>
                                <i class="ui teal basic left pointing label m-padded-mini">{{item.createTime | formatDate}}</i>
                            </span>
                            <div class="ui orange basic left pointing label m-padded-mini">{{item.flag}}</div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryArchives} from '../api/blog'

export default {
    name : 'archive',
    data() {
        return {
            count : undefined,
            blogs : []
        }
    },
    mounted() {
        this.queryArchives();
    },
    methods : {
       queryArchives : function(params) {
            let ref = this;
            queryArchives().then(response => {
                ref.count = response.data.count;
                ref.blogs = response.data.blogs;
            })
        }
    },
    filters : {
        formatDate : function(time) {
            let updateTime = new Date(time);

            return updateTime.getFullYear() + '-' + (updateTime.getMonth() + 1) + '-' + updateTime.getDate();
        }
    }
}
</script>