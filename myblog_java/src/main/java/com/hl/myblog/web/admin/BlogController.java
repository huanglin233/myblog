package com.hl.myblog.web.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hl.myblog.po.Blog;
import com.hl.myblog.po.User;
import com.hl.myblog.security.jwt.JWTUtil;
import com.hl.myblog.service.impl.BlogServiceImpl;
import com.hl.myblog.service.impl.TagServiceImpl;
import com.hl.myblog.service.impl.TypeServiceImpl;
import com.hl.myblog.service.impl.UserServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 博客管理
 * 
 * @author huanglin 
 * @date   2020年4月20日
 */

@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(value = "博客后台模块", tags = {"博客的管理接口"})
public class BlogController {

    @Autowired
    private BlogServiceImpl blogServiceImpl;
    @Autowired
    private TypeServiceImpl typeServiceImpl;
    @Autowired
    private TagServiceImpl  tagServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "创建一个博客", notes = "添加博客接口")
    @ApiImplicitParam(name = "blog", value = "博客实体", paramType = "query", dataType = "Blog")
    @PostMapping("/blog/add")
    public ResponseResult addBlog(@RequestBody Blog blog, HttpServletRequest request) {
        if(!StringUtils.isEmpty(blog)) {
            Long userId = JWTUtil.getUserId(JWTUtil.getToken(request));
            User user   = userServiceImpl.queryUserById(userId);
            blog.setUser(user);
            blog.setType(typeServiceImpl.getType(blog.getTypeId()));
            blog.setTags(tagServiceImpl.getTagList(blog.getTagIds()));
            int saveResult = blogServiceImpl.saveBlog(blog);
            if(saveResult == 1) {
                return ResponseResult.success();
            }
        }

        return ResponseResult.error();
    }

    @ApiOperation(value = "更新博客", notes = "更新博客接口")
    @ApiImplicitParam(name = "blog", value = "博客实体", paramType = "query", dataType = "Blog")
    @PutMapping("/blog/update")
    public ResponseResult updateBlog(@RequestBody Blog blog, HttpServletRequest request) {
        if(!StringUtils.isEmpty(blog) && !StringUtils.isEmpty(blog.getId())) {
            Long userId = JWTUtil.getUserId(JWTUtil.getToken(request));
            User user   = userServiceImpl.queryUserById(userId);
            blog.setUser(user);
            blog.setType(typeServiceImpl.getType(blog.getTypeId()));
            blog.setTags(tagServiceImpl.getTagList(blog.getTagIds()));
            int saveResult = blogServiceImpl.updateBlog(blog);
            if(saveResult == 1) {
                return ResponseResult.success();
            }
        }

        return ResponseResult.error();
    }

    @ApiOperation(value = "删除博客", notes = "删除博客接口")
    @ApiImplicitParam(name = "id", value = "博客id", paramType = "path", dataType = "Long")
    @DeleteMapping("/blogs/delete/{id}")
    public ResponseResult delete(@PathVariable(value = "id") Long id) {
        int deleteResult = blogServiceImpl.deleteBlog(id);
        if(deleteResult == 1) {
            return ResponseResult.success();
        }

        return ResponseResult.error();
    }
}