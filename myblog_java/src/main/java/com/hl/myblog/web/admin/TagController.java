package com.hl.myblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hl.myblog.po.Tag;
import com.hl.myblog.service.impl.TagServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 标签的管理
 * 
 * @author huanglin 
 * @date   2020年4月11日
 */

@RestController
@RequestMapping("/admin")
@Api(value = "标签管理模块", tags = {"标签管理接口"})
public class TagController {

    @Autowired
    TagServiceImpl tagServiceImpl;

    @ApiOperation(value = "新建标签", notes = "新建标签接口")
    @ApiImplicitParam(name = "tag", value = "标签信息", paramType = "query", dataType = "Tag")
    @PostMapping("/tags/add")
    public ResponseResult tagAdd(Tag tag) {
        Tag tagByName = tagServiceImpl.getTagByName(tag.getName());
        if(tagByName != null) {
            return ResponseResult.error("不能创建重复的标签");
        }

        int addResult = tagServiceImpl.saveTag(tag);
        if(addResult != 1) {
            return ResponseResult.error();
        } else {
            return ResponseResult.success();
      }
    }

    @ApiOperation(value = "更新标签", notes = "更新标签接口")
    @ApiImplicitParam(name = "tag", value = "标签信息", paramType = "query", dataType = "Tag")
    @PutMapping("/tags/edit")
    public ResponseResult tagsEdit(Tag tag) {
        int updateResult = tagServiceImpl.updateTag(tag);
        if(updateResult != 1) {
            return ResponseResult.error();
        } else {
            return ResponseResult.success();
        }
    }

    @ApiOperation(value = "删除标签", notes = "删除标签接口")
    @ApiImplicitParam(name = "id", value = "标签Id", paramType = "path", dataType = "Integer")
    @DeleteMapping("/delete/tag/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id) {
        int deleteResult = tagServiceImpl.deleteTag(id);
        if(deleteResult != 1) {
            return ResponseResult.error();
        } else {
            return ResponseResult.success();
        }
    }
}