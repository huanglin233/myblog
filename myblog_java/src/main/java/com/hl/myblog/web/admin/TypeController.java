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

import com.hl.myblog.common.constants.HttpStatus;
import com.hl.myblog.po.Type;
import com.hl.myblog.service.impl.TypeServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 分类管理
 * 
 * @author huanglin 
 * @date   2020年4月12日
 */

@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(value = "分类管理模块", tags = {"分类管理接口"})
public class TypeController {

    @Autowired
    TypeServiceImpl typeServiceImpl;

    @ApiOperation(value = "新建分类", notes = "新建分类接口")
    @ApiImplicitParam(name = "type", value = "分类信息", paramType = "query", dataType = "Type")
    @PostMapping("/type/add")
    public ResponseResult typesAdd(@RequestBody Type type) {
        Type typeByName = typeServiceImpl.getTypeByName(type.getName());
        if(typeByName != null) {
          return ResponseResult.error(HttpStatus.NOT_MODIFIED,"不能添加重复的分类");
        }

        int addResult = typeServiceImpl.saveType(type);
        if(addResult != 1) {
            return ResponseResult.error();
        } else {
            return ResponseResult.success();
        }
    }

    @ApiOperation(value = "更新分类", notes = "更新分类接口")
    @ApiImplicitParam(name = "type", value = "分类信息", paramType = "query", dataType = "Type")
    @PutMapping("/type/update")
    public ResponseResult typesEdit(@RequestBody Type type) { 
        Type typeByName = typeServiceImpl.getTypeByName(type.getName());
        if(typeByName != null) {
          return ResponseResult.error(HttpStatus.NOT_MODIFIED,"不能添加重复的分类");
        }

        int updateType = typeServiceImpl.updateType(type);
        if(updateType != 1) {
            return ResponseResult.error();
        } else {
            return ResponseResult.success();
        }
    }

    @ApiOperation(value = "删除分类", notes = "删除分类接口")
    @ApiImplicitParam(name = "id", value = "分类id", paramType = "path", dataType = "Long")
    @DeleteMapping("/type/delete/{id}")
    public ResponseResult deleteType(@PathVariable("id") Long id) {
        int deleteResult = typeServiceImpl.deleteType(id);
        if(deleteResult != 1) {
            return ResponseResult.error();
        } else {
            return ResponseResult.success();
        }
    }
}