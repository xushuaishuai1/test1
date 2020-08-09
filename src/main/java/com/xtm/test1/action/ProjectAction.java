package com.xtm.test1.action;

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
    public String editSave(HttpServletRequest request) {
        try {
            SProject sProject = this.projectService.getById(5);
            sProject.setRemark("tttttt");
            projectService.editProject(sProject);
            for(int i=0; i<2; i++){
                new Thread("" + i){
                    public void run(){
                        sProject.setRemark(Thread.currentThread().toString());
                        projectService.editProject(sProject);
                    }
                }.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "success";
    }


}
