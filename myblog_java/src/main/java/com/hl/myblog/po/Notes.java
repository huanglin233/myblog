package com.hl.myblog.po;

import lombok.Data;

import java.util.Date;

/**
 * 学习笔记,该表只记录笔记的的地址链接和笔记名称。笔记查看直接访问连接
 * @author huanglin
 * @date 2022/05/22 17:36
 */

@Data
public class Notes {

    private Integer id;
    private String  name; // 笔记名称
    private String  path; // 笔记服务路径
    private Integer state;
    private Integer orderId;
    private Date    createTime;
    private Date    updateTime;


    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id         = " + id + "\n");
        sb.append(prefix + "name       = " + name + "\n");
        sb.append(prefix + "path       = " + path + "\n");
        sb.append(prefix + "state      = " + state + "\n");
        sb.append(prefix + "orderId    = " + orderId + "\n");
        sb.append(prefix + "createTime = " + createTime + "\n");
        sb.append(prefix + "updateTime = " + updateTime + "\n");


        return sb.toString();
    }
}
