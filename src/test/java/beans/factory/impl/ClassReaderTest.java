package beans.factory.impl;

import core.annotation.AnnotationAttributes;
import core.io.ClassPathResource;
import core.type.classreading.AnnotationMetadataReadingVisitor;
import core.type.classreading.ClassMetadataReadingVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @Author: Marcus
 * @Date: 2019/4/23 10:50
 * @Version 1.0
 */
public class ClassReaderTest {
    @Test
    public void testGetClasMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("test/TestService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        assertFalse(visitor.isAbstract());
        assertFalse(visitor.isInterface());
        assertFalse(visitor.isFinal());
        assertEquals("test.TestService", visitor.getClassName());
        assertEquals("java.lang.Object", visitor.getSuperClassName());
        assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotation() throws Exception{
        ClassPathResource resource = new ClassPathResource("test/TestService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "core.annotation.Component";
        assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        assertEquals("testService", attributes.get("value"));

    }
}
