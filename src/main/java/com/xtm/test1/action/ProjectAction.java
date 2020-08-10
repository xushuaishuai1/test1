package com.xtm.test1.action;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xtm.test1.base.CommonResult;
import com.xtm.test1.mapper.SProjectMapper;
import com.xtm.test1.pojo.SProject;
import com.xtm.test1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("")
@Controller
public class ProjectAction {

    @Autowired
    ProjectService projectService;
    @Autowired
    private SProjectMapper sProjectMapper;

    /**
     * bean 编辑保存
     * @return
     */
    @RequestMapping("/")
    @ResponseBody
    public CommonResult editSave(HttpServletRequest request) {
        SProject sProject = new SProject();
        sProject.setId(5);
        sProject.setRemark("tttttt");
        projectService.editProject(sProject);


//        for(int i=0; i<10; i++){
//            new Thread("" + i){
//                public void run(){
//                    sProject.setRemark(Thread.currentThread().toString());
//                    projectService.editProject(sProject);
//                }
//            }.start();
//        }
        return CommonResult.ok("编辑成功！");
    }

}
