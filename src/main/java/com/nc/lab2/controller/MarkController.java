package com.nc.lab2.controller;


import com.nc.lab2.dao.MarkDAO;
import com.nc.lab2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Controller of Mark model.
 * @author  Pavel Koval
 */
@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MarkController {

    /** Info message field. */
    private static String infoMessageMark;

    /** Info message field. */
    private final String INFO_CHOOSE = "Please choose subject before add mark";

    /** Field with list of all Marks for student from database. */
    private static List<Mark> markList = new ArrayList();

    /** Field with formatter for parsing time */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Field with object of Mark. */
    private Mark mark;

    /** Field with Data Access Object for Mark. */
    @Autowired
    private MarkDAO markDAO;


    /**
     *  Method that get list of all required Marks and
     *  passes them to studentInfo.jsp page.
     * @param id - unique identifier of each Student object.
     * @return ModelAndView object with list of Marks for selected Subject.
     */
    @RequestMapping(value = "/studentMarkInfo/{id}", method = RequestMethod.GET)
    public ModelAndView studentInfo(@PathVariable int id) { // id studenta
        try {
            markList = markDAO.getAllMark(mark.getStudentId(), mark.getSubjectId());
        } catch (Exception e) {
            System.out.println(e);
        }
        ModelAndView modelAndView = new ModelAndView("marksView/studentInfo");
        modelAndView.addObject("marklist", markList);
        HashMap< Integer, String> awailableSubjects = new HashMap<>();
        for (Subject temp: markDAO.getAwailSubject()) {
            awailableSubjects.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("massage",infoMessageMark);
        infoMessageMark = null;
        log.info(infoMessageMark);
//        modelAndView.addObject("command",new Mark());
        modelAndView.addObject("awailableSub",awailableSubjects);
        modelAndView.addObject("studentId",id);
        return modelAndView;
    }

    /**
     *  Method that select Marks list for required Subject.
     * @param mark object of MARK with param's from studentInfo.jsp (Student id and Subject id)
     * @return ModelAndView object with redirection to studentInfo(@PathVariable int id)
     */
    @RequestMapping(value = "/studentMarkInfo/checkStudentMark/{studentId}", method = RequestMethod.POST)
    public ModelAndView checkStudentMark (@ModelAttribute Mark mark) {
        markList = null;
        markList = markDAO.getAllMark(mark.getStudentId(), mark.getSubjectId()); // dlya opredelenoggo stydenta
        this.mark = mark;
        return new ModelAndView("redirect:/studentMarkInfo/{studentId}");
    }

    /**
     *  Method that create new object of Mark and
     *  passes it to addMark.jsp page for filling empty fields.
     * @param mark object of MARK with param's from studentInfo.jsp (Student id and Subject id)
     * @return ModelAndView object with new Mark.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/studentMarkInfo/addMark/{studentId}", method = RequestMethod.GET)
    public ModelAndView addMark(@ModelAttribute Mark mark) {
        ModelAndView modelAndView = new ModelAndView("marksView/addMark");
        try {
            Mark markForAdd = new Mark();
            markForAdd.setSubjectId(this.mark.getSubjectId());
            markForAdd.setStudentId(mark.getStudentId());
            markForAdd.setDate(mark.getDate());
            modelAndView.addObject("command", markForAdd);
            modelAndView.addObject("studentId",mark.getStudentId());
            return modelAndView;
        } catch (NullPointerException e) {
            infoMessageMark = INFO_CHOOSE;
            return new ModelAndView("redirect:/studentMarkInfo/{studentId}");
        }
    }

    /**
     * Method that save new Mark.
     * @param mark new object of Mark filled with param's from addMark.jsp
     * @return ModelAndView redirection to studentInfo(@PathVariable int id)
     */
    @RequestMapping(value = "/studentMarkInfo/addMark/saveMark/{studentId}", method = RequestMethod.POST)
    public ModelAndView saveMark(@ModelAttribute Mark mark) {
        mark.setSubjectId(this.mark.getSubjectId());
        infoMessageMark = markDAO.addMark(mark);
        return new ModelAndView("redirect:/studentMarkInfo/{studentId}");
    }

    /**
     *  Method that delete selected Mark.
     * @param id unique identifier of each Mark object.
     * @return ModelAndView object with redirection to studentInfo(@PathVariable int id)
     */
    @RequestMapping(value = "/studentMarkInfo/deleteMark/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMark(@PathVariable int id) {
        infoMessageMark = markDAO.removeMark(id);
        return new ModelAndView("redirect:/studentMarkInfo/" + mark.getStudentId());
    }

}
