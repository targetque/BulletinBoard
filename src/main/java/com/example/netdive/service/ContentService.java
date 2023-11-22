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
    public BoardDTO readContent(Long contentId) {
        Board board = boardRepository.findById(contentId).orElseThrow();

        BoardDTO boardDTO = BoardDTO.builder().userId(board.getUserId()).title(board.getTitle()).content(board.getContent()).build();

        return boardDTO;
    }

    public void updateContent(Long contentId, BoardDTO boardDTO) {
        Board board = boardRepository.findById(contentId).orElseThrow();
        board.updateContent(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }


}
