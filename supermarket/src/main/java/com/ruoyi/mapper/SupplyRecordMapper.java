package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.SupplyRecord;

/**
 * 供货记录Mapper接口
 * 
 * @author qiutuan
 * @date 2023-12-15
 */
public interface SupplyRecordMapper 
{
    /**
     * 查询供货记录
     * 
     * @param recordId 供货记录主键
     * @return 供货记录
     */
    public SupplyRecord selectSupplyRecordByRecordId(String recordId);

    /**
     * 查询供货记录列表
     * 
     * @param supplyRecord 供货记录
     * @return 供货记录集合
     */
    public List<SupplyRecord> selectSupplyRecordList(SupplyRecord supplyRecord);

    /**
     * 新增供货记录
     * 
     * @param supplyRecord 供货记录
     * @return 结果
     */
    public int insertSupplyRecord(SupplyRecord supplyRecord);

    /**
     * 修改供货记录
     * 
     * @param supplyRecord 供货记录
     * @return 结果
     */
    public int updateSupplyRecord(SupplyRecord supplyRecord);

    /**
     * 删除供货记录
     * 
     * @param recordId 供货记录主键
     * @return 结果
     */
    public int deleteSupplyRecordByRecordId(String recordId);

    /**
     * 批量删除供货记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyRecordByRecordIds(String[] recordIds);
}
