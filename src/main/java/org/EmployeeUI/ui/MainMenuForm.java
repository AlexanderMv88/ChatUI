/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.EmployeeUI.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;

import static com.vaadin.ui.UI.getCurrent;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

import org.EmployeeUI.entity.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author a.martyushev
 */
public class MainMenuForm extends Panel implements View {

    private MenuBar menuBar = new MenuBar();
    private VerticalLayout vLayout = new VerticalLayout();
    private Label lbl = new Label();
    private Grid<Employee> chatUserGrid = new Grid<Employee>("Пользователи");

    public void setLblTime(String currentTimeStr) {
        this.lbl.setCaption(currentTimeStr);
    }

    public MainMenuForm() {
        chatUserGrid.addColumn(Employee::getFullName).setCaption("ФИО");

        
        Button addBtn = new Button("Добавить", e ->  addChatUserWindow());
        Button changeBtn = new Button("Изменить", e-> changeChatUserWindow());
        Button deleteBtn = new Button("Удалить", e->removeEmployee());
        HorizontalLayout hLayout = new HorizontalLayout(addBtn,changeBtn,deleteBtn);
        vLayout.addComponents(lbl, chatUserGrid,hLayout);
        this.setContent(vLayout);
    }

    private void removeEmployee() {

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refreshChatUsersGrid();
    }

    private void refreshChatUsersGrid() throws RestClientException {
        RestTemplate restTemplate = (RestTemplate) ((NavigatorUI) getCurrent()).restTemplate;
        ResponseEntity<List<Employee>> chatUsersResponse
                = restTemplate.exchange("http://localhost:8888/api/findAll",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                });
        List<Employee> chatUsers = chatUsersResponse.getBody();
        chatUserGrid.setItems(chatUsers);
    }

    private void addChatUserWindow() throws NullPointerException, IllegalArgumentException {
        EmployeeWindow employeeWindow = new EmployeeWindow();
        getUI().addWindow(employeeWindow);
        employeeWindow.addCloseListener(e1 -> {
            refreshChatUsersGrid();
        });
    }

    private void changeChatUserWindow() throws NullPointerException, IllegalArgumentException {
        Employee employee;

        if (chatUserGrid.asSingleSelect().getValue() != null) {
            employee = chatUserGrid.asSingleSelect().getValue();

            EmployeeWindow employeeWindow = new EmployeeWindow(employee);
            getUI().addWindow(employeeWindow);
            employeeWindow.addCloseListener(e1 -> {
                refreshChatUsersGrid();
            });
        }else{
            new Notification("Внимание",
                    "Выберите пользователя в таблице",
                    Notification.Type.ERROR_MESSAGE, true)
                    .show(Page.getCurrent());
        }
    }
}
