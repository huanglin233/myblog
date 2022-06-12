<template>
    <div id="about">
        <HeaderComponent header="about"></HeaderComponent>
        <div class="m-container m-padded-tb-big">
            <div class="ui container">
                <div class="ui stackable grid">
                    <div class="ten wide column">
                        <div class="ui segment">
                            <img src="https://tse1-mm.cn.bing.net/th/id/R-C.5b71d169090b27f2e9bf4f8938628f30?rik=6rR0p79DEzqmig&riu=http%3a%2f%2fup.deskbizhi.com%2fpic%2fea%2fee%2fe6%2feaeee6addaa952462a5f854645082cd4.jpg&ehk=VcdWmczYNe0qvt54bxotdD4%2fDpbXWbn3oOu8ZUD60hA%3d&risl=&pid=ImgRaw&r=0" class="ui rounded image">
                        </div>
                    </div>
                    <div class="six wide column">
                        <div class="ui top attached segment">
                            <div class="ui header"> 关于我</div>
                        </div>
                        <div class="ui attached segment">
                            <p class="m-text">黄林</p>
                            <p class="m-text">95后、四川内江人、现坐标成都</p>
                            <p class="m-text">燕山大学里仁学院、计算机科学与技术专业</p>
                            <p class="m-text">热爱编程、喜欢学习新技术...</p>
                        </div>
                        <div class="ui attached segment">
                            <div class="ui orange basic left pointing label">编程</div>
                            <div class="ui orange basic left pointing label">电影</div>
                            <div class="ui orange basic left pointing label">历史</div>
                            <div class="ui orange basic left pointing label">游戏</div>
                        </div>
                        <div class="ui attached segment">
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Java</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">JavaScript</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">C</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Scala</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Spring全家桶</div>                            
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Mybatis</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Dubbo</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">常用中间件(kafka\RabbitMQ\Redis...)</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">常用web容器(Tomcat\Nginx\Apache)</div> 
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Play-framework</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Mysql</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Vue</div>
                        </div>
                        <div class="ui bottom attached segment">
                            <a href="https://github.com/huanglin233" target="_blank" class="ui circular icon button"><i class="github icon"></i></a>
                            <a href="https://gitee.com/huanglin_only" target="_blank" class="ui git circular icon button"><i class="git icon"></i></a>
                            <a href="#" class="ui wechat circular icon button"><i class="weixin icon"></i></a>
                            <a href="#" class="ui qq circular icon button" data-content="2512467648" data-position="bottom center"><i class="qq icon"></i></a>
                        </div>
                        <div class="ui wechat-qr flowing popup transition hidden">
                            <img src="../../static/images/wechat.jpg" th:src="@{/images/wechat.jpg}" class="ui rounded image" style="width: 110px">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
export default {
    name : 'about',
    data() {
        return {

        }
    },
    mounted() {
        this.init();
    },
    methods : {
        init : function() {
            $('.menu.toggle').click(function () {
                $('.m-item').toggleClass('m-mobile-hide');
            });

            $('.wechat').popup({
                popup : $('.wechat-qr'),
                position: 'bottom center'
            });

            $('.qq').popup();
        }
    }
}
</script>
