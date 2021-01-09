package com.hl.myblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.common.constants.HttpStatus;
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

@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(value = "标签管理模块", tags = {"标签管理接口"})
public class TagController {

    @Autowired
    TagServiceImpl tagServiceImpl;

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "新建标签", notes = "新建标签接口")
    @ApiImplicitParam(name = "tag", value = "标签信息", paramType = "query", dataType = "Tag")
    @PostMapping("/tag/add")
    public ResponseResult tagAdd(@RequestBody Tag tag) {
        Tag tagByName = tagServiceImpl.getTagByName(tag.getName());
        if(tagByName != null) {
            return ResponseResult.error(HttpStatus.NOT_MODIFIED ,"不能创建重复的标签");
        }

        int addResult = tagServiceImpl.saveTag(tag);
        if(addResult != 1) {
            return ResponseResult.error(HttpStatus.NOT_MODIFIED ,"创建标签失败");
        } else {
            return ResponseResult.success();
      }
    }

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "更新标签", notes = "更新标签接口")
    @ApiImplicitParam(name = "tag", value = "标签信息", paramType = "query", dataType = "Tag")
    @PutMapping("/tag/update")
    public ResponseResult tagsEdit(@RequestBody Tag tag) {
        int updateResult = tagServiceImpl.updateTag(tag);
        if(updateResult != 1) {
            return ResponseResult.error(HttpStatus.NOT_MODIFIED, "标签更新失败");
        } else {
            return ResponseResult.success();
        }
    }

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "删除标签", notes = "删除标签接口")
    @ApiImplicitParam(name = "id", value = "标签Id", paramType = "path", dataType = "Integer")
    @DeleteMapping("/tag/delete/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id) {
        int deleteResult = tagServiceImpl.deleteTag(id);
        if(deleteResult < 0) {
            return ResponseResult.error(HttpStatus.NOT_MODIFIED, "标签删除失败");
        } else {
            return ResponseResult.success();
        }
    }
}