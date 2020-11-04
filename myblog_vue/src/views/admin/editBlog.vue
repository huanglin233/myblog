<template>
    <div id="editBlog">
        <AdminHeaderComponent AdminHeader="blogs"></AdminHeaderComponent>
        <!-- 列表 -->
        <div class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editBlog" class="teal active item">发布</router-link>
                    <router-link to="/admin/blogs" class="item">列表</router-link>
                </div>
            </div>
        </div>

        <!--中间内容-->
        <div class="m-container m-padded-tb-big">
            <div class="ui container">
                <form id="blog-form" action="#" class="ui form" method="post">
                    <div class="required field">
                        <div class="ui left labeled input">
                            <div class="ui dropdown selection compact teal basic label">
                                <i class="dropdown icon"></i>
                                <span class="text" id="flag" v-text="blog.flag != null ? blog.flag : '原创'"></span>
                                <div class="menu">
                                    <div class="item">原创</div>
                                    <div class="item">转载</div>
                                    <div class="item">翻译</div>
                                </div>
                            </div>
                            <input type="text" name="title" placeholder="标题" v-model="blog.title">
                        </div>
                    </div>
                    <MarkdownEditor content="content" :markdown="blog.content" ref="markDownEditor" style="z-index: 1"></MarkdownEditor>
                    <div class="field">
                        <div id="md-content" style="z-index: 1 !important;">
                            <textarea placeholder="博客内容" name="content" style="display: none" ></textarea>
                        </div>
                    </div>

                    <div class="required field">
                        <div id="md-content">
                            <textarea placeholder="博客内容" name="content" style="display: none;" ></textarea>
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="required field">
                            <div class="ui left labeled action input">
                                <label class="ui compact teal basic label">分类</label>
                                <div class="ui fluid selection dropdown">
                                    <input  name="gender" type="hidden" id="type" v-model="blog.typeId">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                    <div class="menu">
                                        <div class="item" v-for="(type, index) in types" :key="index" :value="type.id" v-text="type.name"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left labeled action input">
                                <label class="ui compact teal basic label">标签</label>
                                <div class="ui fluid selection multiple search dropdown">
                                    <input name="gender" type="hidden" id="tags" v-model="tags2">
                                    <i class="dropdown icon"></i>
                                    <div class="default text"></div>
                                    <div class="menu">
                                        <div class="item" v-for="(tag, index) in tags" :key="index" v-text="tag.name" :data-value="tag.name"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- <div class="ui multiple selection dropdown">
                            <input name="gender" type="hidden" v-model="tags2">
                            <i class="dropdown icon"></i>
                            <a class="ui label" data-value="JAVA">
                                JAVA
                                <i class="delete icon"></i>
                            </a>
                            <div class="default text">默认</div>
                            <div class="menu">
                                <div class="item" data-value="0">值</div>
                                <div class="item" data-value="1">另一个值</div>
                                <div class="item" data-value="default">默认值</div>
                                <div class="item" v-for="(tag, index) in tags" :key="index" v-text="tag.name" :data-value="tag.name"></div>
                            </div>
                        </div> -->
                    </div>
            
                    <div class="field">
                        <div class="ui left labeled input">
                            <label class="ui teal basic label">首图</label>
                            <input type="text" name="firstPicture" v-model="blog.firstPicture" placeholder="首图引用地址">
                        </div>
                    </div>

                    <div class="required field">
                        <textarea name="description" v-model="blog.description" placeholder="博客描述..." maxlength="200"></textarea>
                    </div>

                    <div class="inline fields">
                        <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" id="recommend" name="recommend" class="hidden" v-model="blog.recommend">
                            <label for="recommend">推荐</label>
                        </div>
                        </div>
                        <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" id="shareStatement" name="shareStatement" class="hidden" v-model="blog.shareStatement">
                            <label for="shareStatement">转载声明</label>
                        </div>
                        </div>
                        <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" id="appreciation" name="appreciation" class="hidden" v-model="blog.appreciation">
                            <label for="appreciation">赞赏</label>
                        </div>
                        </div>
                        <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" id="commenabled" name="commenabled" class="hidden" v-model="blog.commenabled">
                            <label for="commenabled">评论</label>
                        </div>
                        </div>
                    </div>
            
                    <div class="ui error message"></div>
            
                    <div class="ui right aligned container">
                        <button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button>
                        <button class="ui secondary button" type="button" id="save-btn">保存</button>
                        <button class="ui teal button" type="button" id="publish-btn" @click="show">发布</button>
                    </div>
                </form>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {addBlog, updateBlog} from '../../api/blog';
import {queryTypeList} from '../../api/type';
import {queryTagList} from '../../api/tag';

export default {
    name : 'editBlog',
    data() {
        return {
            blog  : {
                id             : undefined,
                title          : undefined,
                content        : undefined,
                firstPicture   : undefined,
                flag           : undefined,
                views          : undefined,
                appreciation   : false,
                shareStatement : false,
                commenabled    : false,
                published      : false,
                recommend      : false,
                createTime     : undefined,
                updateTime     : undefined,
                tagIds         : undefined,
                description    : undefined,
                typeId         : undefined
            },
            types : {},
            tags  : {},
            type  : '',
            tags2 : '',
            leetcode : 'java'
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init : function() {
            $('.ui.form').form({
                fields : {
                    title : {
                    identifier: 'title',
                    rules: [{
                        type : 'empty',
                        prompt: '标题：请输入博客标题'
                    }]
                    },
                    content : {
                    identifier: 'content',
                    rules: [{
                        type : 'empty',
                        prompt: '标题：请输入博客内容'
                    }]
                    },
                    typeId : {
                    identifier: 'type.id',
                    rules: [{
                        type : 'empty',
                        prompt: '标题：请输入博客分类'
                    }]
                    },
                    firstPicture : {
                    identifier: 'firstPicture',
                    rules: [{
                        type : 'empty',
                        prompt: '标题：请输入博客首图'
                    }]
                    },
                    description : {
                    identifier: 'description',
                    rules: [{
                        type : 'empty',
                        prompt: '标题：请输入博客描述'
                    }]
                    }
                }
            });

            $('.ui.dropdown').dropdown();
            this.queryTypeList();
            this.queryTagList();
        },
        
        /** 查找分类列表 */
        queryTypeList : function() {
            let ref = this;
            queryTypeList(1, 999).then(response => {
                console.log(response);
                ref.types = response.data.list;
            })
        },

        /** 查找标签列表 */
        queryTagList : function() {
            let ref = this;
            queryTagList(1, 999).then(response => {
                console.log(response);
                ref.tags = response.data.list;
            })
        },

        show : function() {
            this.blog.flag = $('#flag')[0].textContent;
            this.blog.content = this.$refs.markDownEditor.getContent();
            console.log(this.blog);
            console.log($('#type').val());
            console.log($('#tags').val());
            console.log(this.tags2);
        }
    }
}
</script>