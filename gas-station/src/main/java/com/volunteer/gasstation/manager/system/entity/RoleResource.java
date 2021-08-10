package com.volunteer.gasstation.manager.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author huoyao
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_role_resource")
public class RoleResource implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("resource_id")
    private Long resourceId;


}
