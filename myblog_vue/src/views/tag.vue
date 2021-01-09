<template>
    <div id="tag">
        <HeaderComponent header="tag" @rest="rest"></HeaderComponent>

        <div class="m-container-small m-padded-tb-big">
            <div class="ui container">
                <div class="ui top attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <h3 class="ui teal header">标签</h3>
                        </div>
                        <div class="right aligned column">
                            共<h2 class="ui orange header m-inline-block m-text-thin">{{tags.total}}</h2>个
                        </div>
                    </div>
                </div>
                <div class="ui attached segment m-padded-tb-large">
                    <a class="ui basic left pointing large label m-margin-tb-tiny" v-for="(item, index) in tags.list" :key="index" :class="{'teal': (tag != null && tag.id == item.id)}" @click="queryTagById(item.id)">
                        {{item.name}}<div class="detail">{{item.blogs.total}}</div>
                    </a>
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
import {queryTagById, queryTagList} from '../api/tag';
import {queryByBlogQuery} from '../api/blog';

export default {
    name : 'tag',
    data() {
        return {
            tag       : {},
            tags      : [],
            blogs     : [],
            blogQuery : {
                recomment : undefined,
                tagId     : -1,
                title     : undefined,
                typeId    : undefined,
                published : true
            }
        }
    },
    mounted() {
        let tagId = this.$route.query.tagId;
        if(tagId != null && tagId != undefined) {
            this.queryTagById(tagId);
        } else {
            this.queryBlogList(1, 5);
        }
        this.queryTagList(1, 999);
    },
    methods: {
        // 根据博客标签id获取标签信息
        queryTagById : function(id) {
            let ref = this;
            queryTagById(id).then(response => {
                ref.tag = response.data;
                ref.blogs = ref.tag.blogs;
            });
        },

        // 获取所有博客标签列表
        queryTagList : function(pageNum, pageSize) {
            let ref = this;
            queryTagList(pageNum, pageSize).then(response => {
                ref.tags = response.data;
            })
        },

        // 获取的博客列表信息
        queryBlogList : function(pageNum, pageSize) {
            let ref = this;
            if(this.tag != null && this.tag.id != undefined) {
                this.blogQuery.tagId = this.tag.id;
            }
            queryByBlogQuery(pageNum, pageSize, this.blogQuery).then(response => {
                ref.blogs = response.data.blogs;
            })
        },

        rest : function() {
            this.tag = null;
            this.queryBlogList(1, 5);
        }
    }
}
</script>