package com.hl.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.po.Tag;
import com.hl.myblog.service.impl.TagServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 博客标签查询模块
 * 
 * @author huanglin 
 * @date   2020年5月1日
 */

@RestController
@CrossOrigin
@Api(value = "标签查询模块", tags = "标签查询接口")
public class TagShowController {

    @Autowired
    private TagServiceImpl  tagServiceImpl;

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "获取标签列表", notes = "根据分页获取标签接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "显示的页数", paramType = "query", dataType = "Integer", required = false), 
                        @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "query", dataType = "Integer", required = false)})
    @GetMapping("/tags")
    public ResponseResult tags(@RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum, @RequestParam(name = "pageSize", defaultValue = "999", required = false) Integer pageSize) {
        PageInfo<Tag> tags = tagServiceImpl.getTagList(pageNum, pageSize);

        return ResponseResult.success(tags);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "获取标签信息", notes = "根据标签id进行查询")
    @ApiImplicitParam(name = "id", value = "标签Id", paramType = "path", dataType = "Long")
    @GetMapping("/tags/{id}")
    public ResponseResult getTags(@PathVariable("id") Long tagId) {
        Tag tag = tagServiceImpl.getTag(tagId);

        return ResponseResult.success(tag);
    }
}