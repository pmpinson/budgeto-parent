package org.pmp.budgeto.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pmp.budgeto.domain.account.Account;
import org.pmp.budgeto.domain.account.AccountConfig;
import org.pmp.budgeto.domain.account.AccountHelper;
import org.pmp.budgeto.test.config.WebITConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebITConfig.class})
@ActiveProfiles("test")
@WebAppConfiguration
public class ApiControllerIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void get() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/").accept("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[0].rel").value("self"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[0].href").value("http://localhost"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[1].rel").value("account"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[1].href").value("http://localhost/account"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[2].rel").value("budget"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[2].href").value("http://localhost/budget"));
    }

}
