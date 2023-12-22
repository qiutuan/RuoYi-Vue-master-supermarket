package com.ruoyi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.domain.RevenueStatistics;
import com.ruoyi.service.IRevenueStatisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 营收统计Controller
 *
 * @author qiutuan
 * @date 2023-12-17
 */
@RestController
@RequestMapping("/supermarket/revenueStatistics")
public class RevenueStatisticsController extends BaseController
{
    @Autowired
    private IRevenueStatisticsService revenueStatisticsService;

    /**
     * 查询营收统计列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:revenueStatistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(RevenueStatistics revenueStatistics)
    {
        startPage();
        List<RevenueStatistics> list = revenueStatisticsService.selectRevenueStatisticsList(revenueStatistics);
        return getDataTable(list);
    }

    /**
     * 导出营收统计列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:revenueStatistics:export')")
    @Log(title = "营收统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RevenueStatistics revenueStatistics)
    {
        List<RevenueStatistics> list = revenueStatisticsService.selectRevenueStatisticsList(revenueStatistics);
        ExcelUtil<RevenueStatistics> util = new ExcelUtil<>(RevenueStatistics.class);
        util.exportExcel(response, list, "营收统计数据");
    }

    /**
     * 获取营收统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('supermarket:revenueStatistics:query')")
    @GetMapping(value = "/{statId}")
    public AjaxResult getInfo(@PathVariable("statId") String statId)
    {
        return success(revenueStatisticsService.selectRevenueStatisticsByStatId(statId));
    }

    /**
     * 新增营收统计
     */
    @PreAuthorize("@ss.hasPermi('supermarket:revenueStatistics:add')")
    @Log(title = "营收统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RevenueStatistics revenueStatistics)
    {
        return toAjax(revenueStatisticsService.insertRevenueStatistics(revenueStatistics));
    }

    /**
     * 修改营收统计
     */
    @PreAuthorize("@ss.hasPermi('supermarket:revenueStatistics:edit')")
    @Log(title = "营收统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RevenueStatistics revenueStatistics)
    {
        return toAjax(revenueStatisticsService.updateRevenueStatistics(revenueStatistics));
    }

    /**
     * 删除营收统计
     */
    @PreAuthorize("@ss.hasPermi('supermarket:revenueStatistics:remove')")
    @Log(title = "营收统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{statIds}")
    public AjaxResult remove(@PathVariable String[] statIds)
    {
        return toAjax(revenueStatisticsService.deleteRevenueStatisticsByStatIds(statIds));
    }
}
