package com.init.configure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.init.configure.model.FormatterModel;
import com.init.configure.service.FormatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dashboard")
public class FormatterController {

    private final String REQUEST_PREFIX_PAGE = "format/request/formatter";
    private final String RESPONSE_PREFIX_PAGE = "format/response/formatter";

    @Autowired
    private FormatterService jsonFormatterService;

    @GetMapping
    public String showDashboard() {
        return "dashboard";
    }

    @GetMapping("/formatter_json_beautify")
    public String showJson() {
        return REQUEST_PREFIX_PAGE + "_json_beautify";
    }

    @PostMapping("/json/beautify")
    public String jsonBeautify( @RequestParam() String jsonInput, Model jsonResult) throws JsonProcessingException {
        FormatterModel model = new FormatterModel();
        model.setInputData(jsonInput);
        jsonFormatterService.beautify(model);
        jsonResult.addAttribute("jsonResult", model);
        return RESPONSE_PREFIX_PAGE + "_json_beautify";
    }

    @GetMapping("/formatter_json_unescape")
    public String jsonUnescape() {
        return REQUEST_PREFIX_PAGE + "_json_unescape";
    }

    @PostMapping("/json/unescape")
    public String jsonUnescape( @RequestParam() String jsonInput, Model jsonResult) throws JsonProcessingException {
        FormatterModel model = new FormatterModel();
        model.setInputData(jsonInput);
        jsonFormatterService.unescape(model);
        jsonResult.addAttribute("jsonResult", model);
        return RESPONSE_PREFIX_PAGE + "_json_unescape";
    }

    @GetMapping("/formatter_json_single_line")
    public String singleLine() {
        return REQUEST_PREFIX_PAGE + "_json_single_line";
    }

    @PostMapping("/json/single_line")
    public String jsonSingleLine(@RequestParam() String jsonInput, Model jsonResult) throws JsonProcessingException {
        FormatterModel model = new FormatterModel();
        model.setInputData(jsonInput);
        jsonFormatterService.singleLine(model);
        jsonResult.addAttribute("jsonResult", model);
        return RESPONSE_PREFIX_PAGE + "_json_single_line";
    }

    @GetMapping("/formatter_xml_beautify")
    public String xmlFormatter() {
        return REQUEST_PREFIX_PAGE + "_xml_beautify";
    }

    @PostMapping("/xml/beautify")
    public String xmlBeautify(@RequestParam() String xmlInput, Model xmlResult) {
        FormatterModel model = new FormatterModel();
        model.setInputData(xmlInput);
        jsonFormatterService.xmlBeautify(model);
        xmlResult.addAttribute("xmlResult", model);
        return RESPONSE_PREFIX_PAGE + "_xml_beautify";
    }

    @GetMapping("/formatter_yaml_beautify")
    public String groovyFormatter() {
        return REQUEST_PREFIX_PAGE + "_yaml_beautify";
    }

    @PostMapping("/yaml/beautify")
    public String groovyBeautify(@RequestParam() String yamlInput, Model yamlResult){
        FormatterModel model = new FormatterModel();
        model.setInputData(yamlInput);
        jsonFormatterService.beautifyYaml(model);
        yamlResult.addAttribute("yamlResult", model);
        return RESPONSE_PREFIX_PAGE + "_yaml_beautify";
    }
}
