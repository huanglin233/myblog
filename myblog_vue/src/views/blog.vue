<template>
    <div id="blog">
        <HeaderComponent></HeaderComponent>
        <div v-if="hasBlog">
            <div id="waypoint" class="m-container m-padded-tb-big animated fadeIn">
            <div class="ui container">
                    <div class="ui top attached segment">
                        <div class="ui horizontal link list">
                            <div class="item">
                                <img :src="blog.user != null ? blog.user.avatar : '#'" class="ui avatar image">
                                <div class="content"><a href="#" class="header">{{blog.user != null ? blog.user.nickname : ''}}</a></div>
                            </div>
                            <div class="item">
                                <i class="calendar icon"></i>
                                <span>{{blog.updateTime | formatDate}}</span>
                            </div>
                            <div class="item">
                                <i class="eye icon"></i>
                                <span>{{blog.views}}</span>
                            </div>
                            <div class="m-padded-lr-responsive "></div>
                        </div>
                    </div>
                    <div class="ui attached segment">
                        <!--图片区域-->
                        <img :src="blog.firstPicture" class="ui fluid rounded image">
                    </div>
                    <Content :data="blog" :contentHtml="blog.content" v-if="blog.id != null && blog.content != null"></Content>
                    <div class="ui attached positive message">
                        <!--博客信息-->
                        <div class="ui middle aligned grid">
                            <div class="eleven wide column">
                            <ul class="list">
                                <li>作者: <span></span><a target="_blank">{{blog.user != null ? blog.user.nickname : ''}}</a></li>
                                <li>发表时间: <span>{{blog.createTime | formatDate}}</span></li>
                                <li>版权声明：自由转载-非商用-非衍生-保持署名（创意共享3.0许可证）</li>
                                <!-- <li>公众号转载：请在文末添加作者公众号二维码</li> -->
                            </ul>
                            </div>
                            <div class="five wide column">
                                <img src="../../static/images/wechat.jpg" th:src="@{/images/wechat.jpg}" class="ui right floated rounded bordered image" style="width: 100px">
                            </div>
                        </div>
                    </div>
                    <!--留言区域列表-->
                    <div id="comment-container" class="ui bootom attached segment">
                        <div>                   
                            <div class="ui teal segment">
                                <div class="ui threaded comments">
                                    <h3 class="ui dividing header">评论</h3>
                                    <Comment :data="comments" v-if="comments != null" @reply="replyTo"></Comment>
                                </div>
                            </div>
                            <div class="ui form" v-if="blog != null && blog.recomment">
                                <div class="field">
                                    <textarea name="content" v-model="newComment.content" :placeholder="placeholder"></textarea>
                                </div>
                                <div class="fields">
                                    <div class="field m-mobile-wide m-margin-bottom-small">
                                        <div class="ui left icon input">
                                        <i class="user icon"></i>
                                        <input type="text" name="nickname" placeholder="姓名" v-model="newComment.nickname">
                                        </div>
                                    </div>
                                    <div class="field m-mobile-wide m-margin-bottom-small">
                                        <div class="ui left icon input">
                                        <i class="mail icon"></i>
                                        <input type="text" name="email" placeholder="邮箱" v-model="newComment.email">
                                        </div>
                                    </div>
                                    <div class="field m-margin-bottom-small m-mobile-wide some-wrapping-div">
                                        <div id="commentpost-btn" class="ui teal custom button m-mobile-wide" @click="reply()"><i class="edit icon"></i>发布</div>
                                    </div>
                                    <div class="ui custom popup top left transition hidden">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        <div v-else class="m-text-center">
            <h1>没有查找响应博客!</h1>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryByIdAndConvert} from '../api/blog';
import {queryRecommentByBlogId, addRecomment} from '../api/comment';
import QRCode from 'qrcodejs2';
import Comment from '../components/Comment';
import Content from '../components/Content';

