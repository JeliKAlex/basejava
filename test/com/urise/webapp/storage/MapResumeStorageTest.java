package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapResumeStorageTest extends AbstractStorageTest {
    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Ignore("This test is ignored in this class")
    @Test
    public void saveOverflow() {
    }
}