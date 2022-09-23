package com.cn.jmw.entity.table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月23日 17:30
 * @Version 1.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer permission;
}