export default {
    name : 'blog',
    components : {
        "Content" : Content,
        "Comment" : Comment
    },
    data() {
        return {
            blog       : {
                id             : null,
                title          : undefined,
                content        : null,
                firstPicture   : undefined,
                flag           : undefined,
                views          : 0,
                appreciation   : true,
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
            comments   : [],
            newComment : {
                id              : undefined,
                blogId          : undefined,
                parentCommentId : undefined,
                nickname        : undefined,
                email           : undefined,
                content         : undefined,
                avatar          : undefined,
                createTime      : undefined,
                adminComment    : false
            },
            hasBlog     : true,
            placeholder : '请输入评论信息...',
            blogId      : undefined
        }
    },
    created() {
        this.blogId = this.$route.query.blogId;
        if(this.blogId != undefined) {
            this.queryById(this.blogId);
            this.queryRecommentByBlogId(this.blogId);
        } else {
            this.hasBlog = false;
        }
    },
    mounted() {
    
    },
    methods : {
        init : function(blogId) {
            this.initTocbot();
            this.initQrcode(blogId);
            this.initButton();
        },
        initButton : function() {
            $('#payButton').popup({
                popup : $('.payQR.popup'),
                on : 'click',
                position: 'bottom center'
            });

            $('.toc.button').popup({
                popup : $('.toc-container.popup'),
                on:'click',
                position: 'left center'
            });

            $('.wechat.button').popup({
                popup : $('.wechat-qr.popup'),
                on : 'click',
                    
            });
        },  

        initTocbot : function() {
            tocbot.init({
                // Where to render the table of contents.
                tocSelector: '.js-toc',
                // Where to grab the headings to build the table of contents.
                contentSelector: '.js-toc-content',
                // Which headings to grab inside of the contentSelector element.
                headingSelector: 'h1, h2, h3'
            });
        },

        initQrcode : function(blogId) {
            var qrcode = new QRCode("qrcode", {
                text: "http://blog.huanglin.online/blog?blogId=" + blogId,
                width: 110,
                height: 110,
                colorDark : "#000000",
                colorLight : "#ffffff",
                correctLevel : QRCode.CorrectLevel.H
            });
        },

        toTop : function() {
            $(window).scrollTo(0, 500);
        },

       queryById :function (blogId) {
           let ref = this;
           queryByIdAndConvert(blogId).then(response => {
               ref.blog = response.data;;
           });
       },

       queryRecommentByBlogId : function(blogId){
           let ref = this;
           queryRecommentByBlogId(blogId).then(response => {
               ref.comments = response.data;
           })
       },

       addRecomment : function() {
           let ref = this;
           addRecomment(this.newComment).then(response =>{
               if(response.code == 200) {
                   ref.queryRecommentByBlogId(ref.blog.id);
                   ref.newComment.email    = undefined;
                   ref.newComment.nickname = undefined;
                   ref.newComment.content  = undefined;
                   ref.placeholder         = '请输入评论信息...';
               }
           })
       },

       replyTo : function(recommentId, nickname) {
           this.newComment.parentCommentId = recommentId;
           this.placeholder = '@' + nickname
       },

       reply : function() {
           this.newComment.blogId = this.blog.id;
           let alertMsg = undefined;
           if(this.newComment.content == null || this.newComment.content == '') {
               alertMsg = '评论内容不能为空';
           } else if(this.newComment.nickname == null || this.newComment.nickname == '') {
               alertMsg = '请填写好你的姓名';
           } else if(this.newComment.email == null || this.newComment.email == '') {
               alertMsg = '请填写好你的邮箱地址';
           } else {
               let myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
               if(!myreg.test(this.newComment.email)) {
                   alertMsg = '请输入正确的邮箱地址';
               }
           }

           if(alertMsg != undefined) {
               this.recommentCheck(alertMsg);

               return;
           }
           $('.custom.button').popup('destroy'); 
           this.addRecomment();
       },

       recommentCheck : function(data) {
           $('.custom.button').popup({
               title   : '',
               content : data,
               on      : 'click'
           }).popup('show'); 
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