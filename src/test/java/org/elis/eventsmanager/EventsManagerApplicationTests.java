package org.elis.eventsmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elis.eventsmanager.dto.request.LoginRequest;
import org.elis.eventsmanager.dto.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration(classes = EventsManagerApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventsManagerApplicationTests {

   @Autowired
   private WebApplicationContext context;

   private MockMvc mock;

   @BeforeEach
    public void createMock(){
       mock = MockMvcBuilders.webAppContextSetup(context).build();
   }

   @Test
   public void testLogin1() throws Exception{
       mock.perform(MockMvcRequestBuilders.post("/all/user/login")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().is(400));
   }

   @Test
   public void testLogin2() throws Exception{
      LoginRequest lr = new LoginRequest();
      lr.setEmail("andrea.gatta@admin.it");
      lr.setPassword("Andrea03!");
      String json = new ObjectMapper().writeValueAsString(lr);
      mock.perform(MockMvcRequestBuilders.post("/all/user/login")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
              .andExpect(MockMvcResultMatchers.status().is(200));
   }

   @Test
   public void testRegistrazione() throws Exception{
      SignupRequest request = new SignupRequest();
      request.setNome("Giacomo");
      request.setCognome("Bongiovanni");
      request.setEmail("giacomo.bongiovanni@customer.it");
      request.setPassword("Giacomo03!");
      request.setPassword2("Giacomo03!");
      String json = new ObjectMapper().writeValueAsString(request);
      MockHttpServletRequestBuilder builders = MockMvcRequestBuilders.post("/all/user/signup")
              .contentType(MediaType.APPLICATION_JSON)
              .content(json);
      mock.perform(builders).andExpect(MockMvcResultMatchers.status().is(200));
   }

}
