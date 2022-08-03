package com.review.monitoring.MonitoringSystem.elk;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.*;
@Setter
@Getter
@Document(indexName = "test1")
public class ElasticDocument {
    @Id @GeneratedValue
    @Column(name="index_name")
    private Long id;
    private LocalDateTime localDateTime;
    public ElasticDocument(){
        this.localDateTime = LocalDateTime.now();
    }
}
