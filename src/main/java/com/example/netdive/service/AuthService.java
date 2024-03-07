package com.example.netdive.service;

import com.example.netdive.dto.AccessToken;
import com.example.netdive.dto.AccessTokenRequest;
import com.example.netdive.dto.BoardDTO;
import com.example.netdive.model.Board;
import com.example.netdive.repository.BoardRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final BoardRepository boardRepository;

    private final TestService testService;
    public AuthService(BoardRepository boardRepository, TestService testService) {
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


    public AccessToken publishToken(String id, String password) {

        Map<String, String> jwtHeader = new HashMap<>();

        Map<String, Object> jwtPayLoad = new HashMap<>();

        Claims payLoad = new DefaultClaims();

        payLoad.setExpiration(Date.after());

        payLoad.setSubject("");

        jwtHeader.put("alg","HS256");
        jwtHeader.put("typ","JWT");

        JwtBuilder jwtBuilder = Jwts.builder().setHeader().setClaims().signWith()
        //2XnBgHqowPsIJOFDX7GaX0
    }
}
