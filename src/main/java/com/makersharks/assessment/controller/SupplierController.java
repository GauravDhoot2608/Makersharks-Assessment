package com.makersharks.assessment.controller;

import com.makersharks.assessment.entity.Supplier;
import com.makersharks.assessment.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/query")
    public ResponseEntity<List<Supplier>> getSuppliers(@RequestParam(required = false) String location,
                                                       @RequestParam(required = false) String natureOfBusiness,
                                                       @RequestParam(required = false) String manufacturingProcess,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(supplierService.getSuppliers(location, natureOfBusiness, manufacturingProcess, page, size));
    }
}
