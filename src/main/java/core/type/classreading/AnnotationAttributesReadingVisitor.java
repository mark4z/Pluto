package core.type.classreading;

import core.annotation.AnnotationAttributes;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:05
 * @Version 1.0
 */
public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
    private final String annotationType;
    private final Map<String, AnnotationAttributes> attributesMap;
    AnnotationAttributes attributes = new AnnotationAttributes();


    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    @Override
    public final void visitEnd() {
        this.attributesMap.put(this.annotationType, this.attributes);
    }

    @Override
    public void visit(String annotationName, Object attributeValue) {
        this.attributes.put(annotationName, attributeValue);
    }
}
