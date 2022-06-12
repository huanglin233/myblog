package com.hl.myblog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.constants.HttpStatus;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.dao.NotesMapper;
import com.hl.myblog.po.Notes;
import com.hl.myblog.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huanglin
 * @date 2022/05/28 14:23
 */

@RestController
@CrossOrigin
@Api(value = "笔记查询模块", tags = "笔记查询模块接口")
@RequestMapping("/notes")
@Slf4j
public class NotesShowController {

    @Autowired
    NotesMapper notesMapper;

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "查询笔记列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "Integer" , paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "keyWords", value = "搜索关键字", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态", required = false, dataType = "Integer", paramType = "query")
    })
    @RecordLog(detail = "查询笔记列表", recordType = RecordType.SELECT, recordObject = RecordObject.NOTES)
    @GetMapping("/queryList")
    public ResponseResult queryList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "20")Integer pageSize,
                                    @RequestParam(value = "keyWords", required = false)String keyWords, @RequestParam(value = "state", required = false)Integer state){

        try {
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<Notes> pageInfo = new PageInfo(notesMapper.queryNotes(keyWords, state));
            return ResponseResult.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(HttpStatus.ERROR, "数据查询异常: " + e.getMessage());
        }
    }

    @AccessLimit(seconds = 1, maxCount = 50)
    @ApiOperation(value = "查询笔记信息")
    @ApiImplicitParam(name = "id", value = "笔记id", required = true, dataType = "Long", paramType = "query")
    @RecordLog(detail = "查询笔记", recordType = RecordType.SELECT, recordObject = RecordObject.NOTES)
    @GetMapping("/query/{id}")
    public ResponseResult queryById(@PathVariable(value = "id")Long id) {
        try {
            return ResponseResult.success(notesMapper.queryNotesById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(HttpStatus.ERROR, "数据查询异常: " + e.getMessage());
        }
    }
}
