package com.example.studentsystem.Controller;

import com.example.studentsystem.Api.ApiResponse;
import com.example.studentsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents () {
        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent (@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent (@PathVariable int index, @RequestBody Student student) {
        students.set(index, student);
        return new ApiResponse("Student updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent (@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("Student deleted successfully");
    }

    @PutMapping("/honor/{id}")
    public ApiResponse honorsStudent (@PathVariable int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                if (students.get(i).getGPA() >= 3.75) {
                    return new ApiResponse("First honor student");
                } else if (students.get(i).getGPA() >= 3.25) {
                    return new ApiResponse("Second honor student");
                }
            }
        }
        return null;
    }

    @PutMapping("/average")
    public ArrayList<Student> studentsGPA () {
        ArrayList<Student> averageGPA = new ArrayList<>();
        double sum = 0;
        double average;

        for (int i = 0; i < students.size(); i++) {
           sum += students.get(i).getGPA();
        }

        average = sum / students.size();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getGPA() > average) {
                averageGPA.add(students.get(i));
            }
        }
        return averageGPA;
    }

}
