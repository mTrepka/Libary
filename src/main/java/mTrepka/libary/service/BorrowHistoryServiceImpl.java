package mTrepka.libary.service;


import mTrepka.libary.domain.BorrowHistory;
import mTrepka.libary.repository.BorrowHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mario on 2017-07-17.
 */
@Service
public class BorrowHistoryServiceImpl implements BorrowHistoryService {
    @Autowired
    private BorrowHistoryRepository borrowHistoryRepository;

    @Override
    public List<BorrowHistory> getAllHistory() {
        return borrowHistoryRepository.findAll();
    }

    @Override
    public void save(BorrowHistory borrowHistory) {
        borrowHistoryRepository.save(borrowHistory);
    }

    @Override
    public long countHistories() {
        return borrowHistoryRepository.count();
    }
}
