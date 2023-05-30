package kopo.poly.service;

import kopo.poly.dto.OcrDTO;

public interface IOcrService {

    //인터페이스는 인터페이스를 구현하는 자바 객체들의 공통 상수 설정할 때도 활용
    String modelFile = "c:/model/tessdata";
    // 학습 모델 파일이 존재하는 폴더 주소 받아오는 변수 <String 타입>
    // 원래 상수는 final을 붙여야 함 ex) public static final String modelFile
    // 하지만 스프링부트에서 메모리에 처음 실행시 한 번만 올려서 등록하므로 변수지만 상수와 같은 동작원리로 작동하게 됌

    OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception;
    //이미지 파일로부터 문자 읽어 오기
}
