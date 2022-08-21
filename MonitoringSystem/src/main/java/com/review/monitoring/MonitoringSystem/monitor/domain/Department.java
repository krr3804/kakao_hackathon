package com.review.monitoring.MonitoringSystem.monitor.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Department {
    DELIVERY_DEPT("delivery", Arrays.asList(Keyword.CALL, Keyword.ORDER, Keyword.EXCHANGE, Keyword.CUSTOMER, Keyword.SOLD_OUT,
            Keyword.PERSON, Keyword.REVIEW, Keyword.CONTACT, Keyword.KINDNESS, Keyword.SERVICE, Keyword.SELLER)),
    CUSTOMER_SERVICE_DEPT("customerService",Arrays.asList(Keyword.SHIPPING, Keyword.PACKING, Keyword.PARCEL_SERVICE,
            Keyword.DISPATCH, Keyword.ARRIVAL, Keyword.DELIVERY, Keyword.WEEK, Keyword.WEEKDAY, Keyword.WEEKEND)),
    QUALITY_DEPT("quality",Arrays.asList(Keyword.USE, Keyword.PRODUCT,Keyword.PRICE, Keyword.CHEAP, Keyword.CLEAN, Keyword.DESIGN,
            Keyword.CONDITION, Keyword.RECOMMENDATION, Keyword.COLOR, Keyword.MATERIAL, Keyword.HIGH_QUALITY, Keyword.QUALITY, Keyword.PERFORMANCE)),
    UCF("ucf", Collections.emptyList());
    @Getter
    private final String name;
    @Getter
    private final List<Keyword> keywords;

    Department(String name, List<Keyword> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    private static final Map<String,String> NAME_MAP = Collections.unmodifiableMap(Stream.of(values()).
            collect(Collectors.toMap(Department::getName,Department::name)));

    public static Department of(final String name) {
        return Department.valueOf(NAME_MAP.get(name));
    }

}
