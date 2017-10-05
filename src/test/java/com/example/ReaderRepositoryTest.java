package com.example;

import com.example.domain.Reader;
import com.example.jpa.ReaderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test of ReaderRepository.
 *
 * Created by dkorolev on 10/5/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = DemoApplication.class)
public class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Before
    public void setUp() throws Exception {
        Reader reader = new Reader();
        reader.setUsername("B");
        reader.setFullname("Bobby");
        reader.setPassword("123");
        readerRepository.saveAndFlush(reader);
    }

    @Test
    public void testService() {
        Reader address = readerRepository.findByFullname("Bobby");
        assertNotNull(address);
        assertEquals("B", address.getUsername());
        assertEquals("Bobby", address.getFullname());
    }
}
