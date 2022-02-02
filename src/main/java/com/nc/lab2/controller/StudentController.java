package com.nc.lab2.controller;


import com.nc.lab2.dao.StudentDAO;
import com.nc.lab2.model.Group;
import com.nc.lab2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nc.lab2.Lab2Application.log;

/**
 * Class for Controller of Student model.
 * @author  Pavel Koval
 */
@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentController {

    /** Id of Student from database. */
    private int idForEdit = 0;

    /** Info message field. */
    private String  infoMessage = null;

    /** Info message field. */
    private final String INFO_FIND= "Student Find";

    /** Info message field. */
    private final String INFO_NO_FIND= "No student with this name";

    /** INFO message */
    private final String INFO_SQL_ERRO = "Some problem with Student SQL request.";

    /** Field with list of all Students in group from database. */
    private static List<Student> studentList = new ArrayList();

    /** Field with Data Access Object for Student. */
    @Autowired
    private StudentDAO studentDAO;

    /**
     *  Method that get list of all required Student and
     *  passes them to viewAllStudent.jsp page.
     * @return ModelAndView object with list of Students.
     */
    @RequestMapping(value = "/viewAllStudents", method = RequestMethod.GET)
    public ModelAndView viewAllStudents() {
        studentList = studentDAO.getAllStudents();
        infoMessage = studentList == null? INFO_SQL_ERRO: infoMessage;
        log.info(infoMessage);
        ModelAndView modelAndView = new ModelAndView("studentsView/viewAllStudents");
        modelAndView.addObject("massage",infoMessage);
        infoMessage = null;
        modelAndView.addObject("list",studentList);
        return modelAndView;
    }

    /**
     *  Method that create new object of Student and
     *  passes it to addStudent.jsp page for filling empty fields.
     * @return ModelAndView object with new Student.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public ModelAndView addStudent() {
        ModelAndView modelAndView = new ModelAndView("studentsView/addStudent");
        modelAndView.addObject("command", new Student());
        HashMap< Integer, String> awailableGroups = new HashMap<>();
        for (Group temp: studentDAO.getAwailGroup()) {
            awailableGroups.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("awailableGroups",awailableGroups);

        return modelAndView;
    }

    /**
     * Method that save new Student.
     * @param student new object of Student filled with param's from addGroup.jsp
     * @return ModelAndView redirection to viewAllStudents()
     */
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        infoMessage = studentDAO.addStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    /**
     *  Method that get selected Student object and
     *  passes it to editStudent.jsp page for edit.
     * @param id - unique identifier of each Student object.
     * @return ModelAndView object with selected Student.
     */
    @RequestMapping(value = "/editStudent/{id}", method = RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable int id) {
        idForEdit = id;
        ModelAndView modelAndView = new ModelAndView("studentsView/editStudent");
        modelAndView.addObject("command",findStudentInList(studentList, id));
        HashMap< Integer, String> awailableGroups = new HashMap<>();
        for (Group temp: studentDAO.getAwailGroup()) {
            awailableGroups.put(temp.getId(), temp.getName());
        }
        modelAndView.addObject("awailableGroups",awailableGroups);
        return modelAndView;
    }

    /**
     * Method that save edited Student object.
     * @param student edited object of Student filled with param's from editStudent.jsp
     * @return ModelAndView object with redirection to viewAllStudent()
     */
    @RequestMapping(value = "/editStudent/editSaveStudent", method = RequestMethod.POST)
    public ModelAndView editSaveStudent(@ModelAttribute Student student) {
        student.setId(idForEdit);
        infoMessage = studentDAO.saveStudent(student);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    /**
     * Method that delete selected Student.
     * @param id unique identifier of each Student object.
     * @return ModelAndView object with redirection to viewAllStudent()
     */
    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStudent(@PathVariable int id) {
        infoMessage = studentDAO.removeStudent(id);
        return new ModelAndView("redirect:/viewAllStudents");
    }

    /**
     * Method that find selected Student from all Group list.
     * @param studentList list of Students.
     * @param id unique identifier of each Student object.
     * @return selected Student Object.
     */
    public Student findStudentInList(List<Student> studentList, int id) {
        Student student = null;
        for (Student temp: studentList) {
            if (temp.getId() == id) {student = temp;}
        }
        return student;
    }


    /**
     *  Method that create new object of Student and
     *  passes it to findStudent.jsp page for find.
     * @return ModelAndView object with new Student for find
     */
    @RequestMapping(value = "/findStudent", method = RequestMethod.GET)
    public ModelAndView findStudent() {
        return new ModelAndView("studentsView/findStudent", "command", new Student());
    }

    /**
     *  Method that find Student.
     * @param student edited object of Student filled with param's from editStudent.jsp
     * @return ModelAndView object with list of found Student.
     */
    @RequestMapping(value = "/getStudent", method = RequestMethod.POST)
    public ModelAndView getStudent(@ModelAttribute Student student) {
        List<Student> studentList = new ArrayList();
        studentList = (studentDAO.findStudentAccount(student.getName()));
        infoMessage = studentList.isEmpty()? INFO_NO_FIND : INFO_FIND;
        return new ModelAndView("studentsView/viewAllStudents", "list", studentList);
    }
}
