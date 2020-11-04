<template>
    <div id="editTag">
        <AdminHeaderComponent AdminHeader="tags"></AdminHeaderComponent>
        <!-- 博客列表 -->
        <div th:fragment="list" class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editTag" class="teal active item">新增</router-link>
                    <router-link to="/admin/tags"  class="teal item">列表</router-link>
                </div>
            </div>
        </div>
        
        <!--中间内容-->
        <div class="m-container-small m-padded-tb-big">
            <div class="ui contrainer">
                <form class="ui form" th:object="${tag}" method="post" action="#" th:action="*{id} eq null ? @{/admin/tags/add} : @{'/admin/tags/edit/' + *{id}}">
                    <input type="hidden" name="id" th:value="*{id}">
                    <div class="field">
                        <div class="ui left labeled input">
                            <label class="ui teal basic label">名称</label>
                            <input type="text" name="name" placeholder="标签名称" th:value="*{name}">
                        </div>
                    </div>

                    <div class="ui error message"></div>
                    <div class="ui negative message" th:if="${#fields.hasErrors('name')}">
                        <i class="close icon" @click="closeBox"></i>
                        <div class="header">验证失败</div>
                        <p th:errors="*{name}">提交信息不符合规则</p>
                    </div>
                    
                    <div class="ui right aligend container">
                        <button type="button" class="ui button" onclick="window.history.go(-1)">返回</button>
                        <button class="ui teal submit button">提交</button>
                    </div>
                </form>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
export default {
    name : 'editTag',
    data() {
        return {

        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init : function() {
            $(".ui.dropdown").dropdown({
                on : "hover"
            });
            $(".ui.form").form({
                fields : {
                    title : {
                        identifier : "name",
                        rules : [{
                            type   : "empty",
                            prompt : "请输入标签名称"
                        }]   
                    }
                }
            });
        },
        closeBox : function(e) {
            var messageBox = e.target;
            $(messageBox).closest(".message").transition('fade');
        }
    }
}
</script>