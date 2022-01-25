package com.nc.lab2.controller;


import com.nc.lab2.dao.FacultyDAO;
import com.nc.lab2.model.Faculty;
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
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FacultyController {

    public static Logger log = LogManager.getLogger();
    private  int idForEdit = 0;
    private static String infoMessageFaculty;
    private static List<Faculty> facultyList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private FacultyDAO facultyDAO;

    @RequestMapping(value = "/viewAllFacultys", method = RequestMethod.GET)
    public ModelAndView viewAllGroups() {
        facultyList = facultyDAO.getAllFaculty();
        log.info("Log inside method View Faculty (Test)");
        return new ModelAndView("facultysView/viewAllFacultys", "list", facultyList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public ModelAndView addFaculty() {
        ModelAndView modelAndView = new ModelAndView("facultysView/addFaculty");
        modelAndView.addObject("command", new Faculty());
        return modelAndView;
    }

    @RequestMapping(value = "/saveFaculty", method = RequestMethod.POST)
    public ModelAndView saveFaculty(@ModelAttribute Faculty faculty) {
        facultyDAO.addFaculty(faculty);
        return new ModelAndView("redirect:/viewAllFacultys");
    }

    @RequestMapping(value = "/editFaculty/editSaveFaculty", method = RequestMethod.POST)
    public ModelAndView editSaveFaculty(@ModelAttribute Faculty faculty) {
        faculty.setId(idForEdit);
        infoMessageFaculty = facultyDAO.saveFaculty(faculty);
        return new ModelAndView("redirect:/viewAllFacultys");
    }

    @RequestMapping(value = "/editFaculty/{id}", method = RequestMethod.GET)
    public ModelAndView editFaculty(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("facultysView/editFaculty");
        modelAndView.addObject("command",findFacultyInList(facultyList, id));
        return modelAndView;
    }

    @RequestMapping(value = "/deleteFaculty/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFaculty(@PathVariable int id) {
        infoMessageFaculty = facultyDAO.removeFaculty(id);
        return new ModelAndView("redirect:/viewAllFacultys");
    }

    public Faculty findFacultyInList(List<Faculty> facultyList, int id) {
        Faculty faculty = null;
        for (Faculty temp: facultyList) {
            if (temp.getId() == id) {faculty = temp;}
        }
        return faculty;
    }


}
