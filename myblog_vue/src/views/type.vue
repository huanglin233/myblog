<template>
    <div id="type">
        <HeaderComponent header="type"></HeaderComponent>

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
                        <a href="#" class="ui basic button" :class="{'teal':(type != null && item.id == type.id)}">{{item.name}}</a>
                        <div class="ui basic left pointing label" :class="{'teal' : (type != null && item.id == type.id)}">{{item.blogs != null ? item.blogs.total : 0}}</div>
                    </div>
                </div>
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
import {queryTypeById, queryTypeList} from '../api/type';
import {queryByBlogQuery} from '../api/blog';

export default {
    name : 'type',
    data() {
        return {
            types     : [],
            type      : {},
            blogs     : [],
            blogQuery : {
                recommend : undefined,
                tagId     : undefined,
                title     : undefined,
                typeId    : undefined,
                published : true
            }
        }
    },
    mounted() {
        let typeId = this.$route.query.typeId;
        if(typeId != undefined && typeId != null) {
            this.queryTypeById(typeId);
            this.queryTypeList(1, 999);
        } else {
            this.queryBlogList(1, 5);
        }
    },
    methods: {
        // 根据博客类型id获取博客信息
        queryTypeById (id) {
            let ref = this;
            queryTypeById(id).then(response => {
                console.log(response.data);
                ref.type  = response.data;
                ref.blogs = ref.type.blogs;
            });
        },

        // 获取所有的博客分类列表
        queryTypeList(pageNum, pageSize) {
            let ref = this;
            queryTypeList(pageNum, pageSize).then(response => {
                console.log(response.data);
                ref.types = response.data.list;
            });
        },

        // 获取所有分类的博客类型列表
        queryBlogList(pageNum, pageSize) {
            let ref = this;
            queryByBlogQuery(pageNum, pageSize, this.blogQuery).then(response => {
                console.log(response.data);
                ref.blogs = response.data.blogs;
                ref.types = response.data.types;
            });
        }
    },
}
</script>