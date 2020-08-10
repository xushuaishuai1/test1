package com.xtm.test1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtm.test1.aop.RedisLock;
import com.xtm.test1.base.BaseServiceImpl;
import com.xtm.test1.mapper.SProjectMapper;
import com.xtm.test1.pojo.SProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
public class ProjectService extends BaseServiceImpl<SProjectMapper, SProject> {

    @Autowired
    private SProjectMapper sProjectMapper;

    //验证重复
    public boolean check(String projectName,String id){
//        QueryWrapper<SProject> queryWrapper = new QueryWrapper<SProject>();
//        if(StringUtils.isNotEmpty(id)){
//            queryWrapper.ne("id",id);
//        }
//        queryWrapper.eq("projectName",projectName);
//        List<SProject> projectList = sProjectMapper.selectList(queryWrapper);
//        if(projectList != null && projectList.size() >0){
//            return true;
//        }
        return false;
    }



    /**
     * 编辑
     * @param sProject
     */
    @RedisLock(value = "redisLockRegistry",time = 60)
    public void editProject(SProject sProject){
        this.updateById_(sProject);
    }

    public void editByWrapper(UpdateWrapper updateWrapper){
        boolean b = this.update(updateWrapper);
        if(b){
            System.out.println("success");
        }
        System.out.println("error");
    }


}
