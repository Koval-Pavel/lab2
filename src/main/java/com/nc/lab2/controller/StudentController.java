package com.nc.lab2.controller;


import com.nc.lab2.dao.StudentDAO;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentController {

    private String  infoMessage = null;

    public static Logger log = LogManager.getLogger();

    private static List<Student> studentList = new ArrayList();
    private int idForEdit = 0;

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "/viewAllStudents", method = RequestMethod.GET)
    public ModelAndView viewAllStudents() {
        studentList = studentDAO.getAllStudents();
        log.info("Log inside method VIEW STUDENT (Test)");
        ModelAndView modelAndView = new ModelAndView("studentsView/viewAllStudents");
        modelAndView.addObject("massage",infoMessage);
        infoMessage = null;
        modelAndView.addObject("list",studentList);
        return modelAndView;
    }

    @RequestMapping(value = "/findStudent", method = RequestMethod.GET)
    public ModelAndView findStudent() {
        return new ModelAndView("studentsView/findStudent", "command", new Student());
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.POST)
    public ModelAndView getStudent(@ModelAttribute Student student) {
        List<Student> studentList = new ArrayList();
        studentList = (studentDAO.findStudentAccount(student.getName()));
        return new ModelAndView("studentsView/viewAllStudents", "list", studentList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public ModelAndView addStudent() {
        ModelAndView modelAndView = new ModelAndView("studentsView/addStudent");
        modelAndView.addObject("command", new Student());
        HashMap< Integer, String> awailableGroups = new HashMap<>();
        for (Group temp: studentDAO.getAwailGroup()) {
            awailableGroups.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("name1",awailableGroups);

        return modelAndView;
    }

    // к методу адд (сейв после адд формы)
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        infoMessage = studentDAO.addStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStudent(@PathVariable int id) {
        infoMessage = studentDAO.removeStudent(id);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    @RequestMapping(value = "/editStudent/editSaveStudent", method = RequestMethod.POST)
    public ModelAndView editSaveStudent(@ModelAttribute Student student) {
        student.setId(idForEdit);
        infoMessage = studentDAO.saveStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    @RequestMapping(value = "/editStudent/{id}", method = RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("studentsView/editStudent");
        modelAndView.addObject("command",findStudentInList(studentList, id));
        HashMap< Integer, String> awailableGroups = new HashMap<>();
        for (Group temp: studentDAO.getAwailGroup()) {
            awailableGroups.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("name1",awailableGroups);
        return modelAndView;
    }

    public Student findStudentInList(List<Student> studentList, int id) {
        Student student = null;
        for (Student temp: studentList) {
            if (temp.getId() == id) {student = temp;}
        }
        return student;
    }

//    ------------------------------------------------- Не реализованные методы

    @RequestMapping(value = "/studentInfo/{id}", method = RequestMethod.GET)
    public ModelAndView studentInfo(@PathVariable int id) {
        return new ModelAndView("studentsView/studentInfo");
    }


}
