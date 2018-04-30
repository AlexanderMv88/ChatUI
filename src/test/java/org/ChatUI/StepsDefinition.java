package org.ChatUI;

import com.codeborne.selenide.Condition;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepsDefinition {


    @Если("^я прошел по ссылке '(.+)'")
    public void goToLink(String link) {
        open(link);
    }

    @То("^откроется форма с таблицей '(.+)'")
    public void checkOnLoad(String fieldName1){
        $(byText(fieldName1)).should(Condition.appear);
    }

    @И("кнопками:$")
    public void checkOnLoadWindowWithButtons(List<String> buttons){
        for (String button:buttons){
            $(byText(button)).should(Condition.appear);
        }
    }
}
