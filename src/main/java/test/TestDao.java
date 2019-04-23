package test;

import core.annotation.Component;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 9:55
 * @Version 1.0
 */
@Component
public class TestDao {
    private String str;
    private boolean isMybatis;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isMybatis() {
        return isMybatis;
    }

    public void setMybatis(boolean mybatis) {
        isMybatis = mybatis;
    }
}
