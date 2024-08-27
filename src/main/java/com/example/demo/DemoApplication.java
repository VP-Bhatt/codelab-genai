package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import com.google.cloud.ServiceOptions;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@RestController
class HelloController {

    String projectId = ServiceOptions.getDefaultProjectId();

    @GetMapping("/")
    public String getFacts(@RequestParam(defaultValue = "dog") String animal) throws IOException {
        String prompt = "Give me 10 fun facts about " + animal + ". Return this as html without backticks.";

        VertexAI vertexAI = new VertexAI(projectId, null);
        GenerativeModel model = new GenerativeModel("gemini-1.5-flash", vertexAI);

        GenerateContentResponse response = model.generateContent(prompt);
        return ResponseHandler.getText(response);
    }

}
