package com.nc.lab2.controller;

import com.nc.lab2.dao.GroupDAO;
import com.nc.lab2.model.*;

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

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Controller of Group model.
 * @author  Pavel Koval
 */
@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GroupController {

    /** Id of Group from database. */
    private  int idForEdit = 0;

    /** Info message field. */
    private static String infoMessageGroup;

    /** INFO message */
    private final String INFO_SQL_ERRO = "Some problem with Group SQL request.";

    /** Field with list of all Groups from database. */
    private static List<Group> groupList = new ArrayList();

    /** Field with list of all Students in group from database. */
    private static List<Student> groupStudentList = new ArrayList();

    /** Field with Data Access Object for Group. */
    @Autowired
    private GroupDAO groupDAO;

    /**
     *  Method that get list of all required Groups and
     *  passes them to viewAllGroup.jsp page.
     * @return ModelAndView object with list of Groups.
     */
    @RequestMapping(value = "/viewAllGroups", method = RequestMethod.GET)
    public ModelAndView viewAllGroups() {
        groupList = groupDAO.getAllGroup();
        infoMessageGroup = groupList == null? INFO_SQL_ERRO: infoMessageGroup;
        log.info(infoMessageGroup);
        ModelAndView modelAndView = new ModelAndView("groupsView/viewAllGroups");
        modelAndView.addObject("massage",infoMessageGroup);
        infoMessageGroup = null;
        modelAndView.addObject("list", groupList);
        return modelAndView;
    }

    /**
     *  Method that create new object of Group and
     *  passes it to addFaculty.jsp page for filling empty fields.
     * @return ModelAndView object with new Faculty.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public ModelAndView addGroup() {
        ModelAndView modelAndView = new ModelAndView("groupsView/addGroup");
        modelAndView.addObject("command",new Group());
        HashMap< Integer, String> availableFaculty = new HashMap<>();
        for (Faculty temp: groupDAO.getAwailFaculty()) {
            availableFaculty.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("availableFaculty",availableFaculty);
        return modelAndView;
    }

    /**
     * Method that save new Group.
     * @param group new object of Group filled with param's from addGroup.jsp
     * @return ModelAndView redirection to viewAllGroups()
     */
    @RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Group group) {
        infoMessageGroup = groupDAO.addGroup(group);
        log.info("INFO message from saveStudent() - " + infoMessageGroup);
        return new ModelAndView("redirect:/viewAllGroups");
    }

    /**
     *  Method that get selected Group object and
     *  passes it to editGroup.jsp page for edit.
     * @param id - unique identifier of each Group object.
     * @return ModelAndView object with selected Group.
     */
    @RequestMapping(value = "/editGroup/{id}", method = RequestMethod.GET)
    public ModelAndView editGroup(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("groupsView/editGroup");
        modelAndView.addObject("command",findGroupInList(groupList, id));
        HashMap< Integer, String> awailableFaculty = new HashMap<>();
        for (Faculty temp: groupDAO.getAwailFaculty()) {
            awailableFaculty.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("awailableFaculty",awailableFaculty);
        return modelAndView;
    }

    /**
     * Method that save edited Group object.
     * @param group edited object of Group filled with param's from editGroup.jsp
     * @return ModelAndView object with redirection to viewAllGroups()
     */
    @RequestMapping(value = "/editGroup/editSaveGroup", method = RequestMethod.POST)
    public ModelAndView editSaveGroup(@ModelAttribute Group group) {
        group.setId(idForEdit);
        infoMessageGroup = groupDAO.saveGroup(group);
        log.info("INFO message from editSaveGroup() - " + infoMessageGroup);
        return new ModelAndView("redirect:/viewAllGroups");
    }

    /**
     * Method that delete selected Group.
     * @param id unique identifier of each Group object.
     * @return ModelAndView object with redirection to viewAllGroups()
     */
    @RequestMapping(value = "/deleteGroup/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGroup(@PathVariable int id) {
        infoMessageGroup = groupDAO.removeGroup(id);
        log.info("INFO message from deleteGroup()" + id +" '/deleteGroup/{id}' = " + infoMessageGroup);
        return new ModelAndView("redirect:/viewAllGroups");
    }

    /**
     * Method that find selected Group from all Group list.
     * @param groupList list of Groups.
     * @param id unique identifier of each Group object.
     * @return selected Group Object.
     */
    public Group findGroupInList(List<Group> groupList, int id) {
        Group group = null;
        for (Group temp: groupList) {
            if (temp.getId() == id) {group = temp;}
        }
        return group;
    }

    /**
     *  Method that get students list from selected Group and
     *  passes it to groupStudentList.jsp page.
     * @param id unique identifier of each Group object.
     * @return ModelAndView object with list of students.
     */
    @RequestMapping(value = "/groupInfo/{id}", method = RequestMethod.GET)
    public ModelAndView groupInfo(@PathVariable int id) {
        groupStudentList = groupDAO.getGroupStudents(id);
        ModelAndView modelAndView = new ModelAndView("groupsView/groupStudentList");
        modelAndView.addObject("groupStudentList", groupStudentList);
        return modelAndView;
    }
}
