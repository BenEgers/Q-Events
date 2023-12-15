package com.example.demo.meeting_files;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private Integer eventId;
    @Lob
//    @Basic(fetch = FetchType.EAGER)
    private byte[] fileData;

    public FileEntity(Long id, String fileName, Integer eventId) {
        this.id = id;
        this.fileName = fileName;
        this.eventId = eventId;
    }
}

