package kopo.poly.service.impl;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j // System.out.println() => log.info() 로 사용 메모리 누수 개선
@Service
public class OcrService implements IOcrService {

    @Override
    public OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getReadforImageText start!");
        // 시작 됐는지 안됐는지 확인하기 위한 수단 <출력되면 성공한 것> 판단을 위한 구문 되도록 넣자.

        ClassPathResource resource = new ClassPathResource(pDTO.getFilePath() + "/" + pDTO.getFileName());
        // resources 폴더의 밑에 존재하는 파일을 활용하기 위해서는 반드시 classPathResource 객체 사용함

        ITesseract instance = new Tesseract();
        // OCR 기술 사용을 위한 Tesseract 플랫폼 객체 생성

        instance.setDatapath(IOcrService.modelFile);
        // IOcrService 인터페이스 파일에 정의한 상수 받아오기

        instance.setLanguage("kor");
        // 이미지 한국어 설정 <기본값은 영어>
        // 우리가 받은 ITesseract 파일이 kor<한국어> 파일임

        String result = instance.doOCR(resource.getFile());
        // 이미지 파일로부터 텍스트 읽기

        pDTO.setResult(result);
        // 읽은 글자 DTO에 저장하기

        log.info(this.getClass().getName() + ".getReadforImageText End!");
        // 정상적으로 끝났음을 확인하기 위한 문장 출력

        return pDTO;
    }
}
