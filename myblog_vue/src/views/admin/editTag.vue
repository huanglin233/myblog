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
                <form class="ui form">
                    <div class="field">
                        <div class="ui left labeled input">
                            <label class="ui teal basic label">名称</label>
                            <input type="text" name="name" placeholder="标签名称" v-model="tag.name">
                        </div>
                    </div>

                    <div class="ui negative message" v-if="errorMsg">
                        <i class="close icon" @click="closeBox"></i>
                        <div class="header">验证失败</div>
                        <p v-text="errorMsgDesc">提交信息不符合规则</p>
                    </div>
                    
                </form>
                <div class="ui right aligend container">
                    <button type="button" class="ui button" onclick="window.history.go(-1)">返回</button>
                    <button class="ui teal submit button" @click="submit">提交</button>
                </div>
            </div>
        </div>

        <FooterComponent class="footerComponent"></FooterComponent>
    </div>
</template>
<script>
import {queryTagById, addTag, updateTag} from '../../api/tag'

export default {
    name : 'editTag',
    data() {
        return {
            errorMsg     : false,
            errorMsgDesc : '提交信息不符合规则',
            tag          : {
                id   : undefined,
                name : undefined
            }
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

            let tagId = this.$route.query.tagId;
            if(tagId != null && tagId != undefined) {
                this.queryTagById(tagId);
            }
        },

        closeBox : function(e) {
            var messageBox = e.target;
            $(messageBox).closest(".message").transition('fade');
        },

        queryTagById : function(id) {
            let ref = this;
            queryTagById(id).then(response => {
                ref.tag = response.data;
            })
        },

        addTag : function() {
            let ref = this;
            addTag(this.tag).then(response => {
                if(response.code != 200) {
                    ref.errorMsg     = true;
                    ref.errorMsgDesc = response.msg;
                } else {
                    window.history.go(-1);
                }
            })
        },

        updateTag : function() {
            let ref = this;
            updateTag(this.tag).then(response => {
                if(response.code != 200) {
                    ref.errorMsg     = true;
                    ref.errorMsgDesc = response.msg;
                } else {
                    window.history.go(-1);
                }
            })
        },

        submit : function() {
            if(this.tag.name == null || this.tag.name == undefined) {
                this.errorMsg     = true;
                this.errorMsgDesc = '标签名称不能为空';

                return;
            }

            if(this.tag.id != null && this.tag.id != undefined) {
                this.updateTag();
            } else {
                this.addTag();
            }
        }
    }
}
</script>