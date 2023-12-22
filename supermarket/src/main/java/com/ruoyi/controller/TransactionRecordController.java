package com.ruoyi.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.RevenueStatistics;
import com.ruoyi.service.IProductService;
import com.ruoyi.service.IRevenueStatisticsService;
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
import com.ruoyi.domain.TransactionRecord;
import com.ruoyi.service.ITransactionRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易记录Controller
 * 
 * @author qiutuan
 * @date 2023-12-17
 */
@RestController
@RequestMapping("/supermarket/transactionRecord")
public class TransactionRecordController extends BaseController
{
    @Autowired
    private ITransactionRecordService transactionRecordService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IRevenueStatisticsService revenueStatisticsService;

    /**
     * 查询交易记录列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransactionRecord transactionRecord)
    {
        startPage();
        List<TransactionRecord> list = transactionRecordService.selectTransactionRecordList(transactionRecord);
        return getDataTable(list);
    }

    /**
     * 导出交易记录列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:export')")
    @Log(title = "交易记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TransactionRecord transactionRecord)
    {
        List<TransactionRecord> list = transactionRecordService.selectTransactionRecordList(transactionRecord);
        ExcelUtil<TransactionRecord> util = new ExcelUtil<TransactionRecord>(TransactionRecord.class);
        util.exportExcel(response, list, "交易记录数据");
    }

    /**
     * 获取交易记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") String recordId)
    {
        return success(transactionRecordService.selectTransactionRecordByRecordId(recordId));
    }

//    /**
//     * 新增交易记录
//     */
//    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:add')")
//    @Log(title = "交易记录", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody TransactionRecord transactionRecord)
//    {
//        return toAjax(transactionRecordService.insertTransactionRecord(transactionRecord));
//    }

    /**
     * 新增交易记录
     */
    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:add')")
    @Log(title = "交易记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArrayList<TransactionRecord> transactionRecord)
    {
        for (TransactionRecord record : transactionRecord) {
            if (transactionRecordService.insertTransactionRecord(record) == 0) {
                return toAjax(0);
            }

            //修改产品库存
            Product product = productService.selectProductByProductId(record.getProductId());
            product.setStock(product.getStock() - record.getQuantity());
            productService.updateProduct(product);

            //添加营收统计
            RevenueStatistics revenueStatistics = new RevenueStatistics();
            revenueStatistics.setTotalSales(record.getTransactionAmount());

            revenueStatisticsService.insertRevenueStatistics(revenueStatistics);


        }

        return toAjax(1);
    }

    /**
     * 修改交易记录
     */
    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:edit')")
    @Log(title = "交易记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransactionRecord transactionRecord)
    {
        return toAjax(transactionRecordService.updateTransactionRecord(transactionRecord));
    }

    /**
     * 删除交易记录
     */
    @PreAuthorize("@ss.hasPermi('supermarket:transactionRecord:remove')")
    @Log(title = "交易记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable String[] recordIds)
    {
        return toAjax(transactionRecordService.deleteTransactionRecordByRecordIds(recordIds));
    }
}
