<template>
    <div id="editType">
        <AdminHeaderComponent AdminHeader="types"></AdminHeaderComponent>
            <!-- 博客列表 -->
        <div th:fragment="list" class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editType" class="teal active item">新增</router-link>
                    <router-link to="/admin/types" class="teal item">列表</router-link>
                </div>
            </div>
        </div>

        <!--中间内容-->
        <div class="m-container-small m-padded-tb-big">
            <div class="ui contrainer">
                <form class="ui form">
                    <div class="field">
                        <div class="ui left labeled input">
                            <label class="ui teal basic label">名称</label>
                            <input type="text" name="name" placeholder="分类名称" v-model="type.name">
                        </div>
                    </div>

                    <div class="ui negative message" v-if="errorMsg">
                        <i class="close icon" @click="close"></i>
                        <div class="header">消息提醒</div>
                        <p v-text="errorMsgDesc">提交信息不符合规则</p>
                    </div>
                    
                </form>
                <div class="ui right aligend container m-padded-tb">
                    <button type="button" class="ui button" onclick="window.history.go(-1)">返回</button>
                    <button class="ui teal button" @click="submit">提交</button>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryTypeById, addType, updateType} from '../../api/type'

export default {
    name : 'editType',
    data() {
        return {
            errorMsg     : false,
            errorMsgDesc : '提交信息不符合规则',
            type         : {
                id   : undefined,
                name : undefined
            }
        }
    },
    mounted() {
        this.init();
    },
    methods : {
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
                            prompt : "请输入分类名称"
                        }]   
                    }
                }
            });

            let typeId = this.$route.query.typeId;
            if(typeId != null && typeId != undefined) {
                this.queryTypeById(typeId);
            }
        },

        close : function(e) {
            let messageBox = e.target;
            $(messageBox).closest(".message").transition("fade");
        },

        /** 根据分类id查询分类信息 */
        queryTypeById :function(id) {
            let ref = this;
            queryTypeById(id).then(response => {
                this.type = response.data;
            })
        },

        submit : function() {
            let ref = this;
            if(this.type.name == null && this.type.name == undefined) {
                this.errorMsg     = true;
                this.errorMsgDesc = '分类名称不能为空'

                return;
            }

            if(this.type.id != null && this.type.id != undefined) {
                updateType(this.type).then(response => {
                    if(response.code == 200) {
                        window.history.go(-1);
                    } else {
                        ref.errorMsg     = true;
                        ref.errorMsgDesc = response.msg;
                    }
                })
            } else {
                addType(this.type).then(response => {
                    if(response.code == 200) {
                        window.history.go(-1);
                    } else {
                        ref.errorMsg     = true;
                        ref.errorMsgDesc = response.msg;
                    }
                });
            }
        }
    }
}
</script>