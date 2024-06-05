package org.example.tiggle.config;

public enum BaseResponseMessage {

    REQUEST_SUCCESS(true, 100, "요청 성공"),

    //    회원 기능 1000번대
    MEMBER_REGISTER_SUCCESS(true, 1000, "정상적으로 가입되었습니다."),
    MEMBER_REGISTER_FAIL_ID_EMPTY(false, 1101, "id를 입력해주세요."),
    MEMBER_REGISTER_FAIL_ID_DUPLICATE(false, 1102, "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요."),
    MEMBER_REGISTER_FAIL_ID_NOT_TYPE(false, 1103, "id를 영문과 숫자를 포함하여 6~20글자 이내로 입력해주세요."),
    MEMBER_REGISTER_FAIL_PASSWORD_EMPTY(false, 1104, "패스워드를 입력해주세요."),
    MEMBER_REGISTER_FAIL_PASSWORD_NOT_EQUAL(false, 1105, "패스워드가 일치하지 않습니다."),
    MEMBER_REGISTER_FAIL_PASSWORD_COMPLEXITY(false, 1106, " 영문 대소문자와 특수문자를 포함하여 패스워드를 8~20글자 입력해주세요."),
    MEMBER_REGISTER_FAIL_TERMS_NOT_ACCEPTED(false, 1107, "이용 약관에 동의하지 않았습니다"),
    MEMBER_REGISTER_FAIL_HUMAN_DUPLICATE(false, 1108, "동일한 사용자가 존재합니다. 하나의 명의로 1개의 계정만 생성할 수 있습니다."),
    MEMBER_LOGIN_SUCCESS(true, 1200, "정상적으로 로그인되었습니다."),
    MEMBER_LOGIN_FAIL(false, 1201, "아이디 혹은 패스워드가 일치하지 않습니다"),

    //티켓팅 2000ㅑ

    TICKETING_SUCCESS(true, 2000, "티켓팅에 성공했습니다."),
    TICKET_PURCHASE_FAIL_ALREADY_RESERVATION_SEAT(false, 2001, "이미 예약된 좌석입니다."),
    TICKET_PURCHASE_FAIL_NOT_FOUND_SEAT(false, 2002, "선택할 수 없는 좌석 번호입니다."),
    TICKET_PURCHASE_FAIL_ABNORMAL_ACCESS(false, 2003, "비정상적인 접근입니다."),
    TICKET_PURCHASE_FAIL_PROGRAM(false, 2004, "예약할 수 없는 공연입니다."),
    TICKET_PURCHASE_FAIL_PAYMENT(false, 2005, "결제 실패입니다."),
    TICKET_PURCHASE_FAIL_TOO_MANY_CONNECTION(false, 2006, "접속 에러"),

    //결제 완료된 티켓
    TICKET_PURCHASE_FAIL_SEAT_SOLDED(false, 2102, "이미 매진된 티켓입니다."),
    TICKET_FAIL_SESSION_EXPIRED(false, 2102, "세션이 만료되었습니다"),
    TICKET_PURCHASE_FAIL_CARD_INVALID(false, 2104, "해당 카드는 유효하지 않습니다."),
    TICKET_PURCHASE_FAIL_CARD_TIME_OUT(false, 2105, "결제 유효 시간이 만료되었습니다."),

    //결제 정보가 맞지 않습니다.
    TICKET_PAYMENT_FAIL_INSUFFICIENT_BALANCE(false, 2101, "통장 잔액이 부족합니다"),
    TICKET_FAIL_PABEOB_ALLOW(false, 2101, "팝업이 허용되지 않았습니다"),
    TICKET_FAIL_EXCEEDS_LIMIT(false, 2201, "구매 가능한 티켓 수를 초과하였습니다"),

    //교환 3000
    EXCHANGE_SUCCESS(true, 3000, "교환에 성공했습니다."),
    EXCHANGE_FAIL_NOT_OPEN_ACCESS(false, 3001, "요청을 보낼 수 없는 상대입니다."),
    EXCHANGE_FAIL_PAYMENT_TIME_EXPIRE(false, 3002, "결제 대기 시간 만료"),
    EXCHANGE_FAIL_REQUEST_TIME_EXPIRE(true, 3003, "요청 시간이 만료되었습니다."),
    EXCHANGE_FAIL_ALREADY_FINISH_PROGRAM(true, 3003, "만료된 공연 정보입니다."),
    EXCHANGE_FAIL_ABNORMAL_ACCESS(true, 3003, "비정상적인 접근입니다."),
    EXCHANGE_FAIL_EXCEEDS_LIMIT(false, 3201, "교환 가능한 횟수를 초과하였습니다"),
    EXCHANGE_FAIL_INSUFFICIENT_BALANCE(false, 3101, "잔액이 부족합니다"),
    EXCHANGE_FAIL_ITEM_NOT_FOUND(false, 3202, "교환이 불가능한 좌석입니다"),
    EXCHANGE_FAIL(true, 3003, "교환 실패");
    //요청 횟수 제한


    private Boolean isSuccess;
    private Integer code;
    private String message;

    BaseResponseMessage(Boolean isSuccess, Integer code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    ;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
};
