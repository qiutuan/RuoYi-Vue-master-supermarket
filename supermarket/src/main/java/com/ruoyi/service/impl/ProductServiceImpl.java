package com.ruoyi.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.ProductMapper;
import com.ruoyi.domain.Product;
import com.ruoyi.service.IProductService;

/**
 * 商品Service业务层处理
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@Service
public class ProductServiceImpl implements IProductService 
{
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询商品
     * 
     * @param productId 商品主键
     * @return 商品
     */
    @Override
    public Product selectProductByProductId(String productId)
    {
        return productMapper.selectProductByProductId(productId);
    }

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {
        product.setProductId(UUID.randomUUID().toString());
        product.setStock(0L);
        return productMapper.insertProduct(product);
    }

    /**
     * 修改商品
     * 
     * @param product 商品
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除商品
     * 
     * @param productIds 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteProductByProductIds(String[] productIds)
    {
        return productMapper.deleteProductByProductIds(productIds);
    }

    /**
     * 删除商品信息
     * 
     * @param productId 商品主键
     * @return 结果
     */
    @Override
    public int deleteProductByProductId(String productId)
    {
        return productMapper.deleteProductByProductId(productId);
    }
}
