package com.init.configure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.init.configure.model.FormatterModel;

public interface FormatterService {

    String beautify(FormatterModel formatterModel) throws JsonProcessingException;
    String unescape(FormatterModel formatterModel) throws JsonProcessingException;
    void singleLine(FormatterModel formatterModel) throws JsonProcessingException;
    String xmlBeautify(FormatterModel formatterModel);
    String beautifyYaml(FormatterModel formatterModel);

}
