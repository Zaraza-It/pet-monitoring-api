package com.example.demo.DAO;

import com.example.demo.entities.Video;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoDAO {
    private final VideoRepository videoRepository;

    Video getVideoById(long id){
        return videoRepository.findVideoById(id);
    }
}
