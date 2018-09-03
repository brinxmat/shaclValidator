package no.greenall.shaclvalidation;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.topbraid.shacl.validation.ValidationUtil;

import java.io.InputStream;
import java.io.StringWriter;

class Validator {

    private static final String JSONLD = "jsonld";
    private static final String NTRIPLES = "ntriples";
    private static final String RDFXML = "rdfxml";
    private static final String TEXT = "text";
    private static final String TURTLE = "turtle";

    private static final Model reportModel = ModelFactory.createDefaultModel();

    Validator(InputStream input, InputStream validation, String inputFormat, String validationFormat) {
        Model inputModel = ModelFactory.createDefaultModel();
        Model validationModel = ModelFactory.createDefaultModel();
        RDFDataMgr.read(inputModel, input, getLang(inputFormat));
        RDFDataMgr.read(validationModel, validation, getLang(validationFormat));
        reportModel.add(ValidationUtil.validateModel(inputModel, validationModel, true).getModel());
    }


    Model getValidationReportModel() {
        return reportModel;
    }

    String getValidationReport(String reportFormat) {
        StringWriter stringWriter = new StringWriter();

        if (reportFormat.equals(TEXT)) {
            stringWriter.append("Not implemented");
        } else {
            RDFDataMgr.write(stringWriter, reportModel, getLang(reportFormat));
        }

        return stringWriter.toString();
    }

    private Lang getLang(String input) {
        Lang lang = null;
        switch (input) {
            case JSONLD:
                lang = Lang.JSONLD;
                break;
            case NTRIPLES:
                lang = Lang.NTRIPLES;
                break;
            case RDFXML:
                lang = Lang.RDFXML;
                break;
            case TURTLE:
            default:
                lang = Lang.TURTLE;
                break;
        }
        return lang;
    }
}
