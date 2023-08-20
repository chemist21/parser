package ru.mts.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.parser.model.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {
}
