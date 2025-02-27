package com.example.demo.DAO;

import com.example.demo.entities.Camera;
import com.example.demo.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CameraDAO {
    private final CameraRepository cameraRepository;

    Camera getCameraById(long id){
        return cameraRepository.findCameraById(id);
    }

    public Camera insertCamera(Camera camera) {
        return cameraRepository.save(camera);
    }
}
