package com.nc.lab2.controller;


import com.nc.lab2.dao.StudentDAO;
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
import java.util.List;


@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentController {

    public static Logger log = LogManager.getLogger();

    private static List<Student> studentList = new ArrayList();
    private static int counter = 0;

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "/viewAllStudents", method = RequestMethod.GET)
    public ModelAndView viewAllStudents() {
        studentList = studentDAO.getAllStudents();
        log.info("Log inside method VIEW STUDENT (Test)");
        return new ModelAndView("viewAllStudents", "list", studentList);
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
        return new ModelAndView("addStudent", "command", new Student());
    }


    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        student.setId(studentDAO.findMaxId().getId() + 1);
        System.out.println(student);
        studentDAO.addStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStudent(@PathVariable int id) {
        studentDAO.removeStudent(findStudentInList(studentList, id));
        return new ModelAndView("redirect:/viewAllStudents");
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


}
