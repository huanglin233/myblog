package com.hl.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.po.Type;
import com.hl.myblog.service.impl.TypeServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 博客分类查询模块
 * 
 * @author huanglin 
 * @date   2020年5月1日
 */

@RestController
@CrossOrigin
@Api(value = "博客分类查询模块", tags = "博客分类查询接口")
public class TypeShowController {

    @Autowired
    TypeServiceImpl typeServiceImpl;

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "获取分类列表", notes = "根据分页获取分类信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "显示的页数", paramType = "query", dataType = "Integer", required = false),
                        @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "query", dataType = "Integer", required = false)})
    @GetMapping("/types")
    public ResponseResult types(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "999") Integer pageSize) {
        PageInfo<Type> typeList = typeServiceImpl.getTypeList(pageNum, pageSize);

        return ResponseResult.success(typeList);
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "获取分类信息", notes = "根据分类id获取分类信息")
    @ApiImplicitParam(name = "id", value = "标签Id", paramType = "path", dataType = "Long")
    @GetMapping("/types/{id}")
    public ResponseResult getTypes(@PathVariable("id") Long typeId) {
        Type type = typeServiceImpl.getType(typeId);

        return ResponseResult.success(type);
    }
}