<template>
  <div id='tip'>
    <div class="ui modal" style="width: 300px; border-radius: 10px">
        <div class="m-padded-tiny" style="text-align: center">
            <div class="m-padded-tb-tiny">
                请确认删除次条记录 -- {{desc}}
            </div>
            <button class="ui red basic button mini" @click="ok">确定</button>
            <button class="ui teal basic button mini" @click="cancel">取消</button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    name : 'tip',
    data() {
        return {
            id : undefined,
            desc : undefined,
        }
    },
    methods: {
        ok() {
            this.$emit("isOk", this.id);
            $('.ui.modal').modal("hide");   
            this.id = undefined;
            this.desc = undefined;
        },
        cancel() {
            $('.ui.modal').modal("hide"); 
        },
        isShow(id, desc) {
            this.id = id;
            this.desc = desc;
            $('.ui.modal').modal("show");   
        }
    }

}
</script>

<style>

</style>