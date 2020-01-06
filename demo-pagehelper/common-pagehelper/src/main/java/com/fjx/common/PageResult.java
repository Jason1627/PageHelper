package com.fjx.common;/*
 @author Jason
 @DESCRIPTION 
 @create 2020-01-04
*/

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.List;

@NoArgsConstructor // 无参的构造函数
@Accessors(chain = true)//  有参的构造函数
@Data
public class PageResult<T> {
    private Long total;// 总条数
    private Integer totalPage;// 总页数
    private List<T> items;// 当前页数据

    // 带总条数和当前页数的的构造参数
    public PageResult(long total, List<T> list) {
        this.total = total;
        this.items = list;
    }
}
