package com.volunteer.gasstation.manager.system.entity;

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
 * @since 2021-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_user")
public class User implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String username;

    private String password;

    private String phone;

    private Integer status;

    @TableField("role_ids")
    private String roleIds;

    @TableField("create_time")
    private LocalDateTime createTime;


}
