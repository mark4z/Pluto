package core.type.classreading;

import core.io.ClassPathResource;
import core.io.Resource;
import core.type.AnnotationMetadata;
import core.type.ClassMetadata;
import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:33
 * @Version 1.0
 */
public class SimpleMetadataReader implements MetadataReader {
    private final Resource resource;
    private final ClassMetadata classMetadata;
    private final AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) throws IOException {
        InputStream is=new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;
        try{
            classReader=new ClassReader(is);
        }finally {
            is.close();
        }
        AnnotationMetadataReadingVisitor visitor=new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor,ClassReader.SKIP_DEBUG);
        this.annotationMetadata=visitor;
        this.classMetadata=visitor;
        this.resource=resource;
    }

    public Resource getResource() {
        return this.resource;
    }

    public ClassMetadata getClassMetaData() {
        return this.classMetadata;
    }

    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
