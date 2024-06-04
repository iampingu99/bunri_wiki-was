package com.example.demo.bounded_context.solution.service;

import com.example.demo.base.exception.CustomException;
import com.example.demo.base.exception.ExceptionCode;
import com.example.demo.bounded_context.solution.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public Optional<Long> findIdByName(String name){
        log.info("SELECT FROM TAG");
        return tagRepository.findIdByName(name);
    }
}
