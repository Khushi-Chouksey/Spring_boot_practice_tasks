package com.example.Assigment2.controller;

import com.example.Assigment2.contoller.UserController;
import com.example.Assigment2.model.User;
import com.example.Assigment2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User dummyUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dummyUser = new User();
        dummyUser.setUsername("khushi");
        dummyUser.setPassword("pass123");
    }

    @Test
    void testLogin() {
        when(userService.verify(dummyUser)).thenReturn("Login Successful");

        String response = userController.login(dummyUser);
        assertEquals("Login Successful", response);
        verify(userService, times(1)).verify(dummyUser);
    }

    @Test
    void testRegister() {
        when(userService.save(dummyUser)).thenReturn(dummyUser);

        User result = userController.register(dummyUser);
        assertNotNull(result);
        assertEquals("khushi", result.getUsername());
        verify(userService, times(1)).save(dummyUser);
    }
}
