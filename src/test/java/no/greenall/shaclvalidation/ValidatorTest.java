package no.greenall.shaclvalidation;

import static org.junit.Assert.assertTrue;

import no.greenall.shaclvalidation.utils.FileUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.junit.Test;

import java.io.InputStream;

public class ValidatorTest {

    private static final Property SHACL_CONFORMS = new PropertyImpl("http://www.w3.org/ns/shacl#conforms");
    private static final String TEST_001_TTL = "test_001.ttl";
    private static final String VALIDATION_001_SHACL = "validation_001.shacl";
    private static final String TURTLE = "turtle";

    @Test
    public void testOverloadedConstructor() {
        InputStream input = FileUtils.readToStream(TEST_001_TTL);
        InputStream validation = FileUtils.readToStream(VALIDATION_001_SHACL);
        Validator validator = new Validator(input, validation, TURTLE, TURTLE);
        Model testModel = validator.getValidationReportModel();
        testModel.listObjectsOfProperty(SHACL_CONFORMS).forEachRemaining(object -> assertTrue(object.asLiteral().getBoolean()));
    }
}
