package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.persistance.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {

    private final IStudentMapper studentMapper;
    // 오라클 DB와 연결된 Mapper

    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".insertStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(
                /* Optional.ofNullable = Null포함 가능하도록 설정 */
                studentMapper.getStudent(pDTO)
                // select 쿼리
        );
        //Studnet 테이블에 등록된 학생 아이디가 존재 체크를 위한 DB조회


        /* 이후 패턴 잘 익혀둘 것 Null 값 처리 방식 */

        if (!res.isPresent()) { // DB 조회 결과로 회원아이디가 존재하지 않는다면
            studentMapper.insertStudent(pDTO);
            // 학생 등록 SQL 실행하기
        }

        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        /* Optional.ofNullable.orElseGet() : Null이 아니라면 Empty로 처리*/
        /* Empty는 메모리에 올라갔고 빈칸인 것 || Null은 값이 없는 것(정의X => 처리 불가능) */
        /* .orElseGet(ArrayList::new) ArrayList에 Empty로 메모리에 올려두어라. */
        /* 이후에 빈칸 값으로 처리 가능해짐 */

        log.info(this.getClass().getName() + ".insertStudent End!");

        return rList;
    }

    @Override
    public List<StudentDTO> insertStudentList(List<StudentDTO> pList) throws Exception {

        log.info(this.getClass().getName() + ".insertStudentGroup Start!");
        StudentDTO pDTO;
        int num = pList.size();

        for(int i = 0; i < num; i++) {
            pDTO = pList.get(i);
            Optional<StudentDTO> res = Optional.ofNullable(
                    /* Optional.ofNullable = Null포함 가능하도록 설정 */
                    studentMapper.getStudent(pDTO)
                    // select 쿼리
            );
            //Studnet 테이블에 등록된 학생 아이디가 존재 체크를 위한 DB조회


            /* 이후 패턴 잘 익혀둘 것 Null 값 처리 방식 */

            if (!res.isPresent()) { // DB 조회 결과로 회원아이디가 존재하지 않는다면
                studentMapper.insertStudent(pDTO);
                // 학생 등록 SQL 실행하기
            }
        }

        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        /* Optional.ofNullable.orElseGet() : Null이 아니라면 Empty로 처리*/
        /* Empty는 메모리에 올라갔고 빈칸인 것 || Null은 값이 없는 것(정의X => 처리 불가능) */
        /* .orElseGet(ArrayList::new) ArrayList에 Empty로 메모리에 올려두어라. */
        /* 이후에 빈칸 값으로 처리 가능해짐 */

        log.info(this.getClass().getName() + ".insertStudentGroup End!");

        return rList;
    }

    @Override
    public List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception {
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
            log.info(pDTO.getUserId()+"님이 삭제되었습니다.");
            // 학생 정보 Delete 하기

        } else {
            log.info("회원이 존재하지 않아 삭제되지 못했습니다.");
        }

        // 학생 테이블 전체 조회하기
        List<StudentDTO> rList = Optional.ofNullable(studentMapper.getStudentList()).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".deleteStudent End!");

        return rList;
    }

    @Override
    public List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".updateStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudent(pDTO));

        if (res.isPresent()) {
            studentMapper.updateStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 수정되었습니다.");
        } else {
            log.info("회원이 존재하지 않아 수정되지 못했습니다.");
        }

        List<StudentDTO> rList = Optional.ofNullable(studentMapper.getStudentList()).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".updateStudent End!");

        return rList;
    }
}
