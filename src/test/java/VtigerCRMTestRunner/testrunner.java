package VtigerCRMTestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Feature",
		glue="vTigerCRM.stepdefinations",
		plugin= {"pretty", "html:target/cucumber-html-report.html","json:target/cucumber.json"}
		,dryRun=false,
		monochrome = true
		)





public class testrunner {

}
