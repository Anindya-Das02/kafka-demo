package in.das.app.kafka.functional;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber-reports/Cucumber.json, junit:target/cucumber-reports/Cucumber.xml, html:target/cucumber-reports/Cucumber.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "in.das.app.kafka.functional")
@ExcludeTags("skip")
public class CucumberTest {
}
