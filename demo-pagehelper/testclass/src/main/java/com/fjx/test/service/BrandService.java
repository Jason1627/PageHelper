package com.fjx.test.service;/*
 @author Jason
 @DESCRIPTION 
 @create 2020-01-04
*/

import com.fjx.common.PageResult;
import com.fjx.test.mapper.BrandMapper;
import com.fjx.test.pojo.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * @author jason
     * @DESCRIPTION:根据查询条件分页并排序查询品牌信息
     * @params: [key, page, rows, sortBy, desc]
     * @return: com.leyou.common.pojo.PageResult<com.leyou.item.pojo.Brand>
     * @Date: 2019-12-21 10:49
     * @Modified By:
     */

    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据name模糊查询，或者根据首字母查询，判断key是否为空（搜索条件）
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        // 判断是否有排序的字段（根据什么排序啊）
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
