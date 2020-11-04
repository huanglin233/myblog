<template>
    <div id="tags">
        <AdminHeaderComponent AdminHeader="tags"></AdminHeaderComponent>
        <!-- 博客列表 -->
        <div th:fragment="list" class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editTag" class="teal item">新增</router-link>
                    <router-link to="/admin/tags"  class="teal active item">列表</router-link>
                </div>
            </div>
        </div>   
        <!--中间内容-->
        <div class="m-container-small m-padded-tb-big">
            <div class="ui container">
                <div class="ui success message" th:unless="${#strings.isEmpty(message)}">
                    <i class="close icon" @click="closeBox"></i>
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
                    <tbody>
                        <tr th:each="tag, startIndex : ${page.list}">
                            <td th:text="${startIndex.count}"></td>
                            <td th:text="${tag.name}"></td>
                            <td>
                                <a href="#" th:href="@{'/admin/tags/edit/' + ${tag.id} }" class="ui mini teal basic button">编辑</a>
                                <a href="#" th:href="@{'/admin/delete/tag/' + ${tag.id}}" class="ui mini red basic button">删除</a>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="6">
                                <div clas="ui mini pagination menu" th:if="${page.pages} > 1">
                                    <a href="#" th:href="@{/admin/tags(page=${page.pageNum} - 1)}" class="item" th:unless="${page.hasPreviousPage}">上一页</a>
                                    <a href="#" th:href="@{/admin/tags(page=${page.pageNum} + 1)}" class="item" th:unless="${page.hasNextPage}">下一页</a>
                                </div>
                                    <a href="#" th:href="@{/admin/tags/add}" class="ui mini right floated teal basic button">新增</a>
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
    name : 'tags',
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
        closeBox : function(e) {
            var messageBox = e.target;
            $(messageBox).closest(".message").transition('fade');
        }
    }
}
</script>