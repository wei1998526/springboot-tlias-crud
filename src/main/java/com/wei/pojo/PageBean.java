package com.wei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询返回的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private long total;
    private List rows;
}
