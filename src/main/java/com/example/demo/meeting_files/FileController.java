package com.example.demo.meeting_files;

import com.example.demo.mappers.EventMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/files")
public class FileController {

    private final FileEntityService fileEntityService;
    private final EventMapper mapper;


    public FileController(FileEntityService fileEntityService, EventMapper mapper) {
        this.fileEntityService = fileEntityService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<FileEntity> getFiles() {
        return fileEntityService.getFiles();
    }
    @GetMapping("/{id}")
    public Optional<FileEntity> getFile(@PathVariable Long fileId) {
        return fileEntityService.getFile(fileId);
    }
    @GetMapping("/event/{eventId}")
    public List<FileEntity> getFilesByEventId(@PathVariable Integer eventId) {
        return fileEntityService.getFilesByEventId(eventId);
    }
    @PostMapping("/{eventId}")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam MultipartFile file, @PathVariable("eventId") Integer eventId) {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileData(file.getBytes());
            fileEntity.setEventId(eventId);

            FileEntity savedFile = fileEntityService.storeFile(fileEntity);

            return ResponseEntity.ok(this.mapper.toFileDTO(savedFile));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
        Optional<FileEntity> fileEntityOptional = fileEntityService.findById(fileId);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();

            ByteArrayResource resource = new ByteArrayResource(fileEntity.getFileData());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileEntity.getFileName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(fileEntity.getFileData().length)
                    .body(resource);
        } else {
            // Handle file not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{fileId}")
    public Integer deleteFile(@PathVariable Long fileId) {
        fileEntityService.deleteFile(fileId);
        return 1;
    }
}

