package com.hl.myblog.web;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.po.Blog;
import com.hl.myblog.service.impl.BlogServiceImpl;
import com.hl.myblog.service.impl.TypeServiceImpl;
import com.hl.myblog.vo.BlogArchive;
import com.hl.myblog.vo.BlogQuery;
import com.hl.myblog.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客查询模块
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

@RestController
@CrossOrigin
@Api(value = "博客查询模块", tags = "博客查询接口")
public class BlogShowController {

    @Autowired
    private BlogServiceImpl blogServiceImpl;
    @Autowired
    private TypeServiceImpl typeServiceImpl;

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "获取博客列表", notes = "根据分页获取博客信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "显示的页数", paramType = "path", dataType = "Integer"), 
                        @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "path", dataType = "Integer")})
    @GetMapping("/blogs/{pageNum}/{pageSize}")
    public ResponseResult blogs(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Blog> blogs = blogServiceImpl.getBlogList(pageNum, pageSize);

        return ResponseResult.success(blogs);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "博客信息搜索", notes = "根据条件搜索博客信息的接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "显示的页数", paramType = "path", dataType = "Integer"), 
                        @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "path", dataType = "Integer"),
                        @ApiImplicitParam(name = "blogQuery", value = "博客查询条件", paramType = "Path", dataType = "BlogQuery")})
    @PostMapping("/blogs/search/{pageNum}/{pageSize}")
    public ResponseResult search(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody BlogQuery blogQuery) {
        PageInfo<Blog> blogs = blogServiceImpl.getBlogList(pageNum, pageSize, blogQuery.title, blogQuery.typeId, blogQuery.tagId, blogQuery.recomment, blogQuery.published);

        return ResponseResult.success(blogs);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "博客信息查询", notes = "根据id查询博客的接口")
    @ApiImplicitParam(name = "id", value = "博客id", paramType = "path", dataType = "Long")
    @GetMapping("/blog/{id}")
    public ResponseResult getBlog(@PathVariable("id") Long id) {
        Blog blog = blogServiceImpl.getBlog(id);

        return ResponseResult.success(blog);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "博客信息查询", notes = "根据id查询博客的接口并把markdown转化为html")
    @ApiImplicitParam(name = "id", value = "博客id", paramType = "path", dataType = "Long")
    @GetMapping("/blog/convert/{id}")
    public ResponseResult getBlogAndConvert(@PathVariable("id") Long id) {
        Blog blog = blogServiceImpl.getAndConvert(id);

        return ResponseResult.success(blog);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "最近推荐博客查询", notes = "查询博客最新推荐的接口")
    @ApiImplicitParam(name = "num", value = "查询推荐博客的数据量", required = true, paramType = "path", dataType = "Integer")
    @GetMapping("/blogs/recommentBlogTop/{num}")
    public ResponseResult newBlogs(@PathVariable("num") Integer num) {
        List<Blog> blogs = blogServiceImpl.getCommentBlogTopList(num);

        return ResponseResult.success(blogs);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "查询博客归档", notes = "查询博客归档信息接口")
    @GetMapping("/blogs/archives")
    public ResponseResult archives() {
        List<BlogArchive>   blogs = blogServiceImpl.archiveBlog();
        Long                count  = blogServiceImpl.countBlog();
        Map<String, Object> hasMap = new HashMap<String, Object>();
        hasMap.put("blogs", blogs);
        hasMap.put("count", count);

        return ResponseResult.success(hasMap);
    }
}