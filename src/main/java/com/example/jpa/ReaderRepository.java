package com.example.jpa;

import com.example.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dkorolev
 *         Date: 11.04.2017
 *         Time: 19:26
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {

    Reader findByFullname(String fullname);
}
