<template>
    <div>
        <div class="ui attached segment" v-for="(item, index) in blogs.list" :key="index">
            <div class="ui padded vertical segment m-padded-tb-large m-margin-lr m-mobile-lr-clear">
                <div class="ui mobile reversed stackable grid">
                    <div class="eleven wide column">
                        <h3 class="ui header">
                            <router-link :to="'/blog?blogId=' + item.id" target="_blank" class=m-black>{{item.title}}</router-link>
                        </h3>
                        <p class="m-text">
                            {{item.description}}
                        </p>
                        <div class="ui grid">
                            <div class="eleven wide column">
                                <div class="ui mini horizontal link list">
                                    <div class="item">
                                        <img class="ui avatar image" :src="item.user.avatar" alt="">
                                        <div class="content">
                                            <router-link to="/about" class="header">{{item.user.nickname}}</router-link>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <i class="calendar icon"></i><span>{{item.updateTime | dateFormat}}</span>
                                    </div>
                                    <div class="item">
                                        <i class="eye icon"></i><span>{{item.views}}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="right aligned five wide column" v-if="item.type != null">
                                <a href="#" target="_blank" class="ui teal basic label m-padded-tb-tiny m-text-thin">{{item.type.name}}</a>
                            </div>
                        </div>
                    </div>
                    <div class="five wide column">
                        <a href="#"  target="_blank">
                            <img class="ui rounded image" :src="item.firstPicture">
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
                
</template>

<script>
export default {
    name: 'blogCard',
    props: ['blogs'],
    data() {
        return {}
    },
    methods: {},
    filters: {
         // 时间戳转化为指定时间格式
        dateFormat(val) {
            const time = new Date(val);
            
            return time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + " " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
        }
    }
}
</script>

<style>

</style>