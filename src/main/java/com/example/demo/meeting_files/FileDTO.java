package com.example.demo.meeting_files;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDTO {
    private Long id;

    private String fileName;

    private Integer eventId;

    public FileDTO(Long id) {
        this.id = id;
    }
}
