<template>
  <div id="bj">
        <HeaderComponent header="bj" @rest="rest"></HeaderComponent>
        <div id="body" class="ui container">
            <div class="ui divided items">
                <div class="item" v-for="(notes, index) in notesList" :item = "notes" :key="index">
                    <span>
                        <h3 class="ui aligned header m-padded" @click="toPage(notes.path)">
                            <i class="mini teal circle icon"></i>
                            {{notes.name}}
                        </h3>
                    </span>
                </div>
            </div>
        </div>
        <FooterComponent class="footerComponent"></FooterComponent>
  </div>
</template>

<script>
import {queryList} from '../api/notes'
export default {
    name : 'bj',
    data() {
        return {
            params : {
                pageSize : 20,
                pageNum : 1,
                keyWords : null,
                state : 1
            },
            notesList : []
        }
    },
    mounted() {
        this.queryList();
    },
    methods : {
        queryList() {
            const that = this;
            queryList(this.params).then(res => {
                console.log(res);
                that.notesList = res.data.list
            })
        },
        rest : function() {
            this.queryList();
        },
        toPage(path) {
            window.open(path)
        }
    }
}
</script>

<style>

</style>