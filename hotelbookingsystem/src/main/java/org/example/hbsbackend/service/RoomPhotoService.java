package org.example.hbsbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.entity.Room;
import org.example.hbsbackend.entity.RoomPhoto;
import org.example.hbsbackend.repository.RoomPhotoRepository;
import org.example.hbsbackend.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RoomPhotoService {

    private final RoomRepository roomRepository;
    private final RoomPhotoRepository photoRepository;

    @Transactional
    public void upload(Long roomId, MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        try {
            RoomPhoto photo = new RoomPhoto();
            photo.setData(file.getBytes());
            photo.setContentType(file.getContentType());
            photo.setRoom(room);

            photoRepository.save(photo);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload room photo");
        }
    }

    public RoomPhoto getPhoto(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    public void deletePhoto(Long photoId) {
        RoomPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        photoRepository.delete(photo);
    }
    @Transactional
    public void setMainPhoto(Long photoId) {

        RoomPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        Room room = photo.getRoom();

        // сбрасываем старое главное фото
        for (RoomPhoto p : room.getPhotos()) {
            p.setMainPhoto(false);
        }

        // ставим новое
        photo.setMainPhoto(true);

        photoRepository.save(photo);
    }


}
