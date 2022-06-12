<template>
  <div id="notes">
    <AdminHeaderComponent AdminHeader="notes"></AdminHeaderComponent>
    <div id="body">
        <div class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editNotes" class="item">新增</router-link>
                    <router-link to="/admin/notes" class="teal active item">列表</router-link>
                </div>
            </div>
        </div>
        <div class="m-container-small m-padded-tb-big">
            <div class="ui container">
                <div class="ui icon input">
                    <input type="text" v-model="params.keyWords" placeholder="笔记名称"/>
                    <i class="inverted circular search link icon" @click="seach"></i>
                </div>
                <div id="table-container">
                    <table class="ui compact teal celled table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>笔记名称</th>
                                <th>地址</th>
                                <th>排序</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in notes.list" :key="index">
                                <td v-text="index + 1"></td>
                                <td v-text="item.name"></td>
                                <td v-text="item.path"></td>
                                <td v-text="item.orderId"></td>
                                <td>
                                    <router-link :to="'/admin/editNotes?notesId=' + item.id" class="ui mini teal basic button">编辑</router-link>
                                    <a class="ui mini red basic button" @click="openTip(item.id, item.name)">删除</a>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="7">
                                    <div class="ui mini pagination secondary menu">
                                        <a @click="toPage(notes.pageNum - 1, 5)" v-if="notes.hasPreviousPage" class="ui mini floated teal basic button item">上一页</a>
                                        <a @click="toPage(notes.pageNum + 1, 5)" v-if="notes.hasNextPage" class="ui mini floadted teal basic button">下一页</a>
                                    </div>
                                </th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <FooterComponent class="footerComponent"></FooterComponent>
    <tip @isOk="deleteNotes" ref="tip"></tip>
  </div>
</template>

<script>
import {queryList, add, update, del} from '../../api/notes'
import tip from '../../components/Tip.vue'
export default {
    name : "notes",
    components : {
        tip
    },
    data() {
        return {
            notes : [],
            params : {
                pageSize : 20,
                pageNum : 1,
                keyWords : null
            },
        }
    },
    mounted() {
        this.init();
    },
    methods : {
        /**
         * 初始笔记列表
         */
        init() {
            const that = this;
            queryList(this.params).then(res => {
                console.log(res);
                that.notes = res.data;
            })
        },
        seach() {
            this.init();
        },
        toPage(pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
        },
        deleteNotes(id) {
            const ref = this;
            del(id).then(res => {
                ref.init();
            }).catch(error => {
                console.log(error);
            })
        },
         /**
         *  打开提示框
         */
        openTip(id, title) {
            this.$refs.tip.isShow(id, title)
        },
    }
}
</script>

<style>

</style>