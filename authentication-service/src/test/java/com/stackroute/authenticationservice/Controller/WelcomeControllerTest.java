package com.stackroute.authenticationservice.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.authenticationservice.controller.Controller;
import com.stackroute.authenticationservice.entity.Role;
import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeControllerTest {


    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private Controller controller;

//User user1 = new User(101,"praveen","praveen9497", Role.student);
//    User user2 = new User(102,"user1","passwprd2", Role.teacher);
//    User user3 = new User(103,"user3","password3", Role.student);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    //    @Test
    // @DisplayName("Post /authenticate Test - Success")
//    public void testRegisterNewUser()  throws  Exception {
//     User user = User.builder().id(101)
//             .userName("praveen")
//             .password("praveen9497")
//             .role(Role.student)
//             .build();
//     //Mockito.when(customUserDetailsService.loadUserByUsername( user)).thenReturn(user);
//        String content = objectWriter.writeValueAsString(user);
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                .post("/authenticate")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(content);
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//               .andExpect(jsonPath("$",notNullValue()))
//               .andExpect(jsonPath("$.userName",is("praveen")))
//                .andExpect(jsonPath("$.password",is("praveen9497")));
//
//    }
    @Test
    @DisplayName("Post /registerUser Test - Success")
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setId(101);
        user.setUserName("praveen");
        user.setPassword("praveen9497");
        user.setRole(Role.student);
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registerUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(404));

    }

    @Test
    @DisplayName("Post /registerUser2 Test - Success")
    public void testRegisterUser2() throws Exception {
        User user = new User();
        user.setId(101);
        user.setUserName("user1");
        user.setPassword("password2");
        user.setRole(Role.teacher);
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registerUser2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(404));

    }
}