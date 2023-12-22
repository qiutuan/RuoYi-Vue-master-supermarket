package com.ruoyi.dot;

import com.ruoyi.domain.*;
import lombok.Data;

import java.util.List;

@Data
public class echartsDto {

    private List<Category> categoryList;

    private List<Integer> categoryCountList;

    private List<Product> productList;

    private List<Supplier> supplierList;

    private List<SupplyRecord> supplyRecordList;

    private List<RevenueStatistics> revenueStatisticsList;

}
