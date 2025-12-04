package com.example.elasticsearch.controller;

import com.example.elasticsearch.controller.dto.request.UserCreateRequestDto;
import com.example.elasticsearch.UserDocument;
import com.example.elasticsearch.controller.dto.request.UserUpdateRequestDto;
import com.example.elasticsearch.repository.UserDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDocumentRepository userDocumentRepository;

    @PostMapping
    public UserDocument createUser(@RequestBody UserCreateRequestDto requestDto) {
        UserDocument user = new UserDocument(
                requestDto.id(),
                requestDto.name(),
                requestDto.age(),
                requestDto.isActive()
        );

        return userDocumentRepository.save(user);
    }

    @GetMapping
    public Page<UserDocument> getUsers() {
        return userDocumentRepository.findAll(PageRequest.of(0, 10));
    }

    @GetMapping("/{id}")
    public UserDocument getUser(@PathVariable String id) {
        return userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
    }

    @PutMapping("/{id}")
    public UserDocument updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto requestDto) {
        UserDocument existingUser = userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        existingUser.setName(requestDto.name());
        existingUser.setAge(requestDto.age());
        existingUser.setIsActive(requestDto.isActive());

        return userDocumentRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        UserDocument user = userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        userDocumentRepository.delete(user);
    }
}
