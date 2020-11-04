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
                            共<h2 class="ui orange header m-inline-block m-text-thin"></h2>个
                        </div>
                    </div>
                </div>
                <div class="ui attached segment m-padded-tb-large">
                    <div class="ui labeled button m-margin-tb-tiny">
                        <a href="#" class="ui basic button"></a>
                        <div class="ui basic left pointing label"></div>
                    </div>
                </div>
                <div class="ui top attached teal segment">
                    <div class="ui padded vertical segment m-padded-tb-large m-margin-lr m-mobile-lr-clear">
                        <div class="ui padded vertical mobile reversed stackable grid">
                            <div class="eleven wide column">
                                <h3 class="ui header">
                                    <a href="#" target="_blank" class="m-black"></a>
                                </h3>
                                <p class="m-text" ></p>
                                <div class="ui grid">
                                    <div class="eleven wide column">
                                        <div class="ui mini horizontal link list">
                                            <div class="item">
                                                <img class="ui avatar image">
                                                <div class="content">
                                                    <a href="#" class="header">黄林</a>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="calendar icon"></i> 
                                                <span></span>
                                            </div>
                                            <div class="item">
                                                <i class="eye icon"></i>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="right aligned five wide column">
                                        <a href="#" target="_blank" class="ui teal basic label m-padded-tb-tiny m-text-thin"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="five wide column">
                                <a href="#" target="_blank">
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
                            <a href="#" class="ui mini teal basic button">上一页</a>
                        </div>
                        <div class="right aligned column">
                            <a href="#" class="ui mini teal basic button">下一页</a>
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
    name : 'type',
    data() {
        return {

        }
    }
}
</script>