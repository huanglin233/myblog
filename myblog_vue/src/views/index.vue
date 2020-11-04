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
                                    共<h2 class="ui orange header m-inline-block m-text-thin"></h2>篇
                                </div>
                            </div>
                        </div>
                        <!--content-->
                        <div class="ui attached segment">
                            <div class="ui padded vertical segment m-padded-tb-large m-margin-lr m-mobile-lr-clear">
                                <div class="ui mobile reversed stackable grid">
                                    <div class="eleven wide column">
                                        <h3 class="ui header">
                                            <a href="#" target="_blank" class="m-black"></a>
                                        </h3>
                                        <p class="m-text"></p>
                                        <div class="ui grid">
                                            <div class="eleven wide column">
                                                <div class="ui mini horizontal link list">
                                                    <div class="item">
                                                        <img class="ui avatar image">
                                                        <div class="content"><a href="#" class="header"></a></div>
                                                    </div>
                                                    <div class="item">
                                                        <i class="calendar icon"></i><span></span>
                                                    </div>
                                                    <div class="item">
                                                        <i class="eye icon"></i><span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="right aligned five wide column">
                                                <a href="#" target="_blank" class="ui teal basic label m-padded-tb-tiny m-text-thin"></a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="five wide column">
                                        <a href="#"  target="_blank">
                                        <img class="ui rounded image">
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
export default {
    name : 'index',
    data() {
        return {

        }
    },
    mounted() {
        
    },
    methods: {
        
    },
}
</script>