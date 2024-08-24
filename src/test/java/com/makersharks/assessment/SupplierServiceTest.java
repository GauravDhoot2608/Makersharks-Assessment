package com.makersharks.assessment;

import com.makersharks.assessment.entity.Supplier;
import com.makersharks.assessment.enums.ManufacturingProcess;
import com.makersharks.assessment.enums.NatureOfBusiness;
import com.makersharks.assessment.repository.SupplierRepository;
import com.makersharks.assessment.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @Test
    public void testGetSuppliers_ValidInputs_ReturnsList() {
        // Arrange
        String location = "Surat";
        String natureOfBusiness = "large_scale";
        String process = "moulding";
        int page = 0;
        int size = 10;

        NatureOfBusiness business = NatureOfBusiness.LARGE_SCALE;
        ManufacturingProcess manufacturingProcess = ManufacturingProcess.MOULDING;

        Supplier supplier = new Supplier(1L,"Test Company","http://testcompany.com", "Surat",business,manufacturingProcess);

        Page<Supplier> pageResult = new PageImpl<>(List.of(supplier));
        when(supplierRepository.findSuppliersByCriteria(location, business, manufacturingProcess, PageRequest.of(page, size)))
                .thenReturn(pageResult);

        // Actual
        List<Supplier> result = supplierService.getSuppliers(location, natureOfBusiness, process, page, size);

        // Assert
        assertEquals(supplier, result.get(0));
    }

    @Test
    public void testGetSuppliers_InvalidNatureOfBusiness_ThrowsException() {
        // Arrange
        String location = "Surat";
        String natureOfBusiness = "invalid";
        String process = "moulding";
        int page = 0;
        int size = 10;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                supplierService.getSuppliers(location, natureOfBusiness, process, page, size));

        assertEquals("!! Invalid Nature of Business !! \nnature of business: [SMALL_SCALE, MEDIUM_SCALE, LARGE_SCALE]", thrown.getMessage());
    }

    @Test
    public void testGetSuppliers_InvalidManufacturingProcess_ThrowsException() {
        // Arrange
        String location = "Surat";
        String natureOfBusiness = "large_scale";
        String process = "invalid";
        int page = 0;
        int size = 10;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                supplierService.getSuppliers(location, natureOfBusiness, process, page, size));

        assertEquals("!! Invalid Manufacturing Process !! \nManufacturing Processes: [MOULDING, PRINTING_3D, CASTING, COATING]", thrown.getMessage());
    }

    @Test
    public void testGetSuppliers_InvalidPageNumber_ThrowsException() {
        // Arrange
        String location = "Surat";
        String natureOfBusiness = "large_scale";
        String process = "moulding";
        int page = -1;  // Invalid page number
        int size = 10;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                supplierService.getSuppliers(location, natureOfBusiness, process, page, size));

        assertEquals("Page number cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testGetSuppliers_InvalidPageSize_ThrowsException() {
        // Arrange
        String location = "Surat";
        String natureOfBusiness = "large_scale";
        String process = "moulding";
        int page = 0;
        int size = 0;  // Invalid page size

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                supplierService.getSuppliers(location, natureOfBusiness, process, page, size));

        assertEquals("Page size must be at least 1.", thrown.getMessage());
    }
}

