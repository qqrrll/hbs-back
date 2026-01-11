package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.entity.RoomPhoto;
import org.example.hbsbackend.service.RoomPhotoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomPhotoController {

    private final RoomPhotoService photoService;

    @PostMapping(
            value = "/{roomId}/photos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )

    @PreAuthorize("hasRole('ADMIN')")
    public void upload(
            @PathVariable Long roomId,

            @Parameter(
                    description = "Image file (jpg, png)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam MultipartFile file
    ) {
        photoService.upload(roomId, file);
    }


    @GetMapping("/photos/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long photoId) {

        RoomPhoto photo = photoService.getPhoto(photoId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .body(photo.getData());
    }

    @DeleteMapping("/photos/{photoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePhoto(@PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
    }

    @PutMapping("/photos/{photoId}/main")
    @PreAuthorize("hasRole('ADMIN')")
    public void setMainPhoto(@PathVariable Long photoId) {
        photoService.setMainPhoto(photoId);
    }
}

