package com.wei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//记录事物操作日志
public class DeptLog {
    private Integer id;
    private LocalDateTime createTime;
    private String description;
}
