package com.ruoyi.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商管理对象 supplier
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@Data
public class Supplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    private String supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 修改时间 */
    private LocalDateTime updatedAt;

    /** 是否删除 */
    private Integer isDeleted;


}
