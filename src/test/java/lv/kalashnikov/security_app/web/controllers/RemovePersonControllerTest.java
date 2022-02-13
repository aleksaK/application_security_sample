package lv.kalashnikov.security_app.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemovePersonControllerTest {

    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testAccessIsForbiddenForUserRole() throws Exception {
        mvc.perform(get("/admin/removePerson")
                .contentType(MediaType.ALL)).andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testAccessIsGrantedForAdminRole() throws Exception {
        mvc.perform(get("/admin/removePerson")
                .contentType(MediaType.ALL)).andExpect(status().isOk());
    }

    @WithAnonymousUser
    @Test
    public void testAccessIsForbiddenForAnonymousUser() throws Exception {
        mvc.perform(get("/admin/removePerson")
                .contentType(MediaType.ALL)).andExpect(status().isFound());
    }

}