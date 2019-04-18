package core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 14:38
 * @Version 1.0
 */
public class FileSystemResource implements Resource {
    private final String path;
    private final File file;

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getDescription() {
        return "file[" + this.file.getAbsoluteFile() + "]";
    }
}
