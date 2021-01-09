<template>
    <div id="Content">
        <div class="ui attached segment">
            <!--内容-->
            <div class="ui right aligned basic segment">
                <div class="ui orange basic label">{{blog.flag}}</div>
            </div>
            <h2 class="ui center aligned header">{{blog.title}}</h2>
            <br>
            <div id="content" v-if="content != null" v-html="content" class="typo typo-selection js-toc-content m-padded-lr-responsive m-padded-tb-large"></div>
            <!--标签-->
            <div class="m-padded-lr-responsive">
                <div class="ui basic teal left pointing label" v-for="(item, index) in blog.tags" :key="index" v-text="item.name"></div>
            </div>
            <!--赞赏-->
            <div class="ui center aligned basic segment" v-if="blog.appreciation">
                <button id="payButton" class="ui orange basic circular button">赞赏</button>
            </div>
            <div class="ui payQR flowing popup transition hidden">
                <div class="ui orange basic label">
                <div class="ui images" style="font-size: inherit !important;">
                    <div class="image">
                    <img src="../../static/images/wechat.jpg" class="ui rounded bordered image" style="width: 120px">
                    <div>支付宝</div>
                    </div>
                    <div class="image">
                    <img src="../../static/images/wechat.jpg" class="ui rounded bordered image" style="width: 120px">
                    <div>微信</div>
                    </div>
                </div>
                </div>
            </div>
        </div>
        <div id="toolbar" class="m-padded m-fixed m-right-bottom">
            <div class="ui vertical icon buttons">
                <button type="button" class="ui toc teal button">目录</button>
                <a href="#comment-container" class="ui teal button">留言</a>
                <button class="ui wechat icon button"><i class="weixin icon"></i></button>
                <div class="ui icon button" @click="toTop"><i class="chevron up icon"></i></div>
            </div>
        </div>
        <div class="ui toc-container flowing popup transition hidden" style="width: 150px !important">
            <ol class="js-toc">
            </ol>
        </div>
        <div id="qrcode" class="ui wechat-qr flowing popup transition hidden" style="width: 130px !important;">
            <!-- <img src="../static/images/wechat.jpg" class="ui rounded image" style="width: 120px !important;"> -->
        </div>
    </div>
</template>
<script>
import QRCode from 'qrcodejs2';

export default {
    name : 'Content',
    props : ['data', 'contentHtml'],
    data() {
        return {
            blog : {},
            content : null
        }
    },
    created() {
        this.blog = this.data;
        this.content = this.contentHtml;
    },
    mounted() {
        if(this.content != null) {
            this.init(this.blog.id);
        }
    },
    methods: {
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
    },
}
</script>