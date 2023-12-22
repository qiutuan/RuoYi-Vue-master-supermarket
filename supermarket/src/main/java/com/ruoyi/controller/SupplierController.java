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
import com.ruoyi.domain.Supplier;
import com.ruoyi.service.ISupplierService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商管理Controller
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@RestController
@RequestMapping("/supermarket/supplier")
public class SupplierController extends BaseController
{
    @Autowired
    private ISupplierService supplierService;

    /**
     * 查询供应商管理列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(Supplier supplier)
    {
        startPage();
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商管理列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplier:export')")
    @Log(title = "供应商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Supplier supplier)
    {
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        ExcelUtil<Supplier> util = new ExcelUtil<Supplier>(Supplier.class);
        util.exportExcel(response, list, "供应商管理数据");
    }

    /**
     * 获取供应商管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") String supplierId)
    {
        return success(supplierService.selectSupplierBySupplierId(supplierId));
    }

    /**
     * 新增供应商管理
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplier:add')")
    @Log(title = "供应商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Supplier supplier)
    {
        return toAjax(supplierService.insertSupplier(supplier));
    }

    /**
     * 修改供应商管理
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplier:edit')")
    @Log(title = "供应商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Supplier supplier)
    {
        return toAjax(supplierService.updateSupplier(supplier));
    }

    /**
     * 删除供应商管理
     */
    @PreAuthorize("@ss.hasPermi('supermarket:supplier:remove')")
    @Log(title = "供应商管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable String[] supplierIds)
    {
        return toAjax(supplierService.deleteSupplierBySupplierIds(supplierIds));
    }
}
