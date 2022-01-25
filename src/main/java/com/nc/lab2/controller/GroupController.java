package com.nc.lab2.controller;


import com.nc.lab2.dao.GroupDAO;
import com.nc.lab2.model.Faculty;
import com.nc.lab2.model.Group;
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
    private  int idForEdit = 0;
    private static String infoMessageGroup;


    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/viewAllGroups", method = RequestMethod.GET)
    public ModelAndView viewAllGroups() {
        groupList = groupDAO.getAllGroup();
        log.info("Log inside method VIEW STUDENT (Test)");
        ModelAndView modelAndView = new ModelAndView("groupsView/viewAllGroups");
        modelAndView.addObject("massage",infoMessageGroup);
        infoMessageGroup = null;
        modelAndView.addObject("list", groupList);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public ModelAndView addGroup() {
        ModelAndView modelAndView = new ModelAndView("groupsView/addGroup");
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
        infoMessageGroup = groupDAO.addGroup(group);
        return new ModelAndView("redirect:/viewAllGroups");
    }

    @RequestMapping(value = "/deleteGroup/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGroup(@PathVariable int id) {
        infoMessageGroup = groupDAO.removeGroup(id);
        return new ModelAndView("redirect:/viewAllGroups");
    }

    @RequestMapping(value = "/editGroup/editSaveGroup", method = RequestMethod.POST)
    public ModelAndView editSaveGroup(@ModelAttribute Group group) {
        group.setId(idForEdit);
        infoMessageGroup = groupDAO.saveGroup(group);
        return new ModelAndView("redirect:/viewAllGroups");
    }

    @RequestMapping(value = "/editGroup/{id}", method = RequestMethod.GET)
    public ModelAndView editGroup(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("groupsView/editGroup");
        modelAndView.addObject("command",findGroupInList(groupList, id));
        HashMap< Integer, String> awailableFaculty = new HashMap<>();
        for (Faculty temp: groupDAO.getAwailFaculty()) {
            awailableFaculty.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("name1",awailableFaculty);
        return modelAndView;
    }

    public Group findGroupInList(List<Group> groupList, int id) {
        Group group = null;
        for (Group temp: groupList) {
            if (temp.getId() == id) {group = temp;}
        }
        return group;
    }


//    ---------------------------------------------------
    @RequestMapping(value = "/getGroupStudents", method = RequestMethod.GET)
    public ModelAndView getGroupStudents() {
        return new ModelAndView("groupsView/getGroupStudents");
    }

}
