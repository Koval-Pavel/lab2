package com.nc.lab2.controller;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GroupController {

    public static Logger log = LogManager.getLogger();

    private static List<Group> groupList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/viewAllGroups", method = RequestMethod.GET)
    public ModelAndView viewAllGroups() {
        groupList = groupDAO.getAllGroup();
        System.out.println(groupList + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("Log inside method VIEW STUDENT (Test)");
        return new ModelAndView("viewAllGroups", "list", groupList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public ModelAndView addGroup() {
        ModelAndView modelAndView = new ModelAndView("addGroup");
        modelAndView.addObject("command",new Group());
        HashMap< Integer, String> awailableFaculty = new HashMap<>();
        for (Faculty temp: groupDAO.getAwailFaculty()) {
            awailableFaculty.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("name1",awailableFaculty);

        return modelAndView;
    }

    @RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Group group) {
        groupDAO.addGroup(group);
        return new ModelAndView("redirect:/viewAllGroups");
    }

//    //    ------------------------------------------------- Не реализованные методы

    @RequestMapping(value = "/getGroupStudents", method = RequestMethod.GET)
    public ModelAndView getGroupStudents() {
        ModelAndView modelAndView = new ModelAndView("getGroupStudents");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteGroup/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGroup(@PathVariable int id) {
//        infoMessage = studentDAO.removeStudent(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/viewAllGroups");
        return modelAndView;
    }

    @RequestMapping(value = "/editGroup/editSave", method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute Group group) {
        groupDAO.saveGroup(group);
        return new ModelAndView("redirect:/viewAllGroupss");
    }

    @RequestMapping(value = "/editGroup/{id}", method = RequestMethod.GET)
    public ModelAndView editGroup(@PathVariable int id) {
        return new ModelAndView("editGroup", "command", findGroupInList(groupList, id));
    }

    public Group findGroupInList(List<Group> groupList, int id) {
        Group group = null;
        for (Group temp: groupList) {
            if (temp.getId() == id) {group = temp;}
        }
        return group;
    }

}
