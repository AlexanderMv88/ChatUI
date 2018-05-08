/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.EmployeeUI.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Component
@SpringUI
@Theme("valo")
public class NavigatorUI extends UI {

    private MainMenuForm mainMenuForm = new MainMenuForm();
    Navigator navigator;

    public static final String MAIN_MENU_FORM = "mainMenuForm";
    public RestTemplate restTemplate = new RestTemplate();
   
    @Autowired
    public NavigatorUI(){
        
    }

    @Override
    protected void init(VaadinRequest request) {
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("Alexander", "12345"));
        navigator = new Navigator(this, this);
        navigator.addView(MAIN_MENU_FORM, mainMenuForm);
        navigator.navigateTo(MAIN_MENU_FORM);

    }

    public Navigator getNavigator() {
        return navigator;
    }

  





}
