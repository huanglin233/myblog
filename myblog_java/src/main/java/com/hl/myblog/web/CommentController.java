package com.hl.myblog.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.common.utils.PrefixString;
import com.hl.myblog.po.Comment;
import com.hl.myblog.po.User;
import com.hl.myblog.security.jwt.JWTUtil;
import com.hl.myblog.service.impl.CommentServiceImpl;
import com.hl.myblog.service.impl.UserServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 博客评论管理模块
 * 
 * @author huanglin 
 * @date   2020年5月1日
 */

@CrossOrigin
@RestController
@Api(value = "博客评论管理模块", tags = "博客评论管理模块接口")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private UserServiceImpl    userServiceImpl;

    @Value("${comment.avatar}")
    private String avatar;

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "查询博客评论", notes = "根据博客Id查询博客评论接口")
    @ApiImplicitParam(name = "blogId", value = "博客id", paramType = "path", dataType = "Long")
    @GetMapping("/comments/{blogId}")
    public ResponseResult getComments(@PathVariable("blogId") Long blogId) {
        List<Comment> comments = commentServiceImpl.getListComment(blogId);

        return ResponseResult.success(comments);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "添加博客评论", notes = "添加博客评论接口")
    @ApiImplicitParam(name = "comment", value = "博客评论信息", paramType = "query", dataType = "Comment")
    @PostMapping("/comments/add")
    public ResponseResult addComment(@RequestBody Comment comment, HttpServletRequest request) {
        System.out.println(comment.toString(PrefixString.Indent1()));
        String token = JWTUtil.getToken(request);
        if(token != null) {
            Long userId = JWTUtil.getUserId(JWTUtil.getToken(request));
            User user = userServiceImpl.queryUserById(userId);
            if(user != null) {
                comment.setAvatar(user.getAvatar());
                comment.setAdminComment(true);
                comment.setNickname(user.getNickname());
                if(commentServiceImpl.saveComment(comment) == 1) {
                    return ResponseResult.success();
                }
            }
            return ResponseResult.error();
        }
        comment.setAvatar(avatar);
        if(commentServiceImpl.saveComment(comment) == 1) {
            return ResponseResult.success();
        }

        return ResponseResult.error();
    }
}