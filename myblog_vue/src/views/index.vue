<template>
    <div id="index">
        <HeaderComponent header="index" @search="search" @rest="rest"></HeaderComponent>

        <div class="m-container m-padded-tb-big">
            <div class="ui container">
                <div class="ui stackable grid">
                    <!--左边博客-->
                    <div class="eleven wide column">
                        <!--header-->
                        <div class="ui top attached segment">
                            <div class="ui middle aligned two column grid">
                                <div class="column">
                                    <h3 class="ui teal header">博客</h3>
                                </div>
                                <div class="right aligned column">
                                    共<h2 class="ui orange header m-inline-block m-text-thin">{{blogs.total}}</h2>篇
                                </div>
                            </div>
                        </div>
                        <!--content-->
                        <blog-card :blogs=blogs></blog-card>
                        <!--分页footer-->
                        <div class="ui bottom attached segment">
                            <div class="ui middle aligned two column grid">
                                <div class="column" v-if="blogs.hasPreviousPage">
                                    <a class="ui mini teal basic button" @click="queryList(blogs.pageNum - 1,5)">上一页</a>
                                </div>
                                <div class="right aligned column" v-if="blogs.hasNextPage">
                                    <a class="ui mini teal basic button" @click="queryList(blogs.pageNum + 1, 5)">下一页</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--右边的top-->
                    <div class="five wide column">
                        <!--分类-->
                        <div class="ui segments">
                            <div class="ui secondary segment">
                                <div class="ui two column grid">
                                    <div class="column">
                                        <i class="idea icon"></i>分类
                                    </div>
                                    <div class="right aligned column">
                                        <router-link to="/type"  target="_blank">more<i class="angle double right icon"></i></router-link>
                                    </div>
                                </div>
                            </div>
                            <div class="ui teal segment">
                                <div class="ui fluid vertical menu" v-for="(item, index) in types.list" :key="index">
                                    <router-link :to="'/type?typeId=' + item.id"  target="_blank" class="item"  >
                                        <span>{{item.name}}</span>
                                        <div class="ui teal basic left pointing label">{{item.blogsNum}}</div>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                        <!--标签-->
                        <div class="ui segments m-margin-top-large">
                            <div class="ui secondary segment">
                                <div class="ui two column grid">
                                    <div class="column">
                                        <i class="tags icon"></i>标签
                                    </div>
                                    <div class="right aligned column">
                                        <router-link to="/tag" target="_blank">more<i class="angle double right icon"></i></router-link>
                                    </div>
                                </div>
                            </div>
                            <div class="ui teal segment">
                                <router-link :to="'/tag?tagId=' + item.id" target="_blank" class="ui teal tag label m-margin-tb-tiny" v-for="(item, index) in tags.list" :key="index">
                                    <span >{{item.name}}</span>
                                    <div class="detail" >{{item.blogsNum}}</div>
                                </router-link>
                            </div>
                        </div>
                        <!--最新推荐-->
                        <div class="ui segments m-margin-top-large">
                            <div class="ui secondary segment">
                                <i class="bookmark icon"></i>最新推荐
                            </div>
                            <div class="ui segment" v-for="(item, index) in recommentBlogs" :key="index">
                                <router-link :to="'/blog?blogId=' + item.id"  target="_blank" class="m-black m-text-thin" >{{item.title}}</router-link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryByBlogQuery, queryRecommentBlogTop} from '../api/blog';
import {queryTypeList} from '../api/type';
import {queryTagList} from '../api/tag';
import BlogCard from '../components/BlogCard.vue'

export default {
    name : 'index',
    components: {
        BlogCard
    },
    data() {
        return {
            blogs          : [],
            types          : [],
            tags           : [],
            recommentBlogs : [],
            searchKey      : null
        }
    },
    mounted() {
        this.searchKey = this.$route.query.title;
        this.queryList(1, 5);
        this.queryRecommentBlogTop(3);
        this.queryTypeList(1, 5);
        this.queryTagList(1, 5);
    },
    methods: {
        // 获取博客列表
        queryList : function(pageNum, pageSize) {
            const ref = this;
            const blogQuery = {
                title : this.searchKey,
                published : true
            }
            queryByBlogQuery(pageNum, pageSize, blogQuery).then(response => {                
                for(let blog of response.data.list) {
                    let updateTime  = new Date(blog.updateTime);
                    blog.updateTime = updateTime.getFullYear() + '-' + (updateTime.getMonth() + 1) + '-' + updateTime.getDate();
                }
                ref.blogs = response.data;
            })
        },

        // 获取最新推荐
        queryRecommentBlogTop : function(num) {
            const ref = this;
            queryRecommentBlogTop(num).then(response => {
                ref.recommentBlogs = response.data;
            })
        },

        // 获取博客分类
        queryTypeList : function(pageNum, pageSize) {
            const ref = this;
            queryTypeList(pageNum, pageSize).then(response => {
                ref.types = response.data;
            })
        },

        // 获取博客标签
        queryTagList : function(pageNum, pageSize) {
            const ref = this;
            queryTagList(pageNum, pageSize).then(response => {
                ref.tags = response.data;
            })
        },

        search : function(title) {
            this.searchKey = title;
            this.queryList(1, 5);
        },

        rest : function() {
            this.blogQuery.title = null;
            this.queryList(1,5);
        }
    },
}
</script>