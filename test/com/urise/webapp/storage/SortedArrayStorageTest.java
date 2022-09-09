package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
        @Test(expected = StorageException.class)
        public void saveOverflow() {
            storage.clear();
            try {
                for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    Resume resume = new Resume();
                    storage.save(resume);
                }
            } catch (Exception e) {
                Assert.fail("Тест не прошел");
            }
            Resume theLast = new Resume();
            storage.save(theLast);
        }
    }

