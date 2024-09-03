package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Ignore("This test is ignored in this class")
    @Test
    public void saveOverflow() {
    }
}