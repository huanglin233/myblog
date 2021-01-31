<template>
    <div id="error">
        <HeaderComponent></HeaderComponent>
        
        <br>
        <br>
        <br>	
        <div class="m-container-small m-padded-tb-massive">
            <div class="ui error message m-padded-tb-huge" >
                <div class="ui contianer">
                <h2>系统繁忙</h2>
                <p>{{msg}}</p>
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
            msg : undefined
        }
    },
    created() {
        this.msg = this.$route.query.msg;
    },
    methods: {

    }
}
</script>