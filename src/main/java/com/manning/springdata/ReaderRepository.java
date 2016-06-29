package com.manning.springdata;

import com.manning.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vadim on 6/25/16.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
