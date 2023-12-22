package com.ruoyi.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.CategoryMapper;
import com.ruoyi.domain.Category;
import com.ruoyi.service.ICategoryService;

/**
 * 商品分类管理Service业务层处理
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询商品分类管理
     * 
     * @param categoryId 商品分类管理主键
     * @return 商品分类管理
     */
    @Override
    public Category selectCategoryByCategoryId(String categoryId)
    {
        return categoryMapper.selectCategoryByCategoryId(categoryId);
    }

    /**
     * 查询商品分类管理列表
     * 
     * @param category 商品分类管理
     * @return 商品分类管理
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增商品分类管理
     * 
     * @param category 商品分类管理
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCategoryId(UUID.fastUUID().toString());
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品分类管理
     * 
     * @param category 商品分类管理
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdatedAt(LocalDateTime.now());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品分类管理
     * 
     * @param categoryIds 需要删除的商品分类管理主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCategoryIds(String[] categoryIds)
    {
        return categoryMapper.deleteCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除商品分类管理信息
     * 
     * @param categoryId 商品分类管理主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCategoryId(String categoryId)
    {
        return categoryMapper.deleteCategoryByCategoryId(categoryId);
    }
}
