package com.example.demo.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    //access
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 서명의 토큰입니다."),
    WRONG_TOKEN(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰 형식입니다."),
    EXPIRE_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰 입니다."),

    //refresh
    EXPIRE_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰 입니다. 재로그인 필요"),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "해당 토큰을 찾을 수 없습니다. 재로그인 필요"),

    //request
    INVALID_BODY(HttpStatus.BAD_REQUEST, "올바르지 않은 바디 값 입니다."),
    INVALID_HEADER(HttpStatus.BAD_REQUEST, "올바르지 않은 헤더 값 입니다."),
    INVALID_COOKIE(HttpStatus.BAD_REQUEST, "올바르지 않은 쿠키 값 입니다."),

    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 계정이름을 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "유효하지 않은 비밀번호 입니다."),
    BLACK_LIST(HttpStatus.BAD_REQUEST, "무효화된 토큰입니다."),
    INVALID_SIGN_OUT(HttpStatus.BAD_REQUEST, "비정상적인 로그아웃 시도입니다."),
    INVALID_WITHDRAWAL(HttpStatus.BAD_REQUEST, "비정상적인 회원탈퇴 시도입니다."),

    DUPLICATE_NAME(HttpStatus.BAD_REQUEST, "중복된 이름이 존재합니다."),
    EMPTY_LOCATION(HttpStatus.BAD_REQUEST, "위치정보가 존재하지 않습니다."),

    WASTE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "해당 폐기물 정보를 찾을 수 없습니다."),
    TAG_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "해당 태그 정보를 찾을 수 없습니다."),

    //solution
    EMPTY_SOLUTION(HttpStatus.NOT_FOUND, "해당 솔루션이 존재하지 않습니다."),

    //wiki
    EMPTY_WIKI(HttpStatus.NOT_FOUND, "해당 위키가 존재하지 않습니다."),
    EXIST_WIKI(HttpStatus.CONFLICT, "해당 배출정보의 보류 상태인 사용자의 위키가 존재합니다."),
    NOT_PENDING_WIKI(HttpStatus.CONFLICT, "반영 및 거부된 위키는 수정 또는 삭제가 불가능합니다."),
    INVALID_WIKI_WRITER(HttpStatus.FORBIDDEN, "해당 위키의 작성자가 아닌 경우 위키의 수정 또는 삭제가 불가능합니다.");

    private HttpStatus status;
    private String message;

    ExceptionCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
