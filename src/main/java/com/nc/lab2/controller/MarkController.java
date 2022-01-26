package com.nc.lab2.controller;


import com.nc.lab2.dao.MarkDAO;
import com.nc.lab2.model.Mark;
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
public class MarkController {

    public static Logger log = LogManager.getLogger();

    private static List<Mark> MarkList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private MarkDAO markDAO;


    @RequestMapping(value = "/studentMarkInfo/{id}", method = RequestMethod.GET)
    public ModelAndView studentInfo(@PathVariable int id) {

        return new ModelAndView("marksView/studentInfo");

    }



//    ------------------------------------------------- Не реализованные методы

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addMark", method = RequestMethod.GET)
    public ModelAndView addMark() {
        ModelAndView modelAndView = new ModelAndView("addMark");
//        modelAndView.addObject("command",new Group());
//        HashMap< Integer, String> awailableFaculty = new HashMap<>();
//        for (Faculty temp: groupDAO.getAwailFaculty()) {
//            awailableFaculty.put(temp.getId(), temp.getName());
//        }
//        modelAndView.addObject("name1",awailableFaculty);

        return modelAndView;
    }

    @RequestMapping(value = "/saveMark", method = RequestMethod.POST)
    public ModelAndView saveMark(@ModelAttribute Mark mark) {
        markDAO.addMark(mark);
        return new ModelAndView("redirect:/viewStudentWithMark");
    }


}
