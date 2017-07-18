package mTrepka.libary.repository;

import mTrepka.libary.domain.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mario on 2017-07-17.
 */
@Repository("borrowHistoryRepository")
public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory,Long> {
}
