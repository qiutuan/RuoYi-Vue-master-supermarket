package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.Product;

/**
 * 商品Mapper接口
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
public interface ProductMapper 
{
    /**
     * 查询商品
     * 
     * @param productId 商品主键
     * @return 商品
     */
    public Product selectProductByProductId(String productId);

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除商品
     * 
     * @param productId 商品主键
     * @return 结果
     */
    public int deleteProductByProductId(String productId);

    /**
     * 批量删除商品
     * 
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByProductIds(String[] productIds);
}
