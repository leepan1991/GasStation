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
@TableName("auth_resource")
public class Resource implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    @TableField("parent_id")
    private Long parentId;


}
