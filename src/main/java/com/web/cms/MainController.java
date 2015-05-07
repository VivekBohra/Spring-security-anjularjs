package com.web.cms;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * this is for handle each request of interface.**
 */
@Controller
public class MainController {

    @Autowired
    TaskManagerService taskmanagerservice;

    //for home page(login view)
    @RequestMapping(value = {"/", "/w"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This is default page!");
        model.setViewName("login");
        return model;

    }

    //for main index page
    @RequestMapping(value = {"/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
       
         ModelAndView model = new ModelAndView();
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);
             model.addObject("title", "Spring Security Login Form - Database Authentication");
             model.addObject("message", "This is default page!");
             model.setViewName("index");

          // model.addObject("username", userDetail.getUsername());

        }
        else{
             model.addObject("username", "xyz");
             model.setViewName("403");
        }

      
        return model;
       
       

    }

    //for all user contact list
    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    public @ResponseBody
    Object getAllUsers() {
        List<Contact> tasks = taskmanagerservice.getAllTasks();

        return tasks;

    }

    // login spring security 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/tasks/archive/{ids}", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
    public @ResponseBody
    List<Contact> archiveAllTasks(@PathVariable int ids) {

        taskmanagerservice.archiveTask(ids);

        List<Contact> tasks = taskmanagerservice.getAllTasks();
        return tasks;

    }

    @RequestMapping(value = "/tasks/{id}", method = {RequestMethod.GET, RequestMethod.POST}, headers = "Accept=application/json")
    public @ResponseBody
    List<Contact> getTask(@PathVariable int id) throws ParseException {
        // taskmanagerservice.changeTaskStatus(taskId,taskStatus);		 
        return taskmanagerservice.getTaskById(id);

    }

    @RequestMapping(value = "/tasks/insert/{firstName}/{lastname}/{mobileno}/{email}", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    List<Contact> addTask(@PathVariable String firstName, @PathVariable String lastname, @PathVariable String mobileno, @PathVariable String email) throws ParseException {
        Contact task = new Contact();
        task.setFirst_name(firstName);
        task.setLast_name(lastname);
        task.setEmail(mobileno);
        task.setMobile_number(email);
        taskmanagerservice.addTask(task);
        return taskmanagerservice.getAllTasks();

    }

    @RequestMapping(value = "/tasks/update/{firstName}/{lastname}/{mobileno}/{email}/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    List<Contact> updateTask(@PathVariable String firstName, @PathVariable String lastname, @PathVariable String mobileno, @PathVariable String email, @PathVariable int id) throws ParseException {
        Contact task = new Contact();
        task.setFirst_name(firstName);
        task.setLast_name(lastname);
        task.setEmail(mobileno);
        task.setMobile_number(email);
        task.setId(id);
        taskmanagerservice.updateTask(task);
        return taskmanagerservice.getAllTasks();

    }

}
