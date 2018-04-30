package org.ChatUI;

import com.codeborne.selenide.Condition;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;

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
