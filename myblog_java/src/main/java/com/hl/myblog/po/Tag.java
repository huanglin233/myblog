package com.hl.myblog.po;

import lombok.Data;

/**
 * 标签类实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

@Data
public class Tag {
	
    private Long   id;
    private String name;
    private Long   blogsNum;

	public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id       = " + id       + "\n");
        sb.append(prefix + "name     = " + name     + "\n");
        sb.append(prefix + "blogsNum = " + blogsNum + "\n");

        return sb.toString();
    }
}