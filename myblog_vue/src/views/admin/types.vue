<template>
    <div id="types">
        <AdminHeaderComponent AdminHeader="types"></AdminHeaderComponent>
        
        <div class="ui attached pointing menu">
            <div class="ui container">
            <div class="right menu">
                <router-link to="/admin/editType" class="item">新增</router-link>
                <router-link to="/admin/types" class="teal active item">列表</router-link>
            </div>
            </div>
        </div>       
        <!-- 中间内容 -->
        <div class="m-container-small m-padded-tb-big">
            <div class="ui container">
                <div class="ui success message" th:unless="${#strings.isEmpty(message)}">
                    <i class="close icon" @click="close"></i>
                    <div class="header">提示：</div>
                    <p th:text="${message}"></p>
                </div>
                <table class="ui compact teal celled table stackable">
                    <thead>
                        <tr>
                            <th></th>
                            <th>名称</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <thead>
                        <tr th:each="type, typeIndex : ${page.list}">
                            <td th:text="${typeIndex.count}"></td>
                            <td th:text="${type.name}"></td>
                            <td>
                                <a href="#" th:href="@{'/admin/types/edit/' + ${type.id}}" class="ui mini teal basic button">编辑</a>
                                <a href="#" th:href="@{'/admin/delete/type/' + ${type.id}}" class="ui mini red basic button">删除</a>
                            </td>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="6" >
                            <div class="ui mini pagination menu" th:if="${page.pages}>1">
                                <a th:href="@{/admin/types(page=${page.pageNum} - 1)}" class="item" th:unless="${page.hasPreviousPage}">上一页</a>
                                <a th:href="@{/admin/types(page=${page.pageNum} + 1)}" class="item" th:unless="${page.hasNextPage}">下一页</a>
                            </div>
                            <a href="#" th:href="@{/admin/types/add}"  class="ui mini right floated teal basic button">新增</a>
                            </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
            
        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
export default {
    name : 'types',
    data() {
        return {
            
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init : function() {

        },
        close : function(e) {
            let messageBox = e.target;
            $(messageBox).closest(".message").transition("fade");
        }

    }
}
</script>