package com.learn.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String tag;

    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "lastupdateddate")
    private LocalDateTime lastUpdatedDate;

    @Column(name = "createdby")
    private String createdBy;

    private String url;

    private String size;

    @ManyToOne(fetch = FetchType.EAGER)
    private Notes notes;

    private Boolean deleted;
}
