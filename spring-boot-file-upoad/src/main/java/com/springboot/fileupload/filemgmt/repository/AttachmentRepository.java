package com.springboot.fileupload.filemgmt.repository;

import com.springboot.fileupload.filemgmt.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}
