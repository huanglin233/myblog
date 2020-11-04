<template>
    <div id="about">
        <HeaderComponent header="about"></HeaderComponent>
        <div class="m-container m-padded-tb-big">
            <div class="ui container">
                <div class="ui stackable grid">
                    <div class="eleven wide column">
                        <div class="ui segment">
                            <img src="https://unsplash.it/800/600?image=1005" class="ui rounded image">
                        </div>
                    </div>
                    <div class="five wide column">
                        <div class="ui top attached segment">
                            <div class="ui header"> 关于我</div>
                        </div>
                        <div class="ui attached segment">
                            <p class="m-text">HuangLin</p>
                            <p class="m-text">热爱编程、喜欢学习新技术...</p>
                        </div>
                        <div class="ui attached segment">
                            <div class="ui orange basic left pointing label">编程</div>
                            <div class="ui orange basic left pointing label">小说</div>
                            <div class="ui orange basic left pointing label">游戏</div>
                            <div class="ui orange basic left pointing label">电影</div>
                        </div>
                        <div class="ui attached segment">
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Java</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">JavaScript</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Scala</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Spring全家桶</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Play-framework</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Mysql</div>
                            <div class="ui teal basic left pointing label m-margin-tb-tiny">Vue</div>
                        </div>
                        <div class="ui bottom attached segment">
                            <a href="#" class="ui circular icon button"><i class="github icon"></i></a>
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
    }
}
</script>