package com.nc.lab2.controller;


import com.nc.lab2.dao.SubjectDAO;
import com.nc.lab2.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SubjectController {

    public static Logger log = LogManager.getLogger();

    private static List<Subject> subjectList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private SubjectDAO subjectDAO;

    //    ------------------------------------------------- Не реализованные методы


    @RequestMapping(value = "/viewAllSubjects", method = RequestMethod.GET)
    public ModelAndView viewAllSubjects() {
        subjectList = subjectDAO.getAllSubject();
        log.info("Log inside method VIEW STUDENT (Test)");
        return new ModelAndView("viewAllGroups", "list", subjectList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public ModelAndView addSubject() {
        ModelAndView modelAndView = new ModelAndView("addSubject");
//        modelAndView.addObject("command",new Group());
//        HashMap< Integer, String> awailableFaculty = new HashMap<>();
//        for (Faculty temp: groupDAO.getAwailFaculty()) {
//            awailableFaculty.put(temp.getId(), temp.getName());
//        }
//        modelAndView.addObject("name1",awailableFaculty);

        return modelAndView;
    }

    @RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
    public ModelAndView saveSubject(@ModelAttribute Subject subject) {
        subjectDAO.addSubject(subject);
        return new ModelAndView("redirect:/viewAllSubjects");
    }

}
