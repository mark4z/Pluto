package src;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 9:55
 * @Version 1.0
 */
public class TestService {
    private TestDao testDao;
    private int version;

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
