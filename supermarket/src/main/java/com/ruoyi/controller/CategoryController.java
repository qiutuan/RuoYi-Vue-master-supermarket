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
import com.ruoyi.domain.Category;
import com.ruoyi.service.ICategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品分类管理Controller
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@RestController
@RequestMapping("/supermarket/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('supermarket:category:export')")
    @Log(title = "商品分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品分类管理数据");
    }

    /**
     * 获取商品分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('supermarket:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") String categoryId)
    {
        return success(categoryService.selectCategoryByCategoryId(categoryId));
    }

    /**
     * 新增商品分类管理
     */
    @PreAuthorize("@ss.hasPermi('supermarket:category:add')")
    @Log(title = "商品分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品分类管理
     */
    @PreAuthorize("@ss.hasPermi('supermarket:category:edit')")
    @Log(title = "商品分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品分类管理
     */
    @PreAuthorize("@ss.hasPermi('supermarket:category:remove')")
    @Log(title = "商品分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable String[] categoryIds)
    {
        return toAjax(categoryService.deleteCategoryByCategoryIds(categoryIds));
    }
}
