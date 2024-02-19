package fr.ensai.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.strategy.LocalFileSystemScanner;


@RunWith(SpringRunner.class)
@SpringBootTest
    @TestInstance(Lifecycle.PER_CLASS)
class LocalFileSystemScannerTest {
    Logger LOG  = LoggerFactory.getLogger(LocalFileSystemScannerTest.class);

    private LocalFileSystemScanner scanner;

    @BeforeEach
    void setUp() {
        scanner = new LocalFileSystemScanner();
    }

    @Test
    public void testScanFileSystem() {
        String path = "./src/test/resources/sample_folder";
        FolderComponent folder = scanner.importFileSystem(path, 0);
        Scan scan = scanner.scanFileSystem(folder, "", "", Integer.MAX_VALUE, Integer.MAX_VALUE);

        assertNotNull(scan);
        assertEquals("local file system", scan.getFileSystemType());
        assertFalse(scan.getScannedFiles().isEmpty());
    }
}
