package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Ignore("This test is ignored in this class")
    @Test
    public void saveOverflow() {
    }
}