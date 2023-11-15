package com.example.netdive.controller;

import com.example.netdive.dto.BoardDTO;
import com.example.netdive.model.Board;
import com.example.netdive.service.ContentService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/board")
@RestController
public class BoardController {

    private final ContentService contentService;

    public BoardController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping()
    public void registContent(@RequestBody BoardDTO boardDto) {
        contentService.saveContent(boardDto);
    }

    @GetMapping("/content")
    public Board getContent(@RequestParam Long contentId) {
        return contentService.readContent(contentId);
    }

}
