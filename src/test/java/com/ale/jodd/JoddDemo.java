package com.ale.jodd;

import jodd.io.FileUtil;
import jodd.util.ClassUtil;
import jodd.util.MathUtil;
import jodd.util.TimeUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
  * @author alewu
  * @date 2018/3/23
  * @description JoddDemo测试
  */
public class JoddDemo {

    @Test
    public void test01(){
        LocalDateTime.now();
        Date date = TimeUtil.toDate(LocalDateTime.now());
        LocalDateTime localDateTime = TimeUtil.fromDate(new Date());
    }
}
