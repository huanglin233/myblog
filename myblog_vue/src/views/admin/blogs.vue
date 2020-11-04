<template>
    <div id="blogs">
        <AdminHeaderComponent AdminHeader="blogs"></AdminHeaderComponent>
        <!-- 列表 -->
        <div class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editBlog" class="item">发布</router-link>
                    <router-link to="/admin/blogs" class="teal active item">列表</router-link>
                </div>
            </div>
        </div>
        <!--中间内容-->
        <div class="m-container-samll m-padded-tb-big">
            <div class="ui container">
                <div class="ui secondary segemnt form">
                    <input type="hidden" name="page">
                    <div class="inline fields">
                        <div class="field">
                            <input type="text" v-model="blogQuery.title" placeholder="标题">
                        </div>
                        <div class="field">
                            <div class="ui labeled action input">
                                <div class="ui type selection dropdown">
                                    <i class="dropdwon icon"></i>
                                    <div class="default text">分类</div>
                                    <select v-model="blogQuery.typeId">
                                        <option :value="type.id" v-for="(type, index) in types" :key="index" v-text="type.name" class="item"></option>
                                    </select>
                                </div>
                                <button id="clear-btn" class="ui compact button" @click="clear">
                                    clear
                                </button>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui checkbox">
                                <input type="checkbox" v-model="blogQuery.recommend">
                                <label for="recommend">推荐</label>
                            </div>
                        </div>
                        <div class="filed">
                            <button type="button" id="search-btn" class="ui mini basic button" @click="search">
                                <i class="search icon"></i>搜索
                            </button>
                        </div>
                    </div>
                </div>
                <div id="table-container">
                    <table th:fragment="blogList" class="ui compact teal table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>标题</th>
                                <th>类型</th>
                                <th>推荐</th>
                                <th>状态</th>
                                <th>更新时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in blogs.list" :key="index">
                                <td v-text="index + 1"></td>
                                <td v-text="item.title"></td>
                                <td v-text="item.type.name">认知升级</td>
                                <td v-text="item.recommend == true ? '是' : '否`'">是</td>
                                <td v-text="item.published == true ? '发布' : '草稿'">草稿</td>
                                <td v-text="item.updateTime"></td>
                                <td>
                                    <router-link :to="'/admin/editBlog?blogId=' + item.id" class="ui mini teal basic button">编辑</router-link>
                                    <a class="ui mini red basic button" @click="deleteBlog(item.id)">删除</a>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="7">
                                    <div class="ui mini pagination secondary menu" th:if="${page.pages} > 1">
                                        <a @click="queryBlogs(blogs.pageNum -1, 1)" v-if="blogs.pageNum > 1" class="ui mini floated teal basic button item">上一页</a>
                                        <a @click="queryBlogs(blogs.pageNum +1, 1)" v-if="blogs.pageNum < blogs.pages" class="ui mini floated teal basic button item">下一页</a>
                                    </div>
                                    <router-link to="/admin/editBlog" class="ui mini right floated teal basic button">新增</router-link>
                                </th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryByBlogQuery, deleteBlogById} from '../../api/blog'

export default {
    name : 'blogs',
    data() {
        return {
            blogs     : [],
            types     : [],
            blogQuery : {
                recommend : undefined,
                tagId     : undefined,
                title     : undefined,
                typeId    : undefined
            }
        }
    },
    mounted() {
        this.init();
    },
    methods : {
        /**
         * 初始化页面博客列表
         */
        init : function() {
            $('.ui.dropdown').dropdown({
                on: 'hover'
            });

            this.queryByBlogQuery(1, 5);
        },

        /**
         * 分页查询博客列表
         */
        queryBlogs : function(pageNum, pageSize) {
            let ref = this;
            queryList(pageNum, pageSize).then(response => {
                ref.blogs = response.data.blogs;
                ref.types = response.data.types;
            }).catch(error => {
                console.log(error);
            });
        },

        /**
         * 根据条件进行分页查询
         */
        queryByBlogQuery : function(pageNum, pageSize) {
            let ref = this;
            queryByBlogQuery(pageNum, pageSize, this.blogQuery).then(response => {
                console.log(response)
                ref.blogs = response.data.blogs;
                ref.types = response.data.types;
            }).catch(error => {
                console.log(error);
            });
        },

        clear : function() {
            $('.ui.type.dropdown').dropdown('clear');
        },

        search : function() {
            this.queryByBlogQuery(1, 5);
        },

        deleteBlog : function(id) {
            let ref = this;

            deleteBlogById(id).then(response => {
                console.log(response);
                ref.queryByBlogQuery(1, 5)
            }).catch(error => {
                console.log(error);
            })
        }
    }
}
</script>