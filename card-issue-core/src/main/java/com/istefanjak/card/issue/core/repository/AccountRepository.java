package com.istefanjak.card.issue.core.repository;

import com.istefanjak.card.issue.core.model.Account;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repository for managing {@link Account entities} */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  Optional<Account> findByOib(String oib);

  long deleteByOib(String oib);
}
