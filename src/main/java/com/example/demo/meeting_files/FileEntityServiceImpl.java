package com.example.demo.meeting_files;

import com.example.demo.event.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FileEntityServiceImpl implements FileEntityService{

    private final FileEntityRepository fileEntityRepository;

    public FileEntityServiceImpl(FileEntityRepository fileEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
    }

    @Override
    public FileEntity storeFile(FileEntity file) {
        return fileEntityRepository.save(file);
    }

    @Override
    public Optional<FileEntity> findById(Long fileId) {
        return fileEntityRepository.findById(fileId);
    }

    @Override
    public void deleteFile(Long fileId) {
        fileEntityRepository.deleteById(fileId);
    }

    @Override
    public List<FileEntity> getFiles() {
        return this.fileEntityRepository.findAll();
    }

    @Override
    public Optional<FileEntity> getFile(Long id) {
        return this.fileEntityRepository.findById(id);
    }

    @Override
    public List<FileEntity> getFilesByEventId(Integer eventId) {
        return fileEntityRepository.findByEventId(eventId);
    }


}
