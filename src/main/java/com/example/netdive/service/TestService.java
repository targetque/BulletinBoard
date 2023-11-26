package com.example.netdive.service;

import com.example.netdive.dto.BoardDTO;
import com.example.netdive.model.Board;
import com.example.netdive.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class TestService {

   /* private final BoardRepository boardRepository;

    public TestService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void updateContent(Long contentId, BoardDTO boardDTO) {
        Board board = boardRepository.findById(contentId).orElseThrow();
        board.updateContent(boardDTO.getTitle(), boardDTO.getContent());
        try {
            boardRepository.save(board);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }*/

}
