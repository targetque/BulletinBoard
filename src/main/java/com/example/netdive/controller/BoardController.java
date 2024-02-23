package com.example.netdive.controller;

import com.example.netdive.dto.BoardDTO;
import com.example.netdive.dto.EcremmoceResponose;
import com.example.netdive.service.ContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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


    @GetMapping("/api/TomsOrder/test")
    public Map<String,Object> getOrderListEcremmoce(@RequestParam Integer page, @RequestParam String companyId, @RequestParam String status, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String platform) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("/Users/diormiss/IdeaProjects/BulletinBoard/src/main/resources/ecremmoceSampleData.json"), Map.class);
    }

}
