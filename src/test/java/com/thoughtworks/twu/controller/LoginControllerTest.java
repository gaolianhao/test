package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: mgomez
 * Date: 21/10/2012
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class LoginControllerTest {

    @Test
    public void shouldReturnAMessgageWhenPasswordIsWrong(){
        UserService mockUserService = mock(UserService.class);
        User expectedUser = new User();
        expectedUser.setName("Mr Mustache");
        expectedUser.setEmail("mustache@abc.com");
        expectedUser.setPassword("123");
        when(mockUserService.getUserByEmail(expectedUser.getEmail())).thenReturn(expectedUser);

        LoginController loginController = new LoginController(mockUserService);
        ModelAndView modelAndView = loginController.login(expectedUser.getEmail(), "wrong password");

        assertThat((String)modelAndView.getModelMap().get("message"), equalTo("Authentication Failed"));
    }

    @Test
    public void shouldReturnAMessgageWhenEmailIsNotFound(){
        UserService mockUserService = mock(UserService.class);
        User expectedUser = new User();
        expectedUser.setName("Mr Mustache");
        expectedUser.setEmail("mustache@abc.com");
        expectedUser.setPassword("123");
        final String wrongEmail = "wrong email";
        when(mockUserService.getUserByEmail(expectedUser.getEmail())).thenReturn(expectedUser);

        LoginController loginController = new LoginController(mockUserService);
        ModelAndView modelAndView = loginController.login(wrongEmail, expectedUser.getPassword());

        assertThat((String)modelAndView.getModelMap().get("message"), equalTo("Authentication Failed"));
    }

}
