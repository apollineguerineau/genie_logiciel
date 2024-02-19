package fr.ensai.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.repository.ScanRepository;
import fr.ensai.demo.repository.FileLeafRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
    @TestInstance(Lifecycle.PER_CLASS)

public class ScanServiceTest {
    Logger LOG  = LoggerFactory.getLogger(ScanServiceTest.class);

    @Autowired
    private ScanService scanService;

    @MockBean
    private ScanRepository scanRepository;

    @MockBean
    private FileLeafRepository fileLeafRepository;

    @Test
    public void testGetScans() {
        // Créer des scans fictifs
        Scan scan1 = new Scan();
        scan1.setId(1L);
        scan1.setFileSystemType("local");

        Scan scan2 = new Scan();
        scan2.setId(2L);
        scan2.setFileSystemType("s3");

        List<Scan> mockScans = Arrays.asList(scan1, scan2);

        // Définir le comportement du mock du repository
        when(scanRepository.findAll()).thenReturn(mockScans);

        // Appeler la méthode à tester
        Iterable<Scan> result = scanService.getScans();

        // Vérifier le résultat
        assertEquals(mockScans, result);
    }

    @Test
    public void testGetScanById() {
        // Créer un objet Scan fictif
        Scan mockScan = new Scan();
        mockScan.setId(1L);
        mockScan.setFileSystemType("local");

        // Définir le comportement du mock
        when(scanRepository.findById(1L)).thenReturn(Optional.of(mockScan));

        // Appeler la méthode à tester
        Optional<Scan> result = scanService.getScanById(1L);

        // Vérifier le résultat
        assertEquals(Optional.of(mockScan), result);
    }

    @Test
    public void testDeleteScan() {
        // Définir le comportement du mock
        when(scanRepository.findById(1L)).thenReturn(Optional.of(new Scan()));

        // Appeler la méthode à tester
        boolean result = scanService.deleteScan(1L);

        // Vérifier le résultat
        assertEquals(true, result);
        verify(scanRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCompareScans() {
        // Créer deux scans fictifs
        Scan scan1 = new Scan();
        scan1.setId(1L);
        scan1.setFileSystemType("local");
        scan1.setRootDirectoryName("root1");
        scan1.setScanDate("2024-02-05");

        Scan scan2 = new Scan();
        scan2.setId(2L);
        scan2.setFileSystemType("s3");
        scan2.setRootDirectoryName("root1");
        scan2.setScanDate("2024-02-06");

        // Définir le comportement du mock du repository
        when(scanRepository.findById(1L)).thenReturn(Optional.of(scan1));
        when(scanRepository.findById(2L)).thenReturn(Optional.of(scan2));

        // Appeler la méthode à tester
        String differences = scanService.compareScans(1L, 2L);

        // Vérifier le résultat
        String expected = "Différences détectées:\nFileSystemType: local vs s3\nScanDate: 2024-02-05 vs 2024-02-06";
        assertEquals(expected, differences);
    }

    @Test
    public void testUpdateScan() {
        // Créer un objet Scan fictif
        Scan existingScan = new Scan();
        existingScan.setId(1L);
        existingScan.setFileSystemType("local");
        existingScan.setScanDate("2024-02-05");

        // Créer un objet Scan fictif mis à jour
        Scan updatedScan = new Scan();
        updatedScan.setFileSystemType("s3");
        updatedScan.setScanDate("2024-02-06");

        // Définir le comportement du mock du repository
        when(scanRepository.findById(1L)).thenReturn(Optional.of(existingScan));
        when(scanRepository.save(Mockito.any(Scan.class))).thenReturn(updatedScan);

        // Appeler la méthode à tester
        Optional<Scan> result = scanService.updateScan(1L, updatedScan);

        // Vérifier le résultat
        assertTrue(result.isPresent());
        assertEquals(updatedScan.getFileSystemType(), result.get().getFileSystemType());
        assertEquals(updatedScan.getScanDate(), result.get().getScanDate());

        // Vérifier que la méthode save du repository a été appelée avec le scan mis à jour
        verify(scanRepository, times(1)).save(existingScan);
    }

}