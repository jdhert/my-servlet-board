package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;

import java.util.ArrayList;

public interface BoardDao {
    ArrayList<Board> getAll();
    Board getById(Long id);
    void save(Board board);
    void update(Board board);
    void delete(Board board);

}
