package com.manning.springdata;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.manning.domain.Book;

/**
 * Created by vstorozhuk on 6/13/2016.
 */

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(String reader);
}
