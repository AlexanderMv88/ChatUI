/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ChatUI.ui;

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

import org.ChatUI.entity.ChatUser;
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
    private Grid<ChatUser> chatUserGrid = new Grid<ChatUser>("Пользователи");

    public void setLblTime(String currentTimeStr) {
        this.lbl.setCaption(currentTimeStr);
    }

    public MainMenuForm() {
        chatUserGrid.addColumn(ChatUser::getFullName).setCaption("ФИО");

        
        Button addBtn = new Button("Добавить", e ->  addChatUserWindow());
        Button changeBtn = new Button("Изменить", e-> changeChatUserWindow());
        Button deleteBtn = new Button("Удалить");
        HorizontalLayout hLayout = new HorizontalLayout(addBtn,changeBtn,deleteBtn);
        vLayout.addComponents(lbl, chatUserGrid,hLayout);
        this.setContent(vLayout);

    }



    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refreshChatUsersGrid();

    }

    private void refreshChatUsersGrid() throws RestClientException {
        RestTemplate restTemplate = (RestTemplate) ((NavigatorUI) getCurrent()).restTemplate;
        ResponseEntity<List<ChatUser>> chatUsersResponse
                = restTemplate.exchange("http://localhost:8888/api/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ChatUser>>() {
                });
        List<ChatUser> chatUsers = chatUsersResponse.getBody();
        chatUserGrid.setItems(chatUsers);
    }

    private void addChatUserWindow() throws NullPointerException, IllegalArgumentException {
        ChatUserWindow chatUserWindow = new ChatUserWindow();
        getUI().addWindow(chatUserWindow);
        chatUserWindow.addCloseListener(e1 -> {
            refreshChatUsersGrid();
        });
    }

    private void changeChatUserWindow() throws NullPointerException, IllegalArgumentException {
        ChatUser chatUser;

        if (chatUserGrid.asSingleSelect().getValue() != null) {
            chatUser = chatUserGrid.asSingleSelect().getValue();

            ChatUserWindow chatUserWindow = new ChatUserWindow(chatUser);
            getUI().addWindow(chatUserWindow);
            chatUserWindow.addCloseListener(e1 -> {
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
