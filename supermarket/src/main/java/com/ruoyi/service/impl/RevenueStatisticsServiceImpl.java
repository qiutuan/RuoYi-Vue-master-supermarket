package com.ruoyi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.RevenueStatisticsMapper;
import com.ruoyi.domain.RevenueStatistics;
import com.ruoyi.service.IRevenueStatisticsService;

/**
 * 营收统计Service业务层处理
 *
 * @author qiutuan
 * @date 2023-12-17
 */
@Service
public class RevenueStatisticsServiceImpl implements IRevenueStatisticsService
{
    @Autowired
    private RevenueStatisticsMapper revenueStatisticsMapper;

    /**
     * 查询营收统计
     *
     * @param statId 营收统计主键
     * @return 营收统计
     */
    @Override
    public RevenueStatistics selectRevenueStatisticsByStatId(String statId)
    {
        return revenueStatisticsMapper.selectRevenueStatisticsByStatId(statId);
    }

    /**
     * 查询营收统计列表
     *
     * @param revenueStatistics 营收统计
     * @return 营收统计
     */
    @Override
    public List<RevenueStatistics> selectRevenueStatisticsList(RevenueStatistics revenueStatistics)
    {
        return revenueStatisticsMapper.selectRevenueStatisticsList(revenueStatistics);
    }

    /**
     * 新增营收统计
     *
     * @param revenueStatistics 营收统计
     * @return 结果
     */
    @Override
    public int insertRevenueStatistics(RevenueStatistics revenueStatistics)
    {
        //判断今天是否已经创建营收统计记录
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);

        String day = new SimpleDateFormat("yyyy-MM-dd").format(date);

        RevenueStatistics todayRevenueStatistics = revenueStatisticsMapper.selectRevenueStatisticsByDate(day);
        System.out.println(todayRevenueStatistics);

        if(todayRevenueStatistics != null){
            //如果今天已经创建了营收统计记录，则直修改今天的营收统计记录
            if (todayRevenueStatistics.getTotalSales() == null)
                todayRevenueStatistics.setTotalSales(new BigDecimal(0));

            todayRevenueStatistics.setTotalSales(todayRevenueStatistics.getTotalSales().add(revenueStatistics.getTotalSales()));
            return revenueStatisticsMapper.updateRevenueStatistics(todayRevenueStatistics);

        }else {
            //如果今天还没有创建营收统计记录，则创建今天的营收统计记录
            revenueStatistics.setStatId(UUID.randomUUID().toString());
            revenueStatistics.setDate(date);
            revenueStatistics.setCreatedAt(new Date());
            revenueStatistics.setUpdatedAt(new Date());
            revenueStatistics.setIsDeleted(0);
            revenueStatistics.setNetIncome(new BigDecimal(0));
            revenueStatistics.setTotalExpenses(new BigDecimal(0));
        }

        return revenueStatisticsMapper.insertRevenueStatistics(revenueStatistics);
    }

    /**
     * 修改营收统计
     *
     * @param revenueStatistics 营收统计
     * @return 结果
     */
    @Override
    public int updateRevenueStatistics(RevenueStatistics revenueStatistics)
    {
        return revenueStatisticsMapper.updateRevenueStatistics(revenueStatistics);
    }

    /**
     * 批量删除营收统计
     *
     * @param statIds 需要删除的营收统计主键
     * @return 结果
     */
    @Override
    public int deleteRevenueStatisticsByStatIds(String[] statIds)
    {
        return revenueStatisticsMapper.deleteRevenueStatisticsByStatIds(statIds);
    }

    /**
     * 删除营收统计信息
     *
     * @param statId 营收统计主键
     * @return 结果
     */
    @Override
    public int deleteRevenueStatisticsByStatId(String statId)
    {
        return revenueStatisticsMapper.deleteRevenueStatisticsByStatId(statId);
    }
}
