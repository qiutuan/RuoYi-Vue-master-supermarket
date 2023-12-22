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
 * 供货记录对象 supply_record
 * 
 * @author qiutuan
 * @date 2023-12-15
 */
@Data
public class SupplyRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private String recordId;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierId;

    private String supplierName;

    /** 商品 */
    @Excel(name = "商品")
    private String productId;

    private String productName;

    /** 供货数量 */
    @Excel(name = "供货数量")
    private Long supplyQuantity;

    /** 供货时间 */
    private Date supplyTime;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 修改时间 */
    private LocalDateTime updatedAt;

    /** 是否删除 */
    private Integer isDeleted;

}
