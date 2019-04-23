package test;

import core.annotation.Autowired;
import core.annotation.Component;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 9:55
 * @Version 1.0
 */
@Component("testService")
public class TestService {
    @Autowired
    private TestDao testDao;
    private int version;

    public TestService() {
    }

    public TestService(TestDao testDao, int version) {
        this.testDao = testDao;
        this.version = version;
    }

    public TestDao getTestDao() {
        return testDao;
    }

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
