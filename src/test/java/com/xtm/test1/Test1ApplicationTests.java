package com.xtm.test1;


import com.xtm.test1.mapper.SProjectMapper;
import com.xtm.test1.pojo.SProject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test1Application.class)
public class Test1ApplicationTests {

    @Autowired
    SProjectMapper sProjectMapper;


    @Test
    public void testUpdateByIdFail() {
        SProject u = sProjectMapper.selectById(5);
        u.setRemark("ssssssss");
        int preVersion = u.getVersion();
        sProjectMapper.updateById(u);

        SProject userUpdate = new SProject();
        userUpdate.setId(u.getId());
        userUpdate.setRemark("update");
        userUpdate.setVersion(preVersion);
        sProjectMapper.updateById(userUpdate);
    }

}
