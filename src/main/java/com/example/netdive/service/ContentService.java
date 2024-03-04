package com.example.netdive.service;

import com.example.netdive.dto.BoardDTO;
import com.example.netdive.model.Board;
import com.example.netdive.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class ContentService {

    private final BoardRepository boardRepository;

    private final TestService testService;
    public ContentService(BoardRepository boardRepository, TestService testService) {
        this.boardRepository = boardRepository;
        this.testService = testService;
    }


    //@Transactional(rollbackFor = Exception.class)
    public void saveContent(BoardDTO boardDTO) throws Exception {

        Board board = new Board(boardDTO);
        boardRepository.save(board);
        testService.updateContent(1000L, boardDTO);
        throw new RuntimeException();

    }

    /*
    * @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void updateContent(Long contentId, BoardDTO boardDTO) {
        Board board = boardRepository.findById(contentId).orElseThrow();
        board.updateContent(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }
    * */



    public BoardDTO readContent(Long contentId) {
        Board board = boardRepository.findById(contentId).orElseThrow();

        BoardDTO boardDTO = BoardDTO.builder().userId(board.getUserId()).title(board.getTitle()).content(board.getContent()).build();

        return boardDTO;
    }



}
