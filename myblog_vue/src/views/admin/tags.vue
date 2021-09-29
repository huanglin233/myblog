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
                <div class="ui success message" v-if="errorMsg">
                    <i class="close icon" @click="closeBox"></i>
                    <div class="header">提示：</div>
                    <p v-text="errotMsgDesc"></p>
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
                        <tr v-for="(item, index) in tags.list" :key="index">
                            <td v-text="index + 1"></td>
                            <td v-text="item.name"></td>
                            <td>
                                <router-link :to="'/admin/editTag?tagId=' + item.id" class="ui mini teal basic button">编辑</router-link>
                                <a class="ui mini red basic button" @click="openTip(item.id, item.name)">删除</a>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="6" >
                                <div class="ui mini pagination secondary menu" v-if="tags.hasNextPage || tags.hasPreviousPage">
                                    <a class="ui mini floated teal basic button item" v-if="tags.hasPreviousPage" @click="queryTagList(tags.pageNum - 1, 5)">上一页</a>
                                    <a class="ui mini floated teal basic button item" v-if="tags.hasNextPage" @click="queryTagList(tags.pageNum + 1, 5)">下一页</a>
                                </div>
                                <router-link to="/admin/editTag" class="ui mini right floated teal basic button">新增</router-link>
                            </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <FooterComponent class="footerComponent"></FooterComponent>
        <tip @isOk="deleteTagById" ref="tip"></tip>
    </div>
</template>
<script>
import {queryTagList, deleteTagById} from '../../api/tag'
import tip from '../../components/Tip.vue'

export default {
    name : 'tags',
    components: {
        tip
    },
    data() {
        return {
            errorMsg     : false,
            errotMsgDesc : '操作错误',
            tags         : []
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init : function() {
            this.queryTagList(1, 5);
        },

        /** 删除提示信息 */
        closeBox : function(e) {
            var messageBox = e.target;
            $(messageBox).closest(".message").transition('fade');
        },

        /** 查询博客标签列表 */
        queryTagList : function(pageNum, pageSize) {
            let ref = this;
            queryTagList(pageNum, pageSize).then(response => {
                ref.tags = response.data;
            })
        },

        /** 打开删除提示框 */
        openTip(id, name) {
            this.$refs.tip.isShow(id, name);
        },

        /** 删除博客标签 */
        deleteTagById : function(id) {
            let ref = this;
            deleteTagById(id).then(response => {
                ref.queryTagList(1, 5);
            })
        }
    }
}
</script>
