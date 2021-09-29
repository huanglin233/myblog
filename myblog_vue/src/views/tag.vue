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
                            共<h2 class="ui orange header m-inline-block m-text-thin">{{tags.length}}</h2>个
                        </div>
                    </div>
                </div>
                <div class="ui attached segment m-padded-tb-large">
                    <a class="ui basic left pointing large label m-margin-tb-tiny" v-for="(item, index) in tags" :key="index" :class="{'teal': (tag != null && tag.id == item.id)}" @click="changeTag(item)">
                        {{item.name}}<div class="detail">{{item.blogsNum}}</div>
                    </a>
                </div>
                <!--content-->
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
import {queryTagList} from '../api/tag';
import {queryByBlogQuery} from '../api/blog';
import BlogCard from '../components/BlogCard.vue';

export default {
    name : 'tag',
    components: {
        BlogCard
    },
    data() {
        return {
            tag       : {},
            tags      : [],
            blogs     : [],
        }
    },
    mounted() {
        const tagId = this.$route.query.tagId;
        if(tagId != null && tagId != undefined) {
            this.queryTagList();
            this.tag.id = tagId;
            this.queryBlogList(1, 5);
        } else {
            this.init();
        }
        
    },
    methods: {
        // 初始化页面数据
        init : function() {
            const ref = this;
            queryTagList().then(response => {
                ref.tags = response.data.list;
                if(ref.tags.length > 0) {
                    ref.tag = ref.tags[0];
                    ref.queryBlogList(1, 5);
                }
            })
        },

        // 获取所有博客标签列表
        queryTagList : function(pageNum, pageSize) {
            const ref = this;
            queryTagList(pageNum, pageSize).then(response => {
                ref.tags = response.data.list;
            })
        },

        // 获取的博客列表信息
        queryBlogList : function(pageNum, pageSize) {
            const ref = this;
            const blogQuery = {
                tagId : this.tag.id,
                published : true
            } 
            queryByBlogQuery(pageNum, pageSize, blogQuery).then(response => {
                ref.blogs = response.data;
            })
        },

        // 切换博客标签
        changeTag : function(tag) {
            this.tag = tag;
            this.queryBlogList(1, 5);
        },

        rest : function() {
            this.init();
        }
    }
}
</script>