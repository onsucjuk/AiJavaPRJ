package kopo.poly;

import kopo.poly.dto.StudentDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import kopo.poly.service.IStudentDelete;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class AiJavaPrjApplication implements CommandLineRunner {
    // CommandLineRunner은 웹을 사용안하고 JAVA에서 구현할 때 사용. @Override 이후에 코딩

    private final IOcrService ocrService;
    // @RequiredArgsConstructor의 역할
    // @Service 정의된 자바 파일
    // Spring Frameworks 실행될 때, @Service 정의한 장바는 자동으로 메모리에 올림
    // 메모리에 올라간 OcrService 객체를 ocrService 변수에 객체를 넣어주기
    // 즉 메모리에 올라간 데이터를 똑같은 타입의 객체(변수)로 받는 것

    private final INlpService nlpService;
    //자연어 처리

    private final IStudentService studentService;
    private final IStudentDelete studentDElete;
    // DB 처리

    public static void main(String[] args) {

        SpringApplication.run(AiJavaPrjApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        log.info("자바 프로그래밍 시작!!");

        // 이미지 인식

/*        String filePath = "image"; // 문자열을 인식할 이미지 파일 경로
        String fileName = "sample01.jpg"; // 문자열을 인식할 이미지 파일 이름
        // 읽을 이미지 파일

        OcrDTO pDTO = new OcrDTO();
        // 전달할 값(Parameter) 약자로 보통 변수앞에 p를 붙임 pDTO(parameter DTO) 전달하는 DTO
        // OcrService의 함수에 정보를 전달할 DTO를 메모리에 올리기

        pDTO.setFilePath(filePath);
        pDTO.setFileName(fileName);

        OcrDTO rDTO = ocrService.getReadforImageText(pDTO);
        // 실행 결과(Result) -> rDTO(result DTO) 받는 DTO

        String result = rDTO.getResult();
        // OcrService 객체의 함수 호출
        // result = 인식된 문자열

        log.info("인식된 문자열");
        log.info(result);

        log.info("자바 프로그래밍 종료!!");

        log.info("-------------------------------------------------------");
        NlpDTO nlpDTO = nlpService.getPlainText(result);
        log.info("형태소별 품사 분석 결과 : " + nlpDTO.getResult());


        nlpDTO = nlpService.getNouns(result);
        //명사 추출 결과

        List<String> nouns = nlpDTO.getNouns();
        // 명사 추출결과를 nouns 변수에 저장하기

        Set<String> distinct = new HashSet<>(nouns);

        log.info("중복 제거 수행 전 단어 수 : " + nouns.size());
        log.info("중복 제거 수행 후 단어 수 : " + distinct.size());

        Map<String, Integer> rMap = new HashMap<>();
        // 단어, 빈도수 Map 구조로 저장을 위해 생성 <중복 제거 HashMap>

        for(String s : distinct) {
            int count = Collections.frequency(nouns,s);
            // 단어 빈도수 [distinct<중복제거>에 속한 단어를 전체 단어에서 각 단어마다 count]
            rMap.put(s, count);
            // 단어, 빈도수 Map 구조로 저장

            log.info(s + " : " + count); // 저장 결과 출력
        }

        //빈도수 결과 정렬
        List<Map.Entry<String, Integer>> sortResult = new LinkedList<>(rMap.entrySet());
        // 정렬을 위해 맵에 저장된 레코드 1개(키, 값)을 리스트 구조로 변경하기

        Collections.sort(sortResult, (o1,o2) -> o2.getValue().compareTo(o1.getValue()));

        log.info("가장 많이 사용된 단어는? : " + sortResult);*/

        StudentDTO pDTO; // 학생 등록, 수정, 삭제에 활용될 DTO
        List<StudentDTO> rList; // DB 조회 결과를 표현

        pDTO = new StudentDTO();

        pDTO.setUserId("hglee67");
        pDTO.setUserName("이협건");
        pDTO.setEmail("hglee67@kopo.ac.kr");
        pDTO.setAddr("서울");

        rList = studentService.insertStudent(pDTO);

        rList.forEach(dto -> {
                log.info("DB에 저장된 아이디 : " + dto.getUserId());
                log.info("DB에 저장된 이름 : " + dto.getUserName());
                log.info("DB에 저장된 이메일 : " + dto.getEmail());
                log.info("DB에 저장된 주소 : " + dto.getAddr());
                });

        studentDElete.deleteStudent(pDTO);

        log.info("자바 프로그래밍 종료!!");
    }
}
