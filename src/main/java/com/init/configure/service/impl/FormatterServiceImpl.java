package com.init.configure.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.init.configure.model.FormatterModel;
import com.init.configure.service.FormatterService;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.StringReader;
import java.io.StringWriter;

@Service
public class FormatterServiceImpl implements FormatterService {

    @Override
    public String beautify(FormatterModel formatterModel) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            Object json = mapper.readValue(formatterModel.getInputData(), Object.class);
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            formatterModel.setOutputData(writer.writeValueAsString(json));
            formatterModel.setResult("success");
        } catch (JsonProcessingException exception) {
            formatterModel.setResult("fail");
            formatterModel.setOutputData(exception.getMessage());
            return "";
        }
        return formatterModel.getOutputData();
    }

    @Override
    public String unescape(FormatterModel formatterModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String result = objectMapper.readValue("\"" + formatterModel.getInputData() + "\"", String.class);
            formatterModel.setOutputData(result);
            formatterModel.setResult("success");
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            formatterModel.setResult("fail");
            formatterModel.setOutputData(e.getMessage());
            return "Error unescaping JSON: " + e.getMessage();
        }
    }

    @Override
    public void singleLine(FormatterModel formatterModel) throws JsonProcessingException {
        JSONObject object = new JSONObject(this.beautify(formatterModel));
        formatterModel.setOutputData( object.toString().replaceAll("\\s+", ""));
    }

    @Override
    public String xmlBeautify(FormatterModel formatterModel) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new StringReader(formatterModel.getInputData()));

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());

            StringWriter stringWriter = new StringWriter();
            xmlOutput.output(document, stringWriter);
            formatterModel.setOutputData(stringWriter.toString());
            formatterModel.setResult("success");
            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
            formatterModel.setResult("fail");
            return "Error beautifying XML: " + e.getMessage();
        }
    }

    @Override
    public String beautifyYaml(FormatterModel formatterModel) {
        Yaml yaml = new Yaml();
        Object yamlObject = yaml.load(formatterModel.getInputData());

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml prettyYaml = new Yaml(options);
        formatterModel.setOutputData(prettyYaml.dump(yamlObject));
        formatterModel.setResult("success");
        return prettyYaml.dump(yamlObject);
        }
}
