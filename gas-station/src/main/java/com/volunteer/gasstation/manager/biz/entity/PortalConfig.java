package com.volunteer.gasstation.manager.biz.entity;

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
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("biz_portal_config")
public class PortalConfig implements Serializable {


    private Long id;

    private String code;

    private String content;


}
