package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.entity.HotelPhoto;
import org.example.hbsbackend.service.HotelPhotoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelPhotoController {

    private final HotelPhotoService photoService;

    @PostMapping(
            value = "/{hotelId}/photos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public void upload(
            @PathVariable Long hotelId,

            @Parameter(
                    description = "Hotel image (jpg/png)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam MultipartFile file
    ) {
        photoService.upload(hotelId, file);
    }

    @GetMapping("/photos/{photoId}")
    public ResponseEntity<byte[]> get(@PathVariable Long photoId) {

        HotelPhoto photo = photoService.getPhoto(photoId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .body(photo.getData());
    }

    @DeleteMapping("/photos/{photoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
    }

    @PutMapping("/photos/{photoId}/main")
    @PreAuthorize("hasRole('ADMIN')")
    public void setMain(@PathVariable Long photoId) {
        photoService.setMainPhoto(photoId);
    }
}

