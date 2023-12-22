package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.TransactionRecord;

/**
 * 交易记录Mapper接口
 * 
 * @author qiutuan
 * @date 2023-12-17
 */
public interface TransactionRecordMapper 
{
    /**
     * 查询交易记录
     * 
     * @param recordId 交易记录主键
     * @return 交易记录
     */
    public TransactionRecord selectTransactionRecordByRecordId(String recordId);

    /**
     * 查询交易记录列表
     * 
     * @param transactionRecord 交易记录
     * @return 交易记录集合
     */
    public List<TransactionRecord> selectTransactionRecordList(TransactionRecord transactionRecord);

    /**
     * 新增交易记录
     * 
     * @param transactionRecord 交易记录
     * @return 结果
     */
    public int insertTransactionRecord(TransactionRecord transactionRecord);

    /**
     * 修改交易记录
     * 
     * @param transactionRecord 交易记录
     * @return 结果
     */
    public int updateTransactionRecord(TransactionRecord transactionRecord);

    /**
     * 删除交易记录
     * 
     * @param recordId 交易记录主键
     * @return 结果
     */
    public int deleteTransactionRecordByRecordId(String recordId);

    /**
     * 批量删除交易记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTransactionRecordByRecordIds(String[] recordIds);
}
