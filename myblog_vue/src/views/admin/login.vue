<template>
  <div class="m-container-small m-padded-tb-massive vertical-horizontal" style="max-width: 40em !important;">
    <div class="ur container">
      <div class="ui middle aligned center aligned grid">
        <div class="column">
          <h2 class="ui teal image header">
            <div class="content">
              管理后台登录
            </div>
          </h2>
          <form class="ui large form"  method="post" action="/admin/doLogin">
            <div class="ui  segment">
              <div class="field">
                <div class="ui left icon input" style="width :350px">
                  <i class="user icon"></i>
                  <input type="text" name="userName" placeholder="用户名" v-model="userName">
                </div>
              </div>
              <div class="field">
                <div class="ui left icon input" style="width :350px">
                  <i class="lock icon"></i>
                  <input type="password" name="password" placeholder="密码" v-model="password">
                </div>
              </div>
            </div>
            <div class="ui error message"></div>
            <button class="ui fluid large teal button">登   录</button>
          </form>
          <div class="ui mini negative message" :class="{hidden : hiddens}" v-text="message">用户名和密码错误</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {login} from "../../api/login"
export default {
    name : "login",
    data(){
        return {
          userName : '',
          password : '',
          hiddens  : true,
          message  : undefined
        }
    },
    mounted() {
      this.init();
    },
    methods : {
      init() {
        let ref = this;
        /** 表单验证 */
        $('.ui.form').form({
          fields : {
            userName : {
              identifier : 'userName',
              rules : [
                {
                  type   : 'empty',
                  prompt : '账号不能为空'
                }
              ]
            },
            password : {
              identifier : 'password',
              rules : [
                {
                  type   : 'empty',
                  prompt : '密码不能为空'
                }
              ]
            }
          },
          onSuccess : function(event, fields) {
            event.preventDefault();
            login(fields).then(res => {
              if(res.code === 200) {
                ref.$router.push('/admin/index');
              } else {
                  ref.hiddens = false;
                  ref.message = res.msg + " :用户名和密码错误";
              }
            }).catch(err => {
              ref.hiddens = false;
              ref.message = res.msg + " :服务出错";
            })
          }
        })
      }
    }
}
</script>