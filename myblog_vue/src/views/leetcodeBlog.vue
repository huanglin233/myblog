<template>
  <div id="leetcode">
      <HeaderComponent header="leetcode" @search="search" @rest="rest"></HeaderComponent>

      <div class="m-container m-padded-tb-big">
          <div class="ui container">
              <div class="ui stackable grid">
                  <div class="eleven wide column">
                        <!--header-->
                        <div class="ui top attached segment">
                            <div class="ui middle aligned two column grid">
                                <div class="column">
                                    <h3 class="ui teal header">博客</h3>
                                </div>
                                <div class="right aligned column">
                                    共<h2 class="ui orange header m-inline-block m-text-thin">{{blogs.total}}</h2>篇
                                </div>
                            </div>
                        </div>
                        <!--content-->
                        <blog-card :blogs=blogs></blog-card>
                        <!--分页footer-->
                        <div class="ui bottom attached segment">
                            <div class="ui middle aligned two column grid">
                                <div class="column" v-if="blogs.hasPreviousPage">
                                    <a class="ui mini teal basic button" @click="queryList(blogs.pageNum - 1,5)">上一页</a>
                                </div>
                                <div class="right aligned column" v-if="blogs.hasNextPage">
                                    <a class="ui mini teal basic button" @click="queryList(blogs.pageNum + 1, 5)">下一页</a>
                                </div>
                            </div>
                        </div>
                    </div>
              </div>
          </div>
      </div>

      <FooterComponent class="footerComponent"></FooterComponent>
  </div>
</template>

<script>
import {queryByBlogQuery} from '../api/blog';
import BlogCard from '../components/BlogCard.vue'
export default {
    name: "leetcode",
    components: {
        BlogCard
    },
    data() {
        return {
            blogs          : [],
            searchKey      : null
        };
    },
    mounted() {
        this.searchKey = this.$route.query.title;
        this.queryList(1, 5);
    },
    methods: {
        // 获取博客列表
        queryList : function(pageNum, pageSize) {
            const ref = this;
            const blogQuery = {
                title : this.searchKey,
                leetcode : true
            }
            queryByBlogQuery(pageNum, pageSize, blogQuery).then(response => {                
                for(let blog of response.data.list) {
                    let updateTime  = new Date(blog.updateTime);
                    blog.updateTime = updateTime.getFullYear() + '-' + (updateTime.getMonth() + 1) + '-' + updateTime.getDate();
                }
                ref.blogs = response.data;
            })
        },
        search : function(title) {
            this.searchKey = title;
            this.queryList(1, 5);
        },
        rest : function() {
            this.queryList(1,5);
        }
    }
}
</script>

<style>

</style>