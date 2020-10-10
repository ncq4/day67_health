package com.itheima.test;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.utils.SMSUtils;
import org.junit.Ignore;
import org.junit.Test;

public class SMSTest {

    @Ignore
    @Test
    public void sendCode() throws ClientException {
        SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, "13538372834", "hello itheima");
    }

    @Ignore
    @Test
    public void sendnotice() throws ClientException {
        SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, "15615805170", "2020-09-10");
    }
}
