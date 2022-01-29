package com.nc.lab2.controller;


import com.nc.lab2.dao.SubjectDAO;
import com.nc.lab2.model.Subject;
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

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Controller of Subject model.
 * @author  Pavel Koval
 */
@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SubjectController {

    /** Id of Subject from database. */
    private  int idForEdit = 0;

    /** Info message field. */
    private static String infoMessageSubject;

    /** INFO message */
    private final String INFO_SQL_ERRO = "Some problem with Subject SQL request.";

    /** Field with list of all Subjects from database. */
    private static List<Subject> subjectList = new ArrayList();

    /** Field with Data Access Object for Subject. */
    @Autowired
    private SubjectDAO subjectDAO;

    /**
     *  Method that get list of all required Subjects and
     *  passes them to viewAllSubject.jsp page.
     * @return ModelAndView object with list of Subjects.
     */
    @RequestMapping(value = "/viewAllSubjects", method = RequestMethod.GET)
    public ModelAndView viewAllSubjects() {
        subjectList = subjectDAO.getAllSubject();
        infoMessageSubject = subjectList == null? INFO_SQL_ERRO: null;
        log.info(infoMessageSubject);
        ModelAndView modelAndView = new ModelAndView("subjectsView/viewAllSubjects");
        modelAndView.addObject("infoMessageSubject",infoMessageSubject);
        modelAndView.addObject("list", subjectList);
        return modelAndView;
    }

    /**
     *  Method that create new object of Subject and
     *  passes it to addSubject.jsp page for filling empty fields.
     * @return ModelAndView object with new Subject.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public ModelAndView addSubject() {
        ModelAndView modelAndView = new ModelAndView("subjectsView/addSubject");
        modelAndView.addObject("command",new Subject());
        return modelAndView;
    }

    /**
     * Method that save new Subject.
     * @param subject new object of Subject filled with param's from addSubject.jsp
     * @return ModelAndView redirection to viewAllSubjects()
     */
    @RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
    public ModelAndView saveSubject(@ModelAttribute Subject subject) {
        subjectDAO.addSubject(subject);
        return new ModelAndView("redirect:/viewAllSubjects");
    }

    /**
     *  Method that get selected Subject object and
     *  passes it to editSubject.jsp page for edit.
     * @param id - unique identifier of each Subject object.
     * @return ModelAndView object with selected Subject.
     */
    @RequestMapping(value = "/editSubject/{id}", method = RequestMethod.GET)
    public ModelAndView editSubject(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("subjectsView/editSubject");
        modelAndView.addObject("command",findSubjectInList(subjectList, id));
        return modelAndView;
    }

    /**
     * Method that save edited Subject object.
     * @param subject edited object of Subject filled with param's from editSubject.jsp
     * @return ModelAndView object with redirection to viewAllSubjects()
     */
    @RequestMapping(value = "/editSubject/editSaveSubject", method = RequestMethod.POST)
    public ModelAndView editSaveSubject(@ModelAttribute Subject subject) {
        subject.setId(idForEdit);
        infoMessageSubject = subjectDAO.saveSubject(subject);
        return new ModelAndView("redirect:/viewAllSubjects");
    }

    /**
     * Method that delete selected Subject.
     * @param id unique identifier of each Subject object.
     * @return ModelAndView object with redirection to viewAllSubjects()
     */
    @RequestMapping(value = "/deleteSubject/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSubject(@PathVariable int id) {
        infoMessageSubject = subjectDAO.removeSubject(id);
        return new ModelAndView("redirect:/viewAllSubjects");
    }

    /**
     * Method that find selected Subject from all Group list.
     * @param subjectList list of Subjects.
     * @param id unique identifier of each Subject object.
     * @return selected Subject Object.
     */
    public Subject findSubjectInList(List<Subject> subjectList, int id) {
        Subject subject = null;
        for (Subject temp: subjectList) {
            if (temp.getId() == id) {subject = temp;}
        }
        return subject;
    }

}
