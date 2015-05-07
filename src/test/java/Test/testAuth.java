/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

/**
 *
 * @author Vivek
 */
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public abstract  class testAuth {
     public static final String CSRF_TOKEN_HEADER_NAME = "X-CSRF-TOKEN";
    public static final String CSRF_TOKEN_REQUEST_PARAM_NAME = "_csrf";
    public static final String CSRF_TOKEN_SESSION_ATTRIBUTE_NAME =
            HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");
    public static final String CSRF_TOKEN_VALUE = "f416e226-bebc-401d-a1ed-f10f47aa9c56";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    protected MockMvc mockMvc;

    public abstract void setup() throws Exception;

    protected HttpSession login() throws Exception {
        return mockMvc.perform(post("/j_spring_security_check")
                .param("username", USERNAME)
                .param("password", PASSWORD)
                .param(CSRF_TOKEN_REQUEST_PARAM_NAME, CSRF_TOKEN_VALUE)
                .sessionAttr(CSRF_TOKEN_SESSION_ATTRIBUTE_NAME, csrf()))
                .andExpect(redirectedUrl("/protected"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    protected CsrfToken csrf() {
        return new CsrfTokenBuilder()
                .headerName(CSRF_TOKEN_HEADER_NAME)
                .requestParameterName(CSRF_TOKEN_REQUEST_PARAM_NAME)
                .tokenValue(CSRF_TOKEN_VALUE)
                .build();
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
