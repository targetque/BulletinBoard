package com.example.netdive.service;

import com.example.netdive.dto.AccessToken;
import com.example.netdive.dto.AccessTokenRequest;
import com.example.netdive.dto.BoardDTO;
import com.example.netdive.model.Board;
import com.example.netdive.repository.BoardRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.impl.crypto.DefaultJwtSigner;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyFactory;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
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

        Map<String, Object> jwtHeader = new HashMap<>();

        Map<String, Object> jwtPayLoad = new HashMap<>();

        jwtPayLoad.put("exp", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        jwtPayLoad.put("subject", "token");

        // 유효기간 24시간

        //log.debug("time {}", payLoad.get("exp"));

        //System.out.println(payLoad.get("exp"));

        jwtHeader.put("alg","HS256");
        jwtHeader.put("typ","JWT");

        JwtBuilder jwtBuilder = Jwts.builder().setHeader(jwtHeader).setClaims(jwtPayLoad).signWith(SignatureAlgorithm.HS256,"Y2xvc2V0Y2xvc2V0");

        AccessToken accessToken = AccessToken
                .builder()
                .accessToken(jwtBuilder.compact())
                .expiresIn(Integer.valueOf((String) jwtPayLoad.get("exp")))
                .tokenType("SELF")
                .build();

        return accessToken;
    }

    public boolean checkValidToken(String accessToken) {

        Jwt jwt = Jwts.parser().setSigningKey("Y2xvc2V0Y2xvc2V0").parse(accessToken);

        Claims payLoad = (Claims) jwt.getBody();

        Integer exp = (Integer)payLoad.get("exp");

        Long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        if(currentTime.compareTo(Long.valueOf(exp)) < 0) {
            return true;
        }

        return false;

    }
}
