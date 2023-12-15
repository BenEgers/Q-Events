package com.example.demo.meeting_files;

import com.example.demo.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByEventId(Integer eventId);
}

