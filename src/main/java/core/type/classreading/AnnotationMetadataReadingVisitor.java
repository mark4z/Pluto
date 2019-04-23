package core.type.classreading;

import core.annotation.AnnotationAttributes;
import core.type.AnnotationMetadata;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 12:36
 * @Version 1.0
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {
    private final Set<String> annotationSet = new LinkedHashSet<String>();
    private final Map<String, AnnotationAttributes> attributeMap=new HashMap<String, AnnotationAttributes>();

    public AnnotationMetadataReadingVisitor() {
    }
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible){
        String className= Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
    }
    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }

    @Override
    public boolean hasSuperClass() {
        return false;
    }
}
