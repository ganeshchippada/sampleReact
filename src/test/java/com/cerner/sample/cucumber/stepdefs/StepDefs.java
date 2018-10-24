package com.cerner.sample.cucumber.stepdefs;

import com.cerner.sample.SpringBootSampleApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = SpringBootSampleApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
