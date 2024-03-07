package com.example.gov.nhsa.fuwu.crawler.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @date: 2024/3/7
 */
@Component
public class JavaCallJS {

    @Bean
    public Invocable getJSInvocable() throws FileNotFoundException, ScriptException {
        String filePath = "src/main/resources/static/fuwu_nhsa_gov_cn.js";
        ScriptEngine ee = new ScriptEngineManager()
                .getEngineByName("Nashorn");
        ee.eval(new FileReader(filePath));
        return (Invocable) ee;
    }
}
