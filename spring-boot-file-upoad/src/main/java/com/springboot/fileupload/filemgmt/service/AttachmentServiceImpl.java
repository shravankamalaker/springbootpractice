package com.springboot.fileupload.filemgmt.service;

import com.springboot.fileupload.filemgmt.entity.Attachment;
import com.springboot.fileupload.filemgmt.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if( filename.contains("..")){
                throw new Exception("Filename contains invalid path sequence"+filename);
            }
            Attachment attachment = new Attachment(filename, file.getContentType(), file.getBytes());
            return  attachmentRepository.save(attachment);
        } catch(Exception e){
            throw new Exception("Exception occured while saving the file = "+filename);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception{
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with id: "+fileId));
    }
}
