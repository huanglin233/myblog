<template>
    <div id="CommentComponent">
        <div class="ui " v-if="comments.length > 0">
            <div class="ui comments">
                <div class="comment" v-for="(item, index) in comments" :key="index">
                    <a class="avatar">
                        <img :src="item.avatar">
                    </a>
                    <div class="content">
                        <a class="author">
                            <span v-text="item.nickname"></span>
                            <div class="ui mini basic teal left pointing label m-padded-mini" v-if="item.adminComment">博主</div>
                            &nbsp;<span v-text="'@' + item.parentComment" class="m-teal" v-if="item.parentCommentId != null"></span>
                        </a>
                        <div class="metadata">
                            <span class="date" >{{item.createTime | formatDate}}</span>
                        </div>
                        <div class="text" v-text="item.content"></div>
                        <div class="actions">
                            <a class="reply" @click="replyTo(item.id, item.nickname)">回复</a>
                        </div>
                    </div>
                    <div class="comments" v-if="item.replyComments.length > 0">
                            <CommentComponent :data="item.replyComments" @reply="replyTo"></CommentComponent>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name : 'CommentComponent',
    props : ['data'],
    data() {
        return {
            comments : [],
        }
    },
    mounted() {      
        this.comments = this.data;
    },
    methods: {
        replyTo : function(commentId, nickname) {
            this.$emit("reply", commentId, nickname);
        }
    },
    watch : {
        data : {
            immediate : true,
            handler(val) {
                this.comments = val;
            }
        }
    },
    filters : {
        formatDate : function(time) {
            let updateTime = new Date(time);

            return updateTime.getFullYear() + '-' + (updateTime.getMonth() + 1) + '-' + updateTime.getDate();
        }
    } 
}
</script>