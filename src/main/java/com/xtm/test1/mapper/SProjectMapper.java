package com.xtm.test1.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtm.test1.pojo.SProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 项目、产品表 Mapper 接口
 * </p>
 *
 * @author xtm
 * @since 2020-07-08
 */
@Repository
public interface SProjectMapper extends BaseMapper<SProject> {

}
