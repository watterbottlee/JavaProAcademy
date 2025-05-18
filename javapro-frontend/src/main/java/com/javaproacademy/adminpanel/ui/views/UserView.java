package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.FDOs.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Route("users")
public class UserView extends VerticalLayout {

    private Grid<UserDto> grid = new Grid<>(UserDto.class);

    public UserView() {
        add(grid);
        try {
            List<UserDto> users = fetchUsers();
            grid.setItems(users);
            grid.setColumns("id", "name", "email","password"); // specify columns to show
        } catch (Exception e) {
            e.printStackTrace();
            // handle error (show notification, etc.)
        }
    }

    public List<UserDto> fetchUsers() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/users/getallusers"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        List<UserDto> users = mapper.readValue(response.body(), new TypeReference<List<UserDto>>() {});
        return users;
    }
}
