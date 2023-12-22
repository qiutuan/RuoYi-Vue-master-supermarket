package com.ruoyi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.service.ICategoryService;
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
import com.ruoyi.domain.Product;
import com.ruoyi.service.IProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@RestController
@RequestMapping("/supermarket/product")
public class ProductController extends BaseController
{
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(Product product)
    {
        startPage();
        List<Product> list = productService.selectProductList(product);

        list.forEach(item -> {
            item.setCategoryName(categoryService.selectCategoryByCategoryId(item.getCategoryId()).getCategoryName());
        });

        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:product:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Product product)
    {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('supermarket:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") String productId)
    {
        return success(productService.selectProductByProductId(productId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('supermarket:product:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Product product)
    {
        return toAjax(productService.insertProduct(product));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('supermarket:product:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Product product)
    {
        return toAjax(productService.updateProduct(product));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('supermarket:product:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable String[] productIds)
    {
        return toAjax(productService.deleteProductByProductIds(productIds));
    }
}
