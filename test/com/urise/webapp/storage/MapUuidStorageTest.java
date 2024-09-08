package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapUuidStorageTest extends AbstractStorageTest {

    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }

    @Ignore("This test is ignored in this class")
    @Test
    public void saveOverflow() {
    }
}