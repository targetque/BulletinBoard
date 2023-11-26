package com.example.netdive.controller;

import com.example.netdive.dto.BoardDTO;
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
    public void registContent(@RequestBody BoardDTO boardDto) throws Exception{
        contentService.saveContent(boardDto);
    }

    @GetMapping("/content")
    public BoardDTO getContent(@RequestParam Long contentId) {
        return contentService.readContent(contentId);
    }
   /* @PutMapping("/{contentId}")
    public void updateContent(@PathVariable Long contentId, @RequestBody BoardDTO boardDTO) {
        contentService.updateContent(contentId, boardDTO);
    }*/

}
