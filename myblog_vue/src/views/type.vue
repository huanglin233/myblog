<template>
    <div id="type">
        <HeaderComponent header="type" @rest="rest"></HeaderComponent>

        <div class="m-container-small m-padded-tb-big">
            <div class="ui container">
                <div class="ui top attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <h3 class="ui teal header">分类</h3>
                        </div>
                        <div class="right aligned column">
                            共<h2 class="ui orange header m-inline-block m-text-thin">{{types.length}}</h2>个
                        </div>
                    </div>
                </div>
                <div class="ui attached segment m-padded-tb-large">
                    <div class="ui labeled button m-margin-tb-tiny" v-for="(item, index) in types" :key="index">
                        <a href="#" class="ui basic button" :class="{'teal':(type != null && item.id == type.id)}" @click="changeType(item)">{{item.name}}</a>
                        <div class="ui basic left pointing label" :class="{'teal' : (type != null && item.id == type.id)}">{{item.blogsNum}}</div>
                    </div>
                </div>
                <blog-card :blogs="blogs"></blog-card>
                <!--分页footer-->
                 <div class="ui bottom attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column" v-if="blogs.hasPreviousPage">
                            <a class="ui mini teal basic button" @click="queryBlogList(blogs.pageNum - 1,5)">上一页</a>
                        </div>
                        <div class="right aligned column" v-if="blogs.hasNextPage">
                            <a class="ui mini teal basic button" @click="queryBlogList(blogs.pageNum + 1, 5)">下一页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryTypeList} from '../api/type';
import {queryByBlogQuery} from '../api/blog';
import BlogCard from '../components/BlogCard.vue';

export default {
    name : 'type',
    components: {
        BlogCard
    },
    data() {
        return {
            types     : [],
            type      : {},
            blogs     : [],
        }
    },
    mounted() {
        const typeId = this.$route.query.typeId;
        if(typeId != undefined && typeId != null) {
            this.queryTypeList();
            this.type.id = typeId;
            this.queryBlogList(1, 5);
        } else {
            this.init();
        }
    },
    methods: {
       // 初始化页面数据
        init : function() {
            const ref = this;
            queryTypeList().then(response => {
                ref.types = response.data.list;
                if(ref.types.length > 0) {
                    ref.type = ref.types[0];
                    this.queryBlogList(1, 5);
                }
            })
        },

        // 获取所有的博客分类列表
        queryTypeList : function(pageNum, pageSize) {
            const ref = this;
            queryTypeList(pageNum, pageSize).then(response => {
                ref.types = response.data.list;
            });
        },

        // 根据指定的分类和分页查询博客列表
        queryBlogList : function (pageNum, pageSize) {
            const ref = this;
            const blogQuery = {
                typeId: this.type.id,
                published: true
            }
            queryByBlogQuery(pageNum, pageSize, blogQuery).then(response => {
                ref.blogs = response.data;
            })
        },

        // 切换博客分类
        changeType : function (type) {
            this.type = type;
            this.queryBlogList(1, 5);
        }, 

        rest : function() {
            this.init();
        }
    },
}
</script>