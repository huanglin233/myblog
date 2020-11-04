<template>
    <div id="error">
        <HeaderComponent></HeaderComponent>
        
        <br>
        <br>
        <br>	
        <div class="m-container-small m-padded-tb-massive">
            <div class="ui error message m-padded-tb-huge" >
                <div class="ui contianer">
                <h2>错误</h2>
                <p>对不起，服务异常，请联系管理员</p>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
export default {
    name : 'error',
    data() {
        return {

        }
    },
    mounted() {

    },
    methods: {

    }
}
</script>