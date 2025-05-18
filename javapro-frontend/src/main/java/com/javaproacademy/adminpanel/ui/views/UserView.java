package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.FDOs.User;
import com.javaproacademy.adminpanel.ui.services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route("users")
public class UserView extends VerticalLayout {

    private Grid<User> grid = new Grid<>(User.class);

    public UserView() {
        add(grid);
        try {
            List<User> users = new UserService().getAllUsers();
            grid.setItems(users);
            grid.setColumns("id", "name", "email","password"); // specify columns to show
        } catch (Exception e) {
            e.printStackTrace();
            // handle error (show notification, etc.)
        }
    }
}
