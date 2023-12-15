package com.example.demo.meeting_files;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FileEntityService {

    List<FileEntity> getFiles();

    Optional<FileEntity> getFile(Long id);

    List<FileEntity> getFilesByEventId(Integer eventId);

    FileEntity storeFile(FileEntity file);

    Optional<FileEntity> findById(Long fileId);

    void deleteFile(Long fileId);
}
