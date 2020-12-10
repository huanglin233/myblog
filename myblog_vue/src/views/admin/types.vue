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
                <div class="ui success message" v-if="errorMsg">
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
                        <tr v-for="(item, index) in types.list" :key="index">
                            <td v-text="index + 1"></td>
                            <td v-text="item.name"></td>
                            <td>
                                <router-link :to="'/admin/editType?typeId=' + item.id" class="ui mini teal basic button">编辑</router-link>
                                <a class="ui mini red basic button" @click="deleteTypeById(item.id)">删除</a>
                            </td>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="6" >
                                <div class="ui mini pagination secondary menu" v-if="types.hasNextPage || types.hasPreviousPage">
                                    <a class="ui mini floated teal basic button item" v-if="types.hasPreviousPage" @click="queryTypeList(types.pageNum - 1, 5)">上一页</a>
                                    <a class="ui mini floated teal basic button item" v-if="types.hasNextPage" @click="queryTypeList(types.pageNum + 1, 5)">下一页</a>
                                </div>
                                <router-link to="/admin/editType" class="ui mini right floated teal basic button">新增</router-link>
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
import {queryTypeList, deleteTypeById} from '../../api/type'

export default {
    name : 'types',
    data() {
        return {
            errorMsg : false,
            types    : {}
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init : function() {
            this.queryTypeList(1, 5);
        },

        close : function(e) {
            let messageBox = e.target;
            $(messageBox).closest(".message").transition("fade");
        },

        /** 查询博客分类列表 */
        queryTypeList : function(pageNum, pageSize) {
            let ref = this;
            queryTypeList(pageNum, pageSize).then(response => {
                ref.types = response.data
            })
        },

        /** 根据分类id删除分类信息 */
        deleteTypeById : function(id) {
            let ref = this;
            deleteTypeById(id).then(response => {
                ref.queryTypeList(1, 5);
            })
        }
    }
}
</script>