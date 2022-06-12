package com.hl.myblog.web.admin;

import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.dao.NotesMapper;
import com.hl.myblog.po.Notes;
import com.hl.myblog.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huanglin
 * @date 2022/05/28 14:56
 */

@RestController
@CrossOrigin
@Api(value = "标签后台管理模块", tags = "标签后台管理模块")
@RequestMapping("/admin/notes")
public class NotesController {

    @Autowired
    NotesMapper notesMapper;

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "创建笔记", notes = "添加笔记接口")
    @ApiImplicitParam(name = "notes", value = "笔记", dataType = "Notes", paramType = "body")
    @RecordLog(detail = "创建笔记", recordType = RecordType.INSERT, recordObject = RecordObject.NOTES)
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Notes notes) {
        try {
            notesMapper.addNotes(notes);
            return ResponseResult.success("笔记添加成功");
        } catch (Exception e ) {
            e.printStackTrace();
            return ResponseResult.error("添加笔记出错: " + e.getMessage());
        }
    }

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "更新笔记", notes = "更新笔记接口")
    @ApiImplicitParam(name = "notes", value = "笔记", dataType = "Notes", paramType = "body")
    @RecordLog(detail = "更新笔记", recordType = RecordType.UPDATE, recordObject = RecordObject.NOTES)
    @PutMapping("/update")
    public ResponseResult update(@RequestBody Notes notes) {
        try {
            notesMapper.updateNotes(notes);
            return ResponseResult.success("更新笔记成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("更新笔记出错: " + e.getMessage());
        }
    }

    @AccessLimit(seconds = 1, maxCount = 10)
    @ApiOperation(value = "删除笔记", notes = "删除笔记接口")
    @ApiImplicitParam(name = "id", value = "笔记id", dataType = "Integer", paramType = "path")
    @RecordLog(detail = "删除笔记", recordType = RecordType.DELETE, recordObject = RecordObject.NOTES)
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable(value = "id")Integer id) {
        try {
            notesMapper.deleteNotes(id);
            return ResponseResult.success("删除笔记成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("删除笔记失败：" + e.getMessage());
        }
    }
}
