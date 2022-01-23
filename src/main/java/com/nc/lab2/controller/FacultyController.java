package com.nc.lab2.controller;


import com.nc.lab2.dao.FacultyDAO;
import com.nc.lab2.dao.GroupDAO;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Student;
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
import java.util.HashMap;
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FacultyController {

    public static Logger log = LogManager.getLogger();

    private static List<Faculty> facultyList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private FacultyDAO facultyDAO;

    //    ------------------------------------------------- Не реализованные методы

    @RequestMapping(value = "/viewAllFacultys", method = RequestMethod.GET)
    public ModelAndView viewAllGroups() {
        facultyList = facultyDAO.getAllFaculty();
        log.info("Log inside method View Faculty (Test)");
        return new ModelAndView("viewAllFacultys", "list", facultyList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public ModelAndView addFaculty() {
        ModelAndView modelAndView = new ModelAndView("addFaculty");
        modelAndView.addObject("command", new Faculty());
        return modelAndView;
    }

    @RequestMapping(value = "/saveFaculty", method = RequestMethod.POST)
    public ModelAndView saveFaculty(@ModelAttribute Faculty faculty) {
        facultyDAO.addFaculty(faculty);
        return new ModelAndView("redirect:/viewAllFacultys");
    }


}
