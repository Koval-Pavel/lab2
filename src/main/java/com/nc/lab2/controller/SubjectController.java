package com.nc.lab2.controller;


import com.nc.lab2.dao.SubjectDAO;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SubjectController {

    public static Logger log = LogManager.getLogger();
    private  int idForEdit = 0;
    private static String infoMessageSubject;
    private static List<Subject> subjectList = new ArrayList();

    @Autowired
    private SubjectDAO subjectDAO;

    @RequestMapping(value = "/viewAllSubjects", method = RequestMethod.GET)
    public ModelAndView viewAllSubjects() {
        subjectList = subjectDAO.getAllSubject();
        log.info("Log inside method VIEW STUDENT (Test)");
        return new ModelAndView("subjectsView/viewAllSubjects", "list", subjectList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public ModelAndView addSubject() {
        ModelAndView modelAndView = new ModelAndView("subjectsView/addSubject");
        modelAndView.addObject("command",new Subject());
        return modelAndView;
    }




    @RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
    public ModelAndView saveSubject(@ModelAttribute Subject subject) {
        subjectDAO.addSubject(subject);
        return new ModelAndView("redirect:/viewAllSubjects");
    }


    @RequestMapping(value = "/editSubject/editSaveSubject", method = RequestMethod.POST)
    public ModelAndView editSaveSubject(@ModelAttribute Subject subject) {
        subject.setId(idForEdit);
        infoMessageSubject = subjectDAO.saveSubject(subject);
        return new ModelAndView("redirect:/viewAllSubjects");
    }

    @RequestMapping(value = "/editSubject/{id}", method = RequestMethod.GET)
    public ModelAndView editSubject(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("subjectsView/editSubject");
        modelAndView.addObject("command",findSubjectInList(subjectList, id));
        return modelAndView;
    }

    @RequestMapping(value = "/deleteSubject/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSubject(@PathVariable int id) {
        infoMessageSubject = subjectDAO.removeSubject(id);
        return new ModelAndView("redirect:/viewAllSubjects");
    }

    public Subject findSubjectInList(List<Subject> subjectList, int id) {
        Subject subject = null;
        for (Subject temp: subjectList) {
            if (temp.getId() == id) {subject = temp;}
        }
        return subject;
    }




}
