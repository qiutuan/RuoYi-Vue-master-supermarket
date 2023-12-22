package com.ruoyi.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.domain.RevenueStatistics;
import org.apache.ibatis.annotations.Param;

/**
 * 营收统计Mapper接口
 *
 * @author qiutuan
 * @date 2023-12-17
 */
public interface RevenueStatisticsMapper
{
    /**
     * 查询营收统计
     *
     * @param statId 营收统计主键
     * @return 营收统计
     */
    public RevenueStatistics selectRevenueStatisticsByStatId(String statId);

    /**
     * 查询营收统计列表
     *
     * @param revenueStatistics 营收统计
     * @return 营收统计集合
     */
    public List<RevenueStatistics> selectRevenueStatisticsList(RevenueStatistics revenueStatistics);

    /**
     * 新增营收统计
     *
     * @param revenueStatistics 营收统计
     * @return 结果
     */
    public int insertRevenueStatistics(RevenueStatistics revenueStatistics);

    /**
     * 修改营收统计
     *
     * @param revenueStatistics 营收统计
     * @return 结果
     */
    public int updateRevenueStatistics(RevenueStatistics revenueStatistics);

    /**
     * 删除营收统计
     *
     * @param statId 营收统计主键
     * @return 结果
     */
    public int deleteRevenueStatisticsByStatId(String statId);

    /**
     * 批量删除营收统计
     *
     * @param statIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRevenueStatisticsByStatIds(String[] statIds);

    public RevenueStatistics selectRevenueStatisticsByDate(@Param("date") String date);
}
