package org.ChatUI;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature/", plugin="pretty")
public class ChatUiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
