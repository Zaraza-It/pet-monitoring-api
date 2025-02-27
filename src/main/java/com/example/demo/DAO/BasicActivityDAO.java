package com.example.demo.DAO;

import com.example.demo.entities.BasicActivity;
import com.example.demo.repository.BasicActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasicActivityDAO {
    private final BasicActivityRepository basicActivityRepository;

    BasicActivity getBasicActivityById(long id){
        return basicActivityRepository.findBasicActivityById(id);
    }
}
