package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.Supplier;

/**
 * 供应商管理Service接口
 * 
 * @author qiutuan
 * @date 2023-12-14
 */
public interface ISupplierService 
{
    /**
     * 查询供应商管理
     * 
     * @param supplierId 供应商管理主键
     * @return 供应商管理
     */
    public Supplier selectSupplierBySupplierId(String supplierId);

    /**
     * 查询供应商管理列表
     * 
     * @param supplier 供应商管理
     * @return 供应商管理集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 批量删除供应商管理
     * 
     * @param supplierIds 需要删除的供应商管理主键集合
     * @return 结果
     */
    public int deleteSupplierBySupplierIds(String[] supplierIds);

    /**
     * 删除供应商管理信息
     * 
     * @param supplierId 供应商管理主键
     * @return 结果
     */
    public int deleteSupplierBySupplierId(String supplierId);
}
