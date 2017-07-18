package mTrepka.libary.service;

import mTrepka.libary.domain.BorrowHistory;

import java.util.List;

/**
 * Created by Mario on 2017-07-17.
 */
public interface BorrowHistoryService {
    List<BorrowHistory> getAllHistory();
    void save(BorrowHistory borrowHistory);
    long countHistories();
}
