<template>
  <div id="editNotes">
    <AdminHeaderComponent AdminHeader="notes"></AdminHeaderComponent>
    <div id="body">
        <div class="ui attached pointing menu">
            <div class="ui container">
                <div class="right menu">
                    <router-link to="/admin/editNotes" class="teal active item">编辑</router-link>
                    <router-link to="/admin/notes" class="item">列表</router-link>
                </div>
            </div>
        </div>
        <div>
            <div class="m-container-small m-padded-tb-big">
                <div class="ui contrainer">
                    <form class="ui form">
                        <div class="field">
                            <div class="ui left labeled input">
                                <label class="ui teal basic label">名称</label>
                                <input type="text" name="name" placeholder="笔记名称" v-model="notes.name" />
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left labeled input">
                                <label class="ui teal basic label">路径</label>
                                <input type="text" name="path" placeholder="笔记名称" v-model="notes.path" />
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left labeled input">
                                <label class="ui teal basic label">排序</label>
                                <input type="text" name="path" placeholder="笔记排序" v-model="notes.orderId" />
                            </div>
                        </div>
                        <div class="ui negative message" v-if="errorMsg">
                            <i class="colse icon" @click="closeBox"></i>
                            <div class="header">验证失败</div>
                            <p v-text="errorMsgDesc">提交的信息不符合要求</p>
                        </div>
                    </form>
                    <div class="ui right aligend container m-padded-tb">
                        <button type="button" class="ui button" onclick="window.history.go(-1)">返回</button>
                        <button class="ui teal submit button" @click="submit">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <FooterComponent class="footerComponent"></FooterComponent>
  </div>
</template>

<script>
import {queryById , add, update} from '../../api/notes'
export default {
    name : "editNotes",
    data() {
        return {
            errorMsg     : false,
            errorMsgDesc : '提交的信息不符合要求',
            notes        : {
                id      : null,
                name    : null,
                path    : null,
                orderId : null
            }
        }
    },
    mounted() {
        this.init();
    },
    methods : {
        init() {
            $(".ui.dropdown").dropdown({
                on : "hover"
            });

            $(".ui.form").form({
                fields : {
                    title : {
                        identifier : 'name',
                        rules : [{
                            type   : 'empty',
                            prompt : '请输入笔记名称'
                        }]
                    },
                    title2 : {
                        identifier : 'path',
                        rules : [{
                            type   : 'empty',
                            prompt : '请输入笔记路径'
                        }]
                    }
                }
            })

            const notesId = this.$route.query.notesId;
            if(notesId) {
                this.queryNotesById(notesId);
            }
        },

        closeBox(e) {
            const messageBox = e.target;
            $(messageBox).closest(".message").transition('fade')
        },

        queryNotesById(id) {
            const ref = this;
            queryById(id).then(res => {
                ref.notes = res.data;
            })
        },

        add() {
            const ref = this;
            add(this.notes).then(res => {
                if(res.code != 200) {
                    ref.errorMsg = true;
                    ref.errorMsgDesc = res.msg;
                } else {
                    window.history.go(-1);
                }
            })
        },

        update() {
            const ref = this;
            update(this.notes).then(res => {
                 if(res.code != 200) {
                    ref.errorMsg = true;
                    ref.errorMsgDesc = res.msg;
                } else {
                    window.history.go(-1);
                }
            })
        },

        submit() {
            console.log(this.notes);
            if(!this.notes.name) {
                this.errorMsg = true;
                this.errorMsgDesc = '笔记名称不能为空'

                return;
            }
            if(!this.notes.path) {
                this.errorMsg = true;
                this.errorMsgDesc = '笔记路径不能为空'

                return;
            }
            if(!this.notes.orderId) {
                this.errorMsg = true;
                this.errorMsgDesc = '笔记排序不能为空'

                return;
            }
            if(isNaN(this.notes.orderId)) {
                 this.errorMsg = true;
                this.errorMsgDesc = '笔记排序只能为数字,不能为字符串'

                return;
            }

            if(this.notes.id) {
                this.update()
            } else {
                this.add();
            }
        }
    }
}
</script>

<style>

</style>