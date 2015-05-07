/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Test;

//import java.security.Principal;
import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
    "file:src/main/webapp/WEB-INF/spring-database.xml", "file:src/main/webapp/WEB-INF/spring-security.xml"})

@WebAppConfiguration
public class AuthenticationTests {
        private static final String REQUEST_PARAM_PASSWORD = "password";
        private static final String REQUEST_PARAM_USERNAME = "username";
       
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;
    
  
    
    

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
              
                .addFilters(springSecurityFilterChain)
               
                .build();
    }

    @Test
    public void requiresAuthentication() throws Exception {
        mvc
                .perform(get("/").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

    @Test
    public void testShowPostsForUser_user_is_not_signed_in() throws Exception {

        ResultActions resultActions = mvc.perform(get("/welcome").accept(MediaType.ALL));
        // resultActions.andExpect(status().isOk());
        resultActions.andExpect(redirectedUrl("http://localhost/login"));
        //resultActions.andExpect(view().name("403"));
    }

    @Test
    public void testUserWithAdminRoleLandOnWelcomeUrl() throws Exception {

        mvc.perform(
                post("/j_spring_security_check").param("username",
                        "viveks").param("password",
                        "123456")
                      .with(csrf())
        )
              //  .andExpect(status().is(HttpStatus.FOUND.value()));
               .andExpect(redirectedUrl("/welcome"));

    }

   
    @Test
    public void testgetallContacttask() throws Exception {

        ResultActions resultActions = mvc.perform(get("/showall").accept(MediaType.ALL));
       // resultActions.andExpect(status().isOk());
        resultActions.andDo(print());

    }

    @Test
    public void testaddContacttask() throws Exception {

        ResultActions resultActions = mvc.perform(post("/tasks/insert/name1/its my name/9856985689/namw1@com").accept(MediaType.APPLICATION_JSON)
                                                         .with(csrf().asHeader()));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());

    }
//*** delete by id

    @Test
    public void testdeletecontacttask() throws Exception {

//change the param value for archive  and get the different status for true 200 and not found 302
        ResultActions resultActions = mvc.perform(delete("/tasks/archive/1").accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());

    }
//*** show individual contact task by id

    @Test
    public void testgetcontacttask() throws Exception {

        ResultActions resultActions = mvc.perform(get("/tasks/24").accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());

    }
    
    @Test
    public void login_CredentialsAreCorrect_ShouldRedirectUserToFrontPage() throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String hashedPassword = passwordEncoder.encode("123456");
        CsrfToken csrfToken = new CsrfTokenBuilder()
                .headerName(IntegrationTestConstants.CSRF_TOKEN_HEADER_NAME)
                .requestParameterName(IntegrationTestConstants.CSRF_TOKEN_REQUEST_PARAM_NAME)
                .tokenValue(IntegrationTestConstants.CSRF_TOKEN_VALUE)
                .build();
     

           mvc.perform(post("/j_spring_security_check")
                .param(REQUEST_PARAM_USERNAME,"viveks")
                .param(REQUEST_PARAM_PASSWORD,"123456")
                .param(IntegrationTestConstants.CSRF_TOKEN_REQUEST_PARAM_NAME, IntegrationTestConstants.CSRF_TOKEN_VALUE)
                .sessionAttr(IntegrationTestConstants.CSRF_TOKEN_SESSION_ATTRIBUTE_NAME,csrfToken)
        )
                .andExpect(status().isMovedTemporarily())
                //.andExpect(redirectedUrl("/j_spring_security_check"))
                .andExpect(view().name("index"))
               // .andExpect(forwardedUrl("/WEB-INF/pages/index.jsp"))
                .andDo(print());
           
    }
     public class CsrfTokenBuilder {
 
    private String headerName;
    private String requestParameterName;
    private String tokenValue;
 
    public CsrfTokenBuilder() {
 
    }
 
    public CsrfTokenBuilder headerName(String headerName) {
        this.headerName = headerName;
        return this;
    }
 
    public CsrfTokenBuilder requestParameterName(String requestParameterName) {
        this.requestParameterName = requestParameterName;
        return this;
    }
 
    public CsrfTokenBuilder tokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
        return this;
    }
 
    public CsrfToken build() {
        return new DefaultCsrfToken(headerName, requestParameterName, tokenValue);
    }
}
}

class IntegrationTestConstants {
 
    public static final String CSRF_TOKEN_HEADER_NAME = "X-XSRF-TOKEN";
    public static final String CSRF_TOKEN_REQUEST_PARAM_NAME = "_csrf";
    public static final String CSRF_TOKEN_SESSION_ATTRIBUTE_NAME =
            HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");
    public static final String CSRF_TOKEN_VALUE = "f416e226-bebc-401d-a1ed-f10f47aa9c56";
 
    public enum User {
 
        FACEBOOK_USER("facebook@socialuser.com", null),
        REGISTERED_USER("registered@user.com", "password"),
        TWITTER_USER("twitter@socialuser.com", null);
 
        private String password;
 
        private String username;
 
        private User(String username, String password) {
            this.password = password;
            this.username = username;
        }
 
        public String getPassword() {
            return password;
        }
 
        public String getUsername() {
            return username;
        }
    }
}