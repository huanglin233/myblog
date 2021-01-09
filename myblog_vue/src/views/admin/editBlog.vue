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
                    <MarkdownEditor v-if="blog.content != null || blog.content != undefined" content="content" :markdown="blog.content" ref="markDownEditor" style="z-index: 1"></MarkdownEditor>
                    <div class="two fields">
                        <div class="required field">
                            <div class="ui left labeled action input">
                                <label class="ui compact teal basic label">分类</label>
                                <div class="ui fluid selection dropdown">
                                    <input name="gender" type="hidden" id="typeId" :value="blog.typeId">
                                    <i class="dropdown icon"></i>
                                    <div class="default text" v-if="blog.type == null || blog.type == undefined">选择分类</div>
                                    <div class="text" v-text="blog.type.name" v-else></div>
                                    <div class="menu">
                                        <div class="item" v-for="(type, index) in types" :key="index" :data-value="type.id" v-text="type.name" @click="changeType(type.id)"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left labeled action input">
                                <label class="ui compact teal basic label">标签</label>
                                <div class="ui fluid selection multiple search dropdown">
                                    <input name="gender" type="hidden" id="tagIds" :value="blog.tagIds" v-if="blog.tagIds != null || blog.tagIds != undefined">
                                    <input name="gender" type="hidden" id="tagIds" v-else>
                                    <i class="dropdown icon"></i>
                                    <div v-for="(tag, index) in blog.tags" :key="index" class="ui label" :data-value="tag.id">
                                        {{tag.name}}
                                        <i class="delete icon"></i>
                                    </div>
                                    <div class="default text">选择标签</div>
                                    <div class="menu">
                                        <div class="item" v-for="(tag, index) in tags" :key="index" v-text="tag.name" :data-value="tag.id" @click="changeTag(tag.id)"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                            <input type="checkbox" id="recomment" name="recomment" class="hidden" v-model="blog.recomment">
                            <label for="recomment">推荐</label>
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
                            <input type="checkbox" id="commentabled" name="commentabled" class="hidden" v-model="blog.commentabled">
                            <label for="commentabled">评论</label>
                        </div>
                        </div>
                    </div>

                    <div class="ui mini negative message" :class="{hidden : hiddens}" v-text="errorMessage">错误提示</div>
                </form>
                <div class="ui right aligned container">
                    <button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button>
                    <button class="ui secondary button" type="button" @click="publish(false)">保存</button>
                    <button class="ui teal button" type="button" @click="publish(true)">发布</button>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {addBlog, updateBlog, queryById} from '../../api/blog';
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
                views          : 0,
                appreciation   : false,
                shareStatement : false,
                commentabled   : false,
                published      : false,
                recomment      : false,
                createTime     : undefined,
                updateTime     : undefined,
                tagIds         : undefined,
                tags           : null,
                description    : undefined,
                typeId         : undefined,
                type           : undefined
            },
            types        : {},
            tags         : {},
            hiddens      : true,
            errorMessage : undefined
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init : function() {
            $('.ui.dropdown').dropdown();

            this.queryTypeList();
            this.queryTagList();
            let blogId = this.$route.query.blogId;
            if(blogId != null && blogId != undefined) {
                this.queryBlogById(blogId);
            } else {
                this.blog.content = '请输入文本';
            }
        },

        /** 根据id查询博客信息 */
        queryBlogById : function(id) {
            let ref = this;
            queryById(id).then(response => {
                ref.blog = response.data;
            })
        },
        
        /** 查找分类列表 */
        queryTypeList : function() {
            let ref = this;
            queryTypeList(1, 999).then(response => {
                ref.types = response.data.list;
            })
        },

        /** 查找标签列表 */
        queryTagList : function() {
            let ref = this;
            queryTagList(1, 999).then(response => {
                ref.tags = response.data.list;
            })
        },

        /** 选择博客类型 */
        changeType : function(typeId){
            this.blog.typeId = typeId;

        },

        /** 选择博客标签类型 */
        changeTag : function(tagId) {
        },

        /** 发布文章和保存文章 */
        publish : function(state) {
            this.blog.flag      = $('#flag')[0].textContent;
            this.blog.content   = this.$refs.markDownEditor.getContent();
            this.blog.tagIds    = $('#tagIds').val();
            this.blog.published = state;

            if(!this.checkFrom()) {
                this.hiddens = false;
                return;
            }

            if(this.blog.id == null || this.blog.id == undefined) {
                addBlog(this.blog).then(response => {
                    if(response.code == 200) {
                        window.history.go(-1);
                    }
                });
            } else {
                updateBlog(this.blog).then(response => {
                    if(response.code == 200) {
                        window.history.go(-1);
                    }
                });
            }
        },

        /** 检查表单 */
        checkFrom : function() {
            if(this.blog.title == null || this.blog.title == undefined) {
                this.errorMessage = '博客主题不能为空';

                return false;
            } else if(this.blog.typeId == null || this.blog.typeId == undefined) {
                this.errorMessage = '博客分类不能为空';

                return false;
            } else if(this.blog.tagIds == null || this.blog.tagIds == undefined || this.blog.tagIds == '') {
                this.errorMessage = '博客标签不能为空';

                return false;
            } else if(this.blog.description == null || this.blog.description == undefined) {
                this.errorMessage = '博客描述不能为空';

                return false;
            }

            return true;
        }
    }
}
</script>