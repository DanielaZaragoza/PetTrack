package com.example.ApiPetTrack.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file, String directory) {
        return uploadFileToDirectory(file, "images/" + (directory != null ? directory : ""));
    }

    public String uploadFile(MultipartFile file, String directory) {
        return uploadFileToDirectory(file, "files/" + (directory != null ? directory : ""));
    }

    private String uploadFileToDirectory(MultipartFile file, String directory) {
        String sanitizedFileName = sanitizeFileName(file.getOriginalFilename());
        String key = directory + "/" + sanitizedFileName;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .contentDisposition("inline")
                    .acl("public-read")
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            return getFileUrl(key);
        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo a S3", e);
        }
    }

    public void deleteFile(String fileKey) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    public String getFileUrl(String fileKey) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileKey);
    }

    private String sanitizeFileName(String fileName) {
        // Reemplaza espacios y caracteres no alfanuméricos (excepto punto, guion, guion bajo)
        return fileName.replaceAll("[^a-zA-Z0-9.\\-_]", "_");
    }
}
