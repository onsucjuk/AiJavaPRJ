package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.persistance.mapper.IStudentMapper;
import kopo.poly.service.IStudentDelete;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentDelete implements IStudentDelete {

    private final IStudentMapper studentMapper;
    @Override
    public void deleteStudent(StudentDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(
                /* Optional.ofNullable = Null포함 가능하도록 설정 */
                studentMapper.getStudent(pDTO)
                // select 쿼리
        );
        //Studnet 테이블에 등록된 학생 아이디가 존재 체크를 위한 DB조회
        /* 이후 패턴 잘 익혀둘 것 Null 값 처리 방식 */

        if (res.isPresent()) { // DB 조회 결과로 회원아이디가 존재한다면
            studentMapper.deleteStudent(pDTO);
            // 학생 정보 Delete 하기

        log.info(this.getClass().getName() + ".deleteStudent End!");

        }
    }
}
