package com.ruoyi.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品对象 product
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@Data
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 商品分类ID */
    @Excel(name = "商品分类ID")
    private String categoryId;

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Long stock;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String imagePath;

    /** 创建时间 */
    private Date createdAt;

    /** 修改时间 */
    private Date updatedAt;

    /** 是否删除 */
    private Integer isDeleted;
    
}
