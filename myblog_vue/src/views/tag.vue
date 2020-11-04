<template>
    <div id="tag">
        <HeaderComponent header="tag"></HeaderComponent>

        <div class="m-container-small m-padded-tb-big">
            <div class="ui container">
                <div class="ui top attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <h3 class="ui teal header">标签</h3>
                        </div>
                        <div class="right aligned column">
                            共<h2 class="ui orange header m-inline-block m-text-thin" th:text=${#arrays.length(tags)}></h2>个
                        </div>
                    </div>
                </div>
                <div class="ui attached segment m-padded-tb-large">
                    <a href="#" th:href="@{/tags/{id}(id=${tag.id})}" target="_blank" class="ui basic left pointing large label m-margin-tb-tiny" th:classappend="${tag.id==activeTagId} ? 'teal'" th:each="tag : ${tags}" th:text="${tag.name}">
                        <div class="detail" th:text="${#arrays.length(tag.blogs)}"></div>
                    </a>
                </div>
                <!--content-->
                <div class="ui top attached teal segment">
                    <div class="ui padded vertical segment m-padded-tb-large m-margin-lr m-mobile-lr-clear" th:each="blog : ${pages.list}">
                        <div class="ui mobile reversed stackable grid">
                            <div class="eleven wide column">
                                <h3 class="ui header">
                                    <a href="#" th:href="@{/blog/{id}(id=${blog.id})}" target="_blank" class="m-black" th:text="${blog.title}"></a>
                                </h3>
                                <p class="m-text" th:text="|${blog.description}......|"></p>
                                <div class="ui grid">
                                    <div class="eleven wide column">
                                        <div class="ui mini horizontal link list">
                                            <div class="item">
                                                <img th:src="@{${blog.user.avatar}}" class="ui avatar image">
                                                <div class="content">
                                                    <a href="#" class="header" th:text="${blog.user.nickname}"></a>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="calendar icon"></i>
                                                <span th:text="${#dates.format(blog.updateTime,'yyyy-MM-dd')}"></span>
                                            </div>
                                            <div class="item">
                                                <i class="eye icon"></i>
                                                <span th:text="${blog.views}"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="right aligned five wide column">
                                        <a href="#" target="_blank" class="ui teal basic label m-padded-tb-tiny m-text-thin" th:text="${blog.type.name}"></a>
                                    </div>
                                </div>
                                <div class="row m-margin-top">
                                    <div class="column">
                                        <a href="#" th:href="@{/tags/{id}(id=${tag.id})}"  class="ui basic teal left pointing label m-padded-mini m-text-thin" th:each="tag : ${blog.tags}" th:text="${tag.name}"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="five wide column">
                                <a href="#" th:href="@{/blog/{id}(id=${blog.id})}" target="_blank">
                                <img th:src="@{${blog.firstPicture}}" class="ui rounded image">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ui bottom attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <a href="#" th:href="@{'/tags/'+${activeTagId}(page=${pages.pageNum}-1)}"  th:unless="${pages.hasPreviousPage}" class="ui mini teal basic button">上一页</a>
                        </div>
                        <div class="right aligned column">
                            <a href="#" th:href="@{'/tags/'+${activeTagId}(page=${pages.pageNum}+1)}"  th:unless="${pages.hasNextPage}" class="ui mini teal basic button">下一页</a>
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
    name : 'tag',
    data() {
        return {

        }
    }
}
</script>