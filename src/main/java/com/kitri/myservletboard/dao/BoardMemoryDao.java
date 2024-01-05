package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class BoardMemoryDao implements BoardDao{

    private static final BoardMemoryDao instance = new BoardMemoryDao();

    public static BoardMemoryDao getInstance(){
        return instance;
    }
    ArrayList<Board> memoryBoardDB = new ArrayList<>();

    public BoardMemoryDao() {
        memoryBoardDB.add(new Board(1L, "첫번째 글입니다!!!", "Parental Advisory", "손흥민", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(2L, "두번째 글입니다!!!", "Parental Advisory", "김민재", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(3L, "세번째 글입니다!!!", "Parental Advisory", "이강인", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(4L, "네번째 글입니다!!!", "Parental Advisory", "황희찬", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(5L, "다섯 번째 글입니다!!!", "Parental Advisory", "황인범", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(6L, "여섯 번째 글입니다!!!", "Parental Advisory", "조규성", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(7L, "일곱 번째 글입니다!!!", "Parental Advisory", "이재성", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(8L, "여덟 번째 글입니다!!!", "Parental Advisory", "이기재", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(9L, "아홉 번째 글입니다!!!", "Parental Advisory", "김영권", LocalDateTime.now(),
                10, 1));
        memoryBoardDB.add(new Board(10L, "열번째 글입니다!!!", "Parental Advisory", "김승규", LocalDateTime.now(),
                10, 1));

    }

    @Override
    public ArrayList<Board> getAll() {
        return memoryBoardDB;
    }

    @Override
    public Board getById(Long id) {
        return memoryBoardDB.stream().filter(board -> Objects.equals(board.getId(), id)).findFirst().get();
    }

    @Override
    public void save(Board board) {
        memoryBoardDB.add(board);
    }

    @Override
    public void update(Board board) {
        Board board1 = getById(board.getId());
        memoryBoardDB.remove(board1);
        memoryBoardDB.add(board);
    }

    @Override
    public void delete(Board board) {
        memoryBoardDB.remove(board);
    }
}
