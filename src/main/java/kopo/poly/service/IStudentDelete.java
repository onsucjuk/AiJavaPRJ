package kopo.poly.service;

import kopo.poly.dto.StudentDTO;

public interface IStudentDelete {
    void deleteStudent(StudentDTO pDTO) throws Exception;
}
