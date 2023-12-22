package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.RevenueStatistics;

/**
 * 营收统计Service接口
 *
 * @author qiutuan
 * @date 2023-12-17
 */
public interface IRevenueStatisticsService
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
     * 批量删除营收统计
     *
     * @param statIds 需要删除的营收统计主键集合
     * @return 结果
     */
    public int deleteRevenueStatisticsByStatIds(String[] statIds);

    /**
     * 删除营收统计信息
     *
     * @param statId 营收统计主键
     * @return 结果
     */
    public int deleteRevenueStatisticsByStatId(String statId);
}
