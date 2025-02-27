package com.example.demo.controllers;

import com.example.demo.DAO.CameraDAO;
import com.example.demo.entities.Camera;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shelter/cameras")
@RequiredArgsConstructor
public class CameraController {
    final CameraDAO cameraDAO;

    @PostMapping("/add")
    public void addCamera(@RequestBody Camera camera){
        cameraDAO.insertCamera(camera);
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add-camera";
    }
}
