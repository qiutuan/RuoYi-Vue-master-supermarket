package com.ruoyi.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 商品分类管理对象 category
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@Data
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private String categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 修改时间 */
    private LocalDateTime updatedAt;

    /** 是否删除 */
    private Integer isDeleted;

}
