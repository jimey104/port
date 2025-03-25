package edu.du.module3.service;

import edu.du.module3.dto.BoardDTO;
import edu.du.module3.entity.Board;
import edu.du.module3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 게시판 서비스 클래스
 * 게시글 관련 비즈니스 로직을 처리함.
 */
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository; // 게시글 데이터 처리를 위한 JPA 리포지토리

    /**
     * 모든 게시글을 조회하는 메서드
     */
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 특정 게시글을 ID로 조회하는 메서드
     */
    public BoardDTO getBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return board.map(this::convertToDTO).orElse(null);
    }

    /**
     * 특정 게시글을 ID로 조회해 삭제하는 메서드
     */
    public void deletedById(Long id) {
       boardRepository.deleteById(id);
    }

    /**
     * 새로운 게시글을 저장하는 메서드
     */
    public void saveBoard(BoardDTO boardDTO) {
        List<Board> list = boardRepository.findAll();

        int result = 0;
        for(Board board : list) {
            if(board.getId().equals(boardDTO.getId())) {
                board.setTitle(boardDTO.getTitle());
                board.setName(boardDTO.getName());
                board.setContent(boardDTO.getContent());
                board.setUpdatedAt(LocalDateTime.now());
                boardRepository.save(board);
                result = 1;
                break;
            }
        }
        if(result == 0) {
            Board board = convertToEntity(boardDTO);
            boardRepository.save(board);
        }
    }

    /**
     * 게시글을 DTO로 변환하는 메서드
     */
    private BoardDTO convertToDTO(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    /**
     * DTO를 엔터티로 변환하는 메서드
     */
    private Board convertToEntity(BoardDTO boardDTO) {
        return Board.builder()
                .id(boardDTO.getId())
                .name(boardDTO.getName())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .createdAt(boardDTO.getCreatedAt() != null ? boardDTO.getCreatedAt() : LocalDateTime.now())
                .updatedAt(boardDTO.getUpdatedAt())
                .build();
    }
}
