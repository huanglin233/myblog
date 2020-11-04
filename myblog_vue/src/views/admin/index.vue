<template>
    <div id="adminIndex">
        <AdminHeaderComponent></AdminHeaderComponent>
        <div class="ui container">
            <div class="m-container-samll m-padded-tb-small">
                <div class="ui success large message">
                    <h3>Hi,</h3>
                    <p>huanglin, 欢迎登录</p>
                </div>
                <img src="http://ppe.oss-cn-shenzhen.aliyuncs.com/collections/182/2/thumb.jpg" class="ui rounded bordered fluid image">
            </div>
        </div> 
        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
export default {
    name : 'adminIndex',
    data() {
        return {

        }
    }
}
</script>