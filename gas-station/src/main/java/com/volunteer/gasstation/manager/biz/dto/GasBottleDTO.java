package com.volunteer.gasstation.manager.biz.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author by huoyao on 2021/8/9.
 */
@Data
public class GasBottleDTO {
    private Long id;

    /**
     * 气瓶编号(自编号)[1]QPNO
     */
    private String qpno;

    /**
     * 气瓶品种（请用代码）[2]QPPZ
     */
    private String qppz;

    /**
     * 充装介质（请用代码）[3]CCJZ
     */
    private String ccjz;

    /**
     * 充装容积[4]QPRJ
     */
    private String qprj;

    /**
     * 公称工作压力[5]GCYL
     */
    private String gcyl;

    /**
     * 水压试验压力[6]SYYL
     */
    private String syyl;

    /**
     * 制造单位[7]ZZDW
     */
    private String zzdw;

    /**
     * 出厂日期CCRQ
     */
    private String ccrq;

    /**
     * 有效期限（年）[9]YXQX
     */
    private Integer yxqx;

    /**
     * 制造编号[10]CCNO
     */
    private String ccno;

    /**
     * 充装标志[11]DWNO
     */
    private String dwno;

    /**
     * 使用单位SYDW
     */
    private String sydw;

    /**
     * 产权性质(默认01)[13]CQXZ
     */
    private String cqxz;

    /**
     * 上次检验日期（年-月-日）如：2016-05-01（统一为1日）[14]JYRQF
     */
    private String jyrqf;

    /**
     * 下次检验日期如：2020-05-01（统一为1日）[15]JYRQN
     */
    private String jyrqn;

    /**
     * 气瓶名称[31]QPMC
     */
    private String qpmc;

    /**
     * 气瓶型号(可以和气瓶名字一样)[32]QPXH
     */
    private String qpxh;

    /**
     * 状态（默认01）[33]QP_STATUS
     */
    private String qpStatus;

    /**
     * 检验报告编号[16]JYNO
     */
    private String jyno;

    /**
     * 检验单位[17]JYDW
     */
    private String jydw;

    /**
     * 气瓶使用登记代码[18]SYNO
     */
    private String syno;

    /**
     * 使用日期[19]SYRQ
     */
    private LocalDate syrq;

    /**
     * 变更情况[20]BGQK
     */
    private String bgqk;

    /**
     * 停用情况[21]SYQK
     */
    private String syqk;

    /**
     * 备注[22]BZ
     */
    private String bz;

    /**
     * 建档日期[23]WRIDATE
     */
    private LocalDateTime wriDate;

    /**
     * 使用登记证编号（默认证书编号）[24]SYDJZBH
     */
    private String sydjzbh;

    /**
     * 发证日期[25]FZRQ
     */
    private LocalDateTime fzrq;

    /**
     * 机构代码（先空）[26]JGDM
     */
    private String jgdm;

    /**
     * 导入的序号[27]IMPXH
     */
    private String impxh;

    /**
     * "最近检验日期
     * （年-月-日）如：2016-05-01（统一为1日）[28]ZJJYRQ"
     */
    private LocalDateTime zjjyqr;

    /**
     * 气瓶厚度[29]QPHD
     */
    private String qphd;

    /**
     * 气瓶重量[30]QPZL
     */
    private String qpzl;

    /**
     * 充装单位（默认成使用单位）[34]CZDW
     */
    private String czdw;

    /**
     * 所属区域[35]SSQY
     */
    private String ssqy;

    /**
     * 所属区域名字[36]SSQYN
     */
    private String ssqyn;

    /**
     * 报废方式[37]BFFS
     */
    private String bffs;

    /**
     * 报废日期[38]BFRQ
     */
    private LocalDateTime bfrq;

    /**
     * 报废人[39]BFCZR
     */
    private String bfczr;

    private String createTime;
}
