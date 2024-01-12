package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.util.ArrayList;

public interface BoardDao {
    ArrayList<Board> getAll();

    ArrayList<Board> getAll(Pagination pagination);
    Board getById(Long id);
    void save(Board board);
    void update(Board board);
    void delete(Board board);

    int count();

    int count(Pagination pagination);
    ArrayList<Board> getConc(Pagination pagination);


    ArrayList<Board> getConc(Pagination pagination, String term);
    int count(Pagination pagination, String term);

}
