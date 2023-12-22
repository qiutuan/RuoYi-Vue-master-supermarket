package com.ruoyi.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.SupplierMapper;
import com.ruoyi.domain.Supplier;
import com.ruoyi.service.ISupplierService;

/**
 * 供应商管理Service业务层处理
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
@Service
public class SupplierServiceImpl implements ISupplierService 
{
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询供应商管理
     * 
     * @param supplierId 供应商管理主键
     * @return 供应商管理
     */
    @Override
    public Supplier selectSupplierBySupplierId(String supplierId)
    {
        return supplierMapper.selectSupplierBySupplierId(supplierId);
    }

    /**
     * 查询供应商管理列表
     * 
     * @param supplier 供应商管理
     * @return 供应商管理
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier)
    {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier)
    {
        supplier.setSupplierId(UUID.fastUUID().toString());
        supplier.setCreatedAt(LocalDateTime.now());
        supplier.setUpdatedAt(LocalDateTime.now());
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier)
    {
        supplier.setUpdatedAt(LocalDateTime.now());
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 批量删除供应商管理
     * 
     * @param supplierIds 需要删除的供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteSupplierBySupplierIds(String[] supplierIds)
    {
        return supplierMapper.deleteSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商管理信息
     * 
     * @param supplierId 供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteSupplierBySupplierId(String supplierId)
    {
        return supplierMapper.deleteSupplierBySupplierId(supplierId);
    }
}
