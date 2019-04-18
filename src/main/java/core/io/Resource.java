package core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 14:31
 * @Version 1.0
 */
public interface Resource {
    public InputStream getInputStream() throws IOException;

    public String getDescription();
}
