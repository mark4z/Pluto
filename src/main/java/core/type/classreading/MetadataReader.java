package core.type.classreading;

import core.io.Resource;
import core.type.AnnotationMetadata;
import core.type.ClassMetadata;


/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:28
 * @Version 1.0
 */
public interface MetadataReader {
    Resource getResource();

    ClassMetadata getClassMetaData();

    AnnotationMetadata getAnnotationMetadata();

}
