package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.Category;

/**
 * 商品分类管理Service接口
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
public interface ICategoryService
{
    /**
     * 查询商品分类管理
     * 
     * @param categoryId 商品分类管理主键
     * @return 商品分类管理
     */
    public Category selectCategoryByCategoryId(String categoryId);

    /**
     * 查询商品分类管理列表
     * 
     * @param category 商品分类管理
     * @return 商品分类管理集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品分类管理
     * 
     * @param category 商品分类管理
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品分类管理
     * 
     * @param category 商品分类管理
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除商品分类管理
     * 
     * @param categoryIds 需要删除的商品分类管理主键集合
     * @return 结果
     */
    public int deleteCategoryByCategoryIds(String[] categoryIds);

    /**
     * 删除商品分类管理信息
     * 
     * @param categoryId 商品分类管理主键
     * @return 结果
     */
    public int deleteCategoryByCategoryId(String categoryId);
}
