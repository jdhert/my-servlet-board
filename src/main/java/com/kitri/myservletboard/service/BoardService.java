package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardJdbcDao;
import com.kitri.myservletboard.dao.BoardMemoryDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.util.ArrayList;
import java.util.Objects;

public class BoardService {

//    BoardDao boardDao = BoardMemoryDao.getInstance();

    BoardDao boardDao = BoardJdbcDao.getInstance();
    private BoardService(){};
    private static final BoardService instance = new BoardService();

    public static BoardService getInstance(){
        return instance;
    }
    public ArrayList<Board> getBoards(){
        return boardDao.getAll();
    }
    public ArrayList<Board> getBoards(Pagination pagination){
//        if(pagination.getKeyword() == null || Objects.equals(pagination.getKeyword(), "")) {
//            pagination.setTotalRecords(boardDao.count());
//            return boardDao.getAll(pagination);
//        } else {
            pagination.setTotalRecords(boardDao.count(pagination));
            return boardDao.getConc(pagination);
//        }
    }
    public Board getBoard(Long id){
        return boardDao.getById(id);
    }
    public void addBoard(Board board){
        boardDao.save(board);
    }
    public void updateBoard(Board board){
        boardDao.update(board);
    }
    public void deleteBoard(Board board){
        boardDao.delete(board);
    }
}
