package beans.factory.impl;

import core.annotation.AnnotationAttributes;
import core.annotation.Component;
import core.io.ClassPathResource;
import core.type.AnnotationMetadata;
import core.type.classreading.MetadataReader;
import core.type.classreading.SimpleMetadataReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:22
 * @Version 1.0
 */
public class MetaDataReaderTest {
    @Test
    void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("test/TestService.class");

        MetadataReader reader = new SimpleMetadataReader(resource);

        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        assertEquals("testService", attributes.get("value"));


        assertFalse(amd.isAbstract());
        assertFalse(amd.isFinal());
        assertEquals("test.TestService", amd.getClassName());

    }
}
