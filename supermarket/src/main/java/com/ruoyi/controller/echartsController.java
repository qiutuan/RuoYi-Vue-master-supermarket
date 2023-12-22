package com.ruoyi.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.domain.Category;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.RevenueStatistics;
import com.ruoyi.dot.echartsDto;
import com.ruoyi.service.ICategoryService;
import com.ruoyi.service.IProductService;
import com.ruoyi.service.IRevenueStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.success;

/**
 * 数据可视化
 * @author qiutuan
 * @date 2023/12/20
 */
@RestController
@RequestMapping("/supermarket/echarts")
public class echartsController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IRevenueStatisticsService revenueStatisticsService;

    @GetMapping
    public AjaxResult getEchartsMsg(){

        echartsDto echartsDto = new echartsDto();

        // 产品数量
        List<Category> categories = categoryService.selectCategoryList(new Category());
        echartsDto.setCategoryList(categories);

        List<Integer> list = new ArrayList<>();
        categories.forEach(category -> {
            Product product = new Product();
            product.setCategoryId(category.getCategoryId());
            list.add(productService.selectProductList(product).size());
        });
        echartsDto.setCategoryCountList(list);


        //销售额
        List<RevenueStatistics> revenueStatistics = revenueStatisticsService.selectRevenueStatisticsList(new RevenueStatistics());
        //拿到最近一周的销售额 ， 按顺序排列
        revenueStatistics.sort(Comparator.comparing(RevenueStatistics::getDate));
        revenueStatistics = revenueStatistics.subList(revenueStatistics.size() - 15, revenueStatistics.size());

        echartsDto.setRevenueStatisticsList(revenueStatistics);

        return success(echartsDto);
    }
}
