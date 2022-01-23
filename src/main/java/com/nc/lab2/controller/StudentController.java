package com.nc.lab2.controller;


import com.nc.lab2.dao.GroupDAO;
import com.nc.lab2.dao.StudentDAO;
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
public class StudentController {

    private String  infoMessage = null;

    public static Logger log = LogManager.getLogger();

    private static List<Student> studentList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "/viewAllStudents", method = RequestMethod.GET)
    public ModelAndView viewAllStudents() {
        studentList = studentDAO.getAllStudents();
        log.info("Log inside method VIEW STUDENT (Test)");
        ModelAndView modelAndView = new ModelAndView("viewAllStudents");
        modelAndView.addObject("massage",infoMessage);
        modelAndView.addObject("list",studentList);
        return modelAndView;
    }

    @RequestMapping(value = "/findStudent", method = RequestMethod.GET)
    public ModelAndView findStudent() {
        return new ModelAndView("findStudent", "command", new Student());
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.POST)
    public ModelAndView getStudent(@ModelAttribute Student student) {
        List<Student> studentList = new ArrayList();
        studentList = (studentDAO.findStudentAccount(student.getName()));
        return new ModelAndView("viewAllStudents", "list", studentList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public ModelAndView addStudent() {
        ModelAndView modelAndView = new ModelAndView("addStudent");
        modelAndView.addObject("command",new Student());
        HashMap< Integer, String> awailableGroups = new HashMap<>();
        for (Group temp: studentDAO.getAwailGroup()) {
            awailableGroups.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("name1",awailableGroups);

        return modelAndView;


    }

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        studentDAO.addStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStudent(@PathVariable int id) {
        infoMessage = studentDAO.removeStudent(id);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + infoMessage);
        ModelAndView modelAndView = new ModelAndView("redirect:/viewAllStudents");
        return modelAndView;
    }

    @RequestMapping(value = "/editStudent/editSave", method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute Student student) {
        System.out.println(student);
        studentDAO.saveStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    @RequestMapping(value = "/editStudent/{id}", method = RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable int id) {
        return new ModelAndView("editStudent", "command", findStudentInList(studentList, id));
    }

    public Student findStudentInList(List<Student> studentList, int id) {
        Student student = null;
        for (Student temp: studentList) {
            if (temp.getId() == id) {student = temp;}
        }
        return student;
    }

//    ------------------------------------------------- Не реализованные методы

    @RequestMapping(value = "/getStudentsWithMark", method = RequestMethod.GET)
    public ModelAndView getStudentsWithMark() {
        ModelAndView modelAndView = new ModelAndView("getStudentsWithMark");
        return modelAndView;
    }


}
