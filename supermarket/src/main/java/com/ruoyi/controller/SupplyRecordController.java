package com.ruoyi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.service.IProductService;
import com.ruoyi.service.ISupplierService;
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
import com.ruoyi.domain.SupplyRecord;
import com.ruoyi.service.ISupplyRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供货记录Controller
 * 
 * @author qiutuan
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/supermarket/supplyRecord")
public class SupplyRecordController extends BaseController
{
    @Autowired
    private ISupplyRecordService supplyRecordService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IProductService productService;

    /**
     * 查询供货记录列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplyRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyRecord supplyRecord)
    {
        startPage();
        List<SupplyRecord> list = supplyRecordService.selectSupplyRecordList(supplyRecord);

        list.forEach(SupplyRecord->{
            SupplyRecord.setProductName(productService.selectProductByProductId(SupplyRecord.getProductId()).getProductName());
            SupplyRecord.setSupplierName(supplierService.selectSupplierBySupplierId(SupplyRecord.getSupplierId()).getSupplierName());
        });

        return getDataTable(list);
    }

    /**
     * 导出供货记录列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplyRecord:export')")
    @Log(title = "供货记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplyRecord supplyRecord)
    {
        List<SupplyRecord> list = supplyRecordService.selectSupplyRecordList(supplyRecord);
        ExcelUtil<SupplyRecord> util = new ExcelUtil<SupplyRecord>(SupplyRecord.class);
        util.exportExcel(response, list, "供货记录数据");
    }

    /**
     * 获取供货记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplyRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") String recordId)
    {
        SupplyRecord supplyRecord = supplyRecordService.selectSupplyRecordByRecordId(recordId);
        supplyRecord.setProductName(productService.selectProductByProductId(supplyRecord.getProductId()).getProductName());
        supplyRecord.setSupplierName(supplierService.selectSupplierBySupplierId(supplyRecord.getSupplierId()).getSupplierName());
        return success(supplyRecord);
    }

    /**
     * 新增供货记录
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplyRecord:add')")
    @Log(title = "供货记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyRecord supplyRecord)
    {
        return toAjax(supplyRecordService.insertSupplyRecord(supplyRecord));
    }

    /**
     * 修改供货记录
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplyRecord:edit')")
    @Log(title = "供货记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyRecord supplyRecord)
    {
        return toAjax(supplyRecordService.updateSupplyRecord(supplyRecord));
    }

    /**
     * 删除供货记录
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplyRecord:remove')")
    @Log(title = "供货记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable String[] recordIds)
    {
        return toAjax(supplyRecordService.deleteSupplyRecordByRecordIds(recordIds));
    }
}
