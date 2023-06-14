package kopo.poly.service;

import kopo.poly.dto.StudentDTO;

import java.util.List;

public interface IStudentService {

    /**
     * 학생 등록한 뒤,결과 조회하기
     *
     * @parm pDTO 등록할 학생 정보를 가지고 있는 DTO
     * @return DB 조회한 학생 정보
     */
    List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception;
    List<StudentDTO> insertStudentList(List<StudentDTO> val) throws Exception;

    List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception;

    /**
     * 학생 수정한 뒤, 결과 조회하기
     *
     * @param pDTO 수정할 학생 정보를 가지고 있는 DTO
     * @return DB 조회한 학생 정보
     */

    List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception;


}
