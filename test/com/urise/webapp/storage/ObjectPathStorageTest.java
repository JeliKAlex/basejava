package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
        super(new PathStorage(PATH_DIRECTORY, new ObjectStreamSerializer()));
    }
}