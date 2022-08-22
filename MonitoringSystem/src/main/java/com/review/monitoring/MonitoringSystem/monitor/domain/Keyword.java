package com.review.monitoring.MonitoringSystem.monitor.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public enum Keyword {
    // cs 부서 키워드
    CALL("전화"), ORDER("주문"), EXCHANGE("교환"), CUSTOMER("고객"), SOLD_OUT("품절"),
    PERSON("사람"), REVIEW("리뷰"), CONTACT("연락"), KINDNESS("친절"), SERVICE("서비스"), SELLER("판매자"),
    // delivery 부서 키워드
    SHIPPING("배송"), PACKING("포장"), PARCEL_SERVICE("택배"), DISPATCH("발송"), ARRIVAL("도착"),
    DELIVERY("배달"), WEEK("주일"), WEEKDAY("평일"), WEEKEND("주말"),
    // quality 부서 키워드
    USE("사용"), PRODUCT("제품"), PRICE("가격"), CHEAP("저렴"), CLEAN("깔끔"), DESIGN("디자인"),
    CONDITION("상태"), RECOMMENDATION("추천"), COLOR("색상"), MATERIAL("재질"), HIGH_QUALITY("고급"),
    QUALITY("퀄리티"), PERFORMANCE("성능");

    @Getter
    private String name;
    Keyword(String name) {
        this.name = name;
    }

    private static final Map<String,String> NAME_MAP = Collections.unmodifiableMap(Stream.of(values()).
            collect(Collectors.toMap(Keyword::getName,Keyword::name)));

    public static Keyword of(final String name) {
        return Keyword.valueOf(NAME_MAP.get(name));
    }
}
