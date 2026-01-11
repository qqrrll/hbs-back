package org.example.hbsbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.entity.HotelPhoto;
import org.example.hbsbackend.repository.HotelPhotoRepository;
import org.example.hbsbackend.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HotelPhotoService {

    private final HotelRepository hotelRepository;
    private final HotelPhotoRepository photoRepository;

    @Transactional
    public void upload(Long hotelId, MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Only image files allowed");
        }

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        try {
            HotelPhoto photo = new HotelPhoto();
            photo.setData(file.getBytes());
            photo.setContentType(file.getContentType());
            photo.setHotel(hotel);

            boolean hasMain = hotel.getPhotos()
                    .stream()
                    .anyMatch(HotelPhoto::isMainPhoto);

            photo.setMainPhoto(!hasMain);

            photoRepository.save(photo);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload hotel photo");
        }
    }

    public HotelPhoto getPhoto(Long photoId) {
        return photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    @Transactional
    public void deletePhoto(Long photoId) {

        HotelPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        Hotel hotel = photo.getHotel();
        boolean wasMain = photo.isMainPhoto();

        photoRepository.delete(photo);

        if (wasMain) {
            hotel.getPhotos().stream()
                    .findFirst()
                    .ifPresent(p -> p.setMainPhoto(true));
        }
    }

    @Transactional
    public void setMainPhoto(Long photoId) {

        HotelPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        Hotel hotel = photo.getHotel();

        hotel.getPhotos().forEach(p -> p.setMainPhoto(false));
        photo.setMainPhoto(true);
    }
}


