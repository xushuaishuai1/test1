package com.xtm.test1.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl{
    /**
     * 重写mybatis plus 的updateById
     * mybatis plus乐观锁不会报错，重写此方法用于报出自定义异常
     * @param entity
     * @return
     */
    public void updateById_(T entity) {
        boolean flag = SqlHelper.retBool(this.getBaseMapper().updateById(entity));
        if(!flag){
            System.out.println("数据已更新，请刷新页面重试!");
          throw new CommonException("数据已更新，请刷新页面重试!");
        }
    }
}
