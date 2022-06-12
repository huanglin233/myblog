package com.hl.myblog.dao;

import com.hl.myblog.po.Notes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huanglin
 * @date 2022/05/22 17:43
 */

@Mapper
public interface NotesMapper {

    /**
     * 查询所有笔记状态为可查看的
     * @param  state 笔记状态，1可查看/0不可查看
     * @return
     */
    public List<Notes> queryNotes(@Param("keyWords")String keywords, @Param("state")Integer state);

    public Notes queryNotesById(@Param("id")Long id);

    /**
     * 新增笔记
     * @param notes 笔记信息
     */
    public void addNotes(@Param("notes")Notes notes);

    /**
     * 更新笔记
     * @param notes 笔记信息
     */
    public void updateNotes(@Param("notes")Notes notes);

    /**
     * 删除笔记记录
     * @param id
     */
    public void deleteNotes(@Param("id")Integer id);
}
