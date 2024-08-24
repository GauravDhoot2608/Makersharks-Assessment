package com.makersharks.assessment.service;

import com.makersharks.assessment.entity.Supplier;
import com.makersharks.assessment.enums.ManufacturingProcess;
import com.makersharks.assessment.enums.NatureOfBusiness;
import com.makersharks.assessment.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getSuppliers(String location, String natureOfBusiness, String process, int page, int size) {

        NatureOfBusiness business = null;
        if(natureOfBusiness != null){
            try{
                business = NatureOfBusiness.valueOf(natureOfBusiness.trim().replaceAll(" " , "_").toUpperCase());
            }catch (IllegalArgumentException ex){

                throw new IllegalArgumentException("!! Invalid Nature of Business !! \n" + "nature of business: " + Arrays.stream(NatureOfBusiness.values()).toList());
            }
        }

        ManufacturingProcess manufacturingProcess = null;
        if(process != null){
            try{
                manufacturingProcess = ManufacturingProcess.valueOf(process.trim().replaceAll(" " , "_").toUpperCase());
            }catch (IllegalArgumentException ex){
                throw new IllegalArgumentException("!! Invalid Manufacturing Process !! \n" + "Manufacturing Processes: " + Arrays.stream(ManufacturingProcess.values()).toList());
            }
        }

        // Validate pagination parameters
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative.");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Page size must be at least 1.");
        }
        if (size > 100) {
            size = 100; // Optionally limit page size to prevent performance issues
        }

        Page<Supplier> suppliers =  supplierRepository.findSuppliersByCriteria(location, business, manufacturingProcess, PageRequest.of(page, size));

        return suppliers.stream().toList();
    }
}
