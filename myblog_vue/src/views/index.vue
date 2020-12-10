<template>
    <div id="index">
        <HeaderComponent header="index"></HeaderComponent>

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
                        <div class="ui attached segment" v-for="(item, index) in blogs.list" :key="index">
                            <div class="ui padded vertical segment m-padded-tb-large m-margin-lr m-mobile-lr-clear">
                                <div class="ui mobile reversed stackable grid">
                                    <div class="eleven wide column">
                                        <h3 class="ui header">
                                            <router-link :to="'/blog?blogId=' + item.id" target="_blank" class=m-black>{{item.title}}</router-link>
                                        </h3>
                                        <p class="m-text">
                                            {{item.description}}
                                        </p>
                                        <div class="ui grid">
                                            <div class="eleven wide column">
                                                <div class="ui mini horizontal link list">
                                                    <div class="item">
                                                        <img class="ui avatar image" :src="item.user.avatar" alt="">
                                                        <div class="content">
                                                            <router-link to="/about" class="header">{{item.user.nickname}}</router-link>
                                                        </div>
                                                    </div>
                                                    <div class="item">
                                                        <i class="calendar icon"></i><span>{{item.updateTime}}</span>
                                                    </div>
                                                    <div class="item">
                                                        <i class="eye icon"></i><span>{{item.views}}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="right aligned five wide column" v-if="item.type != null">
                                                <a href="#" target="_blank" class="ui teal basic label m-padded-tb-tiny m-text-thin">{{item.type.name}}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="five wide column">
                                        <a href="#"  target="_blank">
                                            <img class="ui rounded image" :src="item.firstPicture">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--分页footer-->
                        <div class="ui bottom attached segment">
                            <div class="ui middle aligned two column grid">
                                <div class="column">
                                    <a href="#"  class="ui mini teal basic button">上一页</a>
                                </div>
                                <div class="right aligned column">
                                    <a href="#"  class="ui mini teal basic button">下一页</a>
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
                                        <a href="#"  target="_blank">more<i class="angle double right icon"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="ui teal segment">
                                <div class="ui fluid vertical menu">
                                    <a href="#"  target="_blank" class="item" >
                                        <span ></span>
                                        <div class="ui teal basic left pointing label" >13</div>
                                    </a>
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
                                        <a href="#"  target="_blank">more<i class="angle double right icon"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="ui teal segment">
                                <a href="#"  target="_blank" class="ui teal tag label m-margin-tb-tiny" >
                                    <span ></span>
                                    <div class="detail" ></div>
                                </a>
                            </div>
                        </div>
                        <!--最新推荐-->
                        <div class="ui segments m-margin-top-large">
                            <div class="ui secondary segment">
                                <i class="bookmark icon"></i>最新推荐
                            </div>
                            <div class="ui segment" >
                                <a href="#"  target="_blank" class="m-black m-text-thin" ></a>
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
import {queryList, queryRecommentBlogTop} from '../api/blog';
import {queryTypeList} from '../api/type';
import {queryTagList} from '../api/tag';

export default {
    name : 'index',
    data() {
        return {
            blogs          : [],
            types          : [],
            tags           : [],
            recommendBlogs : [],
            blogQuery      : {
                recommend : undefined,
                tagId     : undefined,
                title     : undefined,
                typeId    : undefined
            }
        }
    },
    mounted() {
        this.queryList(1, 5);
        this.queryRecommentBlogTop(3);
        this.queryTagList(1, 999);
    },
    methods: {
        // 获取博客列表
        queryList : function(pageNum, pageSize) {
            let ref = this;
            queryList(pageNum, pageSize).then(response => {
                ref.blogs = response.data.blogs;
                ref.types = response.data.types;
                console.log(ref.blogs);
                console.log(ref.types);
            })
        },

        // 获取最新推荐
        queryRecommentBlogTop : function(num) {
            let ref = this;
            queryRecommentBlogTop(num).then(response => {
                ref.recommendBlogs = response.data;
                console.log(ref.recommendBlogs);
            })
        },

        // 获取博客分类
        queryTypeList : function(pageNum, pageSize) {
            let ref = this;
            queryTypeList(pageNum, pageSize).then(response => {
                ref.types = response.data;
                console.log(ref.types);
            })
        },

        // 获取博客标签
        queryTagList : function(pageNum, pageSize) {
            let ref = this;
            queryTagList(pageNum, pageSize).then(response => {
                ref.tags = response.data;
                console.log(ref.tags);
            })
        }
    },
}
</script>