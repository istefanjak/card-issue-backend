package com.istefanjak.card.issue.core.repository;

import com.istefanjak.card.issue.core.model.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** Repository for managing {@link Card entities} */
@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

  @Modifying
  @Transactional
  @Query("DELETE FROM Card c WHERE c.id = :id")
  int deleteByIdAndReturnCount(Long id);
}
