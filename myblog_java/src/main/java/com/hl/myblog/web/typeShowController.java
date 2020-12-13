package com.hl.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
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
public class typeShowController {

    @Autowired
    TypeServiceImpl typeServiceImpl;

    @ApiOperation(value = "获取分类列表", notes = "根据分页获取分类信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "显示的页数", paramType = "path", dataType = "Integer"), 
                        @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "path", dataType = "Integer")})
    @GetMapping("/types/{pageNum}/{pageSize}")
    public ResponseResult types(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Type> typeList = typeServiceImpl.getTypeList(pageNum, pageSize);

        return ResponseResult.success(typeList);
    }

    @ApiImplicitParam(name = "tagId", value = "标签Id", paramType = "path", dataType = "Long")
    @GetMapping("/types/{id}")
    public ResponseResult getTypes(@PathVariable("id") Long typeId) {
        Type type = typeServiceImpl.getType(typeId);

        return ResponseResult.success(type);
    }
}