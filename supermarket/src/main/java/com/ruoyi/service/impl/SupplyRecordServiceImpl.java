package com.ruoyi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.ruoyi.domain.Product;
import com.ruoyi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.SupplyRecordMapper;
import com.ruoyi.domain.SupplyRecord;
import com.ruoyi.service.ISupplyRecordService;

/**
 * 供货记录Service业务层处理
 * 
 * @author qiutuan
 * @date 2023-12-15
 */
@Service
public class SupplyRecordServiceImpl implements ISupplyRecordService 
{
    @Autowired
    private SupplyRecordMapper supplyRecordMapper;

    @Autowired
    private IProductService productService;

    /**
     * 查询供货记录
     * 
     * @param recordId 供货记录主键
     * @return 供货记录
     */
    @Override
    public SupplyRecord selectSupplyRecordByRecordId(String recordId)
    {
        return supplyRecordMapper.selectSupplyRecordByRecordId(recordId);
    }

    /**
     * 查询供货记录列表
     * 
     * @param supplyRecord 供货记录
     * @return 供货记录
     */
    @Override
    public List<SupplyRecord> selectSupplyRecordList(SupplyRecord supplyRecord)
    {
        return supplyRecordMapper.selectSupplyRecordList(supplyRecord);
    }

    /**
     * 新增供货记录
     * 
     * @param supplyRecord 供货记录
     * @return 结果
     */
    @Override
    public int insertSupplyRecord(SupplyRecord supplyRecord)
    {
        //修改商品库存
        Product product = new Product();
        String productId = supplyRecord.getProductId();
        product.setProductId(productId);
        product.setStock(productService.selectProductByProductId(productId).getStock() + supplyRecord.getSupplyQuantity());
        productService.updateProduct(product);

        LocalDateTime now = LocalDateTime.now();
        supplyRecord.setCreatedAt(now);
        supplyRecord.setUpdatedAt(now);
        supplyRecord.setRecordId(UUID.randomUUID().toString());
        return supplyRecordMapper.insertSupplyRecord(supplyRecord);
    }

    /**
     * 修改供货记录
     * 
     * @param supplyRecord 供货记录
     * @return 结果
     */
    @Override
    public int updateSupplyRecord(SupplyRecord supplyRecord)
    {
        //修改商品库存
        Product product = new Product();
        String productId = supplyRecord.getProductId();
        product.setProductId(productId);
        product.setStock(
                productService.selectProductByProductId(productId).getStock()
                + supplyRecord.getSupplyQuantity()
                - supplyRecordMapper.selectSupplyRecordByRecordId(supplyRecord.getRecordId()).getSupplyQuantity()
        );

        if (product.getStock() < 0) {
            return -1;
        }

        productService.updateProduct(product);

        supplyRecord.setUpdatedAt(LocalDateTime.now());
        return supplyRecordMapper.updateSupplyRecord(supplyRecord);
    }

    /**
     * 批量删除供货记录
     * 
     * @param recordIds 需要删除的供货记录主键
     * @return 结果
     */
    @Override
    public int deleteSupplyRecordByRecordIds(String[] recordIds)
    {
        //修改商品库存
        for (String recordId : recordIds) {
            Product product = new Product();
            String productId = supplyRecordMapper.selectSupplyRecordByRecordId(recordId).getProductId();
            product.setProductId(productId);
            product.setStock(productService.selectProductByProductId(productId).getStock() - supplyRecordMapper.selectSupplyRecordByRecordId(recordId).getSupplyQuantity());
            if (product.getStock() < 0) {
                return -1;
            }
            productService.updateProduct(product);
        }

        return supplyRecordMapper.deleteSupplyRecordByRecordIds(recordIds);
    }

    /**
     * 删除供货记录信息
     * 
     * @param recordId 供货记录主键
     * @return 结果
     */
    @Override
    public int deleteSupplyRecordByRecordId(String recordId)
    {
        //修改商品库存
        Product product = new Product();
        String productId = supplyRecordMapper.selectSupplyRecordByRecordId(recordId).getProductId();
        product.setProductId(productId);
        product.setStock(productService.selectProductByProductId(productId).getStock() - supplyRecordMapper.selectSupplyRecordByRecordId(recordId).getSupplyQuantity());
        if (product.getStock() < 0) {
            return -1;
        }
        productService.updateProduct(product);


        return supplyRecordMapper.deleteSupplyRecordByRecordId(recordId);
    }
}
