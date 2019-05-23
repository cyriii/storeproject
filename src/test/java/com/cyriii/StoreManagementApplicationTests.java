package com.cyriii;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyriii.dao.InStoreInfoDao;
import com.cyriii.entity.InStoreInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreManagementApplicationTests {

    @Autowired
    private InStoreInfoDao inStoreInfoDao;

//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    private SysUserDao userDao;
//
//    @Test
//    public void contextLoads() throws SQLException {
//        Connection connection = dataSource.getConnection();
//
//        System.out.println(connection);
//
//    }
//
//    @Test
//    public void userDemo(){
//        SysUser user = userDao.selectById(1);
//        System.out.println(user);
//    }

    @Test
    public void inStoreInfoDeme(){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", "65a16152ff2043d8a558d7086eba9e13");
        params.put("name", "火龙果");

        IPage<InStoreInfoVO> iPage = inStoreInfoDao.page(new Page<>(1, 2), params);
        System.out.println(iPage.getRecords());
    }
}
