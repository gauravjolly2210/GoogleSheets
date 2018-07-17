package com.qait.automation.GoogleApi;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features ="Feature"
		,glue= {"Steps"}
		)
public class Runner extends AbstractTestNGCucumberTests{
}
