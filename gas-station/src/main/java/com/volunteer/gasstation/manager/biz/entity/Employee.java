package com.volunteer.gasstation.manager.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author huoyao
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("biz_employee")
public class Employee implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String phone;

    private String password;

    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;


}
