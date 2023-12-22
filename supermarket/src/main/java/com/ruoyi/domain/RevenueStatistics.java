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
 * 营收统计对象 revenue_statistics
 * 
 * @author qiutuan
 * @date 2023-12-17
 */
@Data
public class RevenueStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计ID */
    private String statId;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 总销售额 */
    @Excel(name = "总销售额")
    private BigDecimal totalSales;

    /** 总支出 */
    private BigDecimal totalExpenses;

    /** 净收入 */
    private BigDecimal netIncome;

    /** 创建时间 */
    private Date createdAt;

    /** 修改时间 */
    private Date updatedAt;

    /** 是否删除 */
    private Integer isDeleted;

    public void setStatId(String statId) 
    {
        this.statId = statId;
    }

    public String getStatId() 
    {
        return statId;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setTotalSales(BigDecimal totalSales) 
    {
        this.totalSales = totalSales;
    }

    public BigDecimal getTotalSales() 
    {
        return totalSales;
    }
    public void setTotalExpenses(BigDecimal totalExpenses) 
    {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getTotalExpenses() 
    {
        return totalExpenses;
    }
    public void setNetIncome(BigDecimal netIncome) 
    {
        this.netIncome = netIncome;
    }

    public BigDecimal getNetIncome() 
    {
        return netIncome;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("statId", getStatId())
            .append("date", getDate())
            .append("totalSales", getTotalSales())
            .append("totalExpenses", getTotalExpenses())
            .append("netIncome", getNetIncome())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
