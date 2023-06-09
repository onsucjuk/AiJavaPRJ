package kopo.poly.persistance.mapper;

import kopo.poly.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentMapper {

    void insertStudent(StudentDTO pDTO) throws Exception;

    List<StudentDTO> getStudentList() throws Exception;
    // 조회 결과값 : 여러 개

    StudentDTO getStudent(StudentDTO pDTO) throws Exception;
    // 조회 결과값 : 단일(1개)

    void deleteStudent(StudentDTO pDTO) throws Exception;
    // 데이터 삭제
}
