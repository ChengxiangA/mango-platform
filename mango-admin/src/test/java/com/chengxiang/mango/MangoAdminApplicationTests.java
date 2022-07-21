package com.chengxiang.mango;

import com.chengxiang.mango.entity.SysLoginLog;
import com.chengxiang.mango.mapper.SysLoginLogMapper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.Impl.SysLoginLogServiceImpl;
import com.chengxiang.mango.util.IPUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class MangoAdminApplicationTests {

    @Test
    public void testLogWrite() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", "login");
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(1);
        pageRequest.setPageSize(10);
        pageRequest.setParams(params);
        PageResult page = new SysLoginLogServiceImpl().findPage(pageRequest);
        List<SysLoginLog> content = page.getContent();
        for(SysLoginLog sysLoginLog: content) {
            System.out.println(sysLoginLog);
        }
    }
}
