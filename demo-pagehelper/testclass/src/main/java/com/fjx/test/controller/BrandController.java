package com.fjx.test.controller;/*
 @author Jason
 @DESCRIPTION 
 @create 2020-01-04
*/

import com.fjx.common.PageResult;
import com.fjx.test.pojo.Brand;
import com.fjx.test.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * @author Jason
     * @DESCRIPTION:根据查询条件分页并排序查询品牌信息
     * @params: [key, page, rows, sortBy, desc]
     * @return: org.springframework.http.ResponseEntity<com.fjx.common.PageResult < com.fjx.test.pojo.Brand>>
     * @Date: 2020-01-06 14:52
     * @Modified By:
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            // key 是搜索框中的过滤条件，required = false表示不确定搜索框是否有信息
            @RequestParam(value = "key", required = false) String key,
            // 默认访问当前页
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            // 每页条数显示默认为5
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            // 根据什么排序
            @RequestParam(value = "sortBy", required = false) String sortBy,
            // 排序规则
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        // 判断当前页数据是否为空，为空则提示notfound
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        // 返回数据
        return ResponseEntity.ok(result);
    }
}
