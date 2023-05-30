package kopo.poly;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {

        SpringApplication.run(AiJavaPrjApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception { // 이미지 인식

        log.info("자바 프로그래밍 시작!!");

        String filePath = "image"; // 문자열을 인식할 이미지 파일 경로
        String fileName = "sample02.jpg"; // 문자열을 인식할 이미지 파일 이름
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

    }
}
