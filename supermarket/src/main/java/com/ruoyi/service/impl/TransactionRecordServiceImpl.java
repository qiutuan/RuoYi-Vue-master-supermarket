package com.ruoyi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.domain.Product;
import com.ruoyi.mapper.RevenueStatisticsMapper;
import com.ruoyi.service.IProductService;
import com.ruoyi.service.IRevenueStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.TransactionRecordMapper;
import com.ruoyi.domain.TransactionRecord;
import com.ruoyi.service.ITransactionRecordService;

/**
 * 交易记录Service业务层处理
 * 
 * @author qiutuan
 * @date 2023-12-17
 */
@Service
public class TransactionRecordServiceImpl implements ITransactionRecordService 
{
    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

    @Autowired
    private IProductService productService;

    @Autowired
    private RevenueStatisticsMapper revenueStatisticsMapper;

    /**
     * 查询交易记录
     * 
     * @param recordId 交易记录主键
     * @return 交易记录
     */
    @Override
    public TransactionRecord selectTransactionRecordByRecordId(String recordId)
    {
        return transactionRecordMapper.selectTransactionRecordByRecordId(recordId);
    }

    /**
     * 查询交易记录列表
     * 
     * @param transactionRecord 交易记录
     * @return 交易记录
     */
    @Override
    public List<TransactionRecord> selectTransactionRecordList(TransactionRecord transactionRecord)
    {
        List<TransactionRecord> transactionRecords = transactionRecordMapper.selectTransactionRecordList(transactionRecord);

        transactionRecords.forEach(record ->{
            Product product = productService.selectProductByProductId(record.getProductId());
            record.setProductName(product.getProductName());
            record.setImagePath(product.getImagePath());
        });

        return transactionRecords;
    }

    /**
     * 新增交易记录
     * 
     * @param transactionRecord 交易记录
     * @return 结果
     */
    @Override
    public int insertTransactionRecord(TransactionRecord transactionRecord)
    {
        BigDecimal transactionAmount = transactionRecord.getTransactionAmount();

//        revenueStatisticsMapper.selectRevenueStatisticsList()

        if (transactionAmount.compareTo(BigDecimal.ZERO) < 0){
            return 0;
        }

        transactionRecord.setRecordId(UUID.randomUUID().toString().replace("-",""));
        transactionRecord.setTransactionTime(new java.util.Date());

        return transactionRecordMapper.insertTransactionRecord(transactionRecord);
    }

    /**
     * 修改交易记录
     * 
     * @param transactionRecord 交易记录
     * @return 结果
     */
    @Override
    public int updateTransactionRecord(TransactionRecord transactionRecord)
    {
        return transactionRecordMapper.updateTransactionRecord(transactionRecord);
    }

    /**
     * 批量删除交易记录
     * 
     * @param recordIds 需要删除的交易记录主键
     * @return 结果
     */
    @Override
    public int deleteTransactionRecordByRecordIds(String[] recordIds)
    {
        return transactionRecordMapper.deleteTransactionRecordByRecordIds(recordIds);
    }

    /**
     * 删除交易记录信息
     * 
     * @param recordId 交易记录主键
     * @return 结果
     */
    @Override
    public int deleteTransactionRecordByRecordId(String recordId)
    {
        return transactionRecordMapper.deleteTransactionRecordByRecordId(recordId);
    }
}
