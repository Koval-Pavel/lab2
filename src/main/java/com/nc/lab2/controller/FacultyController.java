package com.nc.lab2.controller;

import com.nc.lab2.dao.FacultyDAO;
import com.nc.lab2.model.Faculty;

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
 * Class for Controller of Faculty model.
 * @author  Pavel Koval
 */
@Controller
public class FacultyController {

    /** Id of Faculty from database. */
    private  int idForEdit = 0;

    /** Info message field. */
    private static String infoMessageFaculty;

    /** Field with list of all Faculty from database. */
    private static List<Faculty> facultyList = new ArrayList();

    /** INFO message */
    private final String INFO_SQL_ERRO = "Some problem with Faculty  SQL request.";

    /** Field with Data Access Object for Faculty. */
    @Autowired
    private FacultyDAO facultyDAO;

    /**
     *  Method that get list of all required Faculty's and
     *  passes them to viewAllFacultys.jsp page.
     * @return ModelAndView object with list of Faculty's.
     */
    @RequestMapping(value = "/viewAllFacultys", method = RequestMethod.GET)
    public ModelAndView viewAllGroups() {
        facultyList = facultyDAO.getAllFaculty();
        infoMessageFaculty = facultyList == null? INFO_SQL_ERRO: null;
        log.info(infoMessageFaculty);
        ModelAndView modelAndView = new ModelAndView("facultysView/viewAllFacultys");
        modelAndView.addObject("massage",infoMessageFaculty);
        infoMessageFaculty = null;
        modelAndView.addObject("list",facultyList);
        return modelAndView;
    }

    /**
     *  Method that create new object of Faculty  and
     *  passes it to addFaculty.jsp page for filling empty fields.
     * @return ModelAndView object with new Faculty.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public ModelAndView addFaculty() {
        ModelAndView modelAndView = new ModelAndView("facultysView/addFaculty");
        modelAndView.addObject("command", new Faculty());
        return modelAndView;
    }

    /**
     * Method that save new Faculty object.
     * @param faculty new object of Faculty filled with param's from addFaculty.jsp
     * @return ModelAndView redirection to viewAllGroups()
     */
    @RequestMapping(value = "/saveFaculty", method = RequestMethod.POST)
    public ModelAndView saveFaculty(@ModelAttribute Faculty faculty) {
        infoMessageFaculty = facultyDAO.addFaculty(faculty);
        return new ModelAndView("redirect:/viewAllFacultys");
    }

    /**
     *  Method that get selected Faculty object and
     *  passes it to editFaculty.jsp page for edit.
     * @param id - unique identifier of each Faculty object.
     * @return ModelAndView object with selected Faculty
     */
    @RequestMapping(value = "/editFaculty/{id}", method = RequestMethod.GET)
    public ModelAndView editFaculty(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("facultysView/editFaculty");
        modelAndView.addObject("command",findFacultyInList(facultyList, id));
        return modelAndView;
    }

    /**
     * Method that save edited Faculty.
     * @param faculty edited object of Faculty filled with param's from editFaculty.jsp
     * @return odelAndView object with redirection to viewAllGroups()
     */
    @RequestMapping(value = "/editFaculty/editSaveFaculty", method = RequestMethod.POST)
    public ModelAndView editSaveFaculty(@ModelAttribute Faculty faculty) {
        faculty.setId(idForEdit);
        infoMessageFaculty = facultyDAO.saveFaculty(faculty);
        return new ModelAndView("redirect:/viewAllFacultys");
    }

    /**
     *  Method that delete selected Faculty.
     * @param id unique identifier of each Faculty object.
     * @return ModelAndView object with redirection viewAllGroups()
     */
    @RequestMapping(value = "/deleteFaculty/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFaculty(@PathVariable int id) {
        infoMessageFaculty = facultyDAO.removeFaculty(id);
        return new ModelAndView("redirect:/viewAllFacultys");
    }

    /**
     * Method that find selected Faculty from all Faculty list.
     * @param facultyList list of Faculty's.
     * @param id unique identifier of each Faculty object.
     * @return selected Faculty Object.
     */
    public Faculty findFacultyInList(List<Faculty> facultyList, int id) {
        Faculty faculty = null;
        for (Faculty temp: facultyList) {
            if (temp.getId() == id) {faculty = temp;}
        }
        return faculty;
    }
}
