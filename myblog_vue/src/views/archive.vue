<template>
    <div id="archive">
        <HeaderComponent header="archive"></HeaderComponent>

        <div class="m-container m-padded-tb-big">
            <div class="ui container">
                <!--header-->
                <div class="ui top attached padded segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <h3 class="ui teal header">归档</h3>
                        </div>
                        <div class="right aligned column">
                            共<h2 class="ui orange header m-inline-block m-text-thin" th:text="${blogCount}"></h2>篇
                        </div>
                    </div>
                </div>
                <!--content-->
                <div >

                </div>
                <div th:each="item : ${archiveMap}">
                    <h3 class="ui center aligned header m-padded" th:text="${item.key}"></h3>
                    <div class="ui fluid vertical menu">
                        <a href="#" th:href="@{/blog/{id}(id=${blog.id})}" target="_blank" class="item" th:each="blog : ${item.value}">
                            <span>
                                <i class="mini teal circle icon"></i>
                                <span th:text="${blog.title}"></span>
                                <i class="ui teal basic left pointing label m-padded-mini" th:text="${#dates.format(blog.updateTime,'MMdd')}"></i>
                            </span>
                            <div class="ui orange basic left pointing label m-padded-mini" th:text="${blog.flag}"></div>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
export default {
    name : 'archive',
    data() {
        return {

        }
    }
}
</script>