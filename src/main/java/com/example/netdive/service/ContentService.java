package com.example.netdive.service;

import com.example.netdive.dto.BoardDTO;
import com.example.netdive.model.Board;
import com.example.netdive.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private final BoardRepository boardRepository;

    public ContentService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void saveContent(BoardDTO boardDTO) {

        Board board = new Board(boardDTO);

        boardRepository.save(board);
    }
    public Board readContent(Long contentId) {
        return boardRepository.findById(contentId).orElseThrow();
    }
}
