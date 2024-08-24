package com.makersharks.assessment.repository;

import com.makersharks.assessment.entity.Supplier;
import com.makersharks.assessment.enums.ManufacturingProcess;
import com.makersharks.assessment.enums.NatureOfBusiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier , Long> {

    @Query("SELECT s FROM Supplier s WHERE " +
            "(:location IS NULL OR s.location = :location) AND " +
            "(:natureOfBusiness IS NULL OR s.natureOfBusiness = :natureOfBusiness) AND " +
            "(:manufacturingProcess IS NULL OR s.manufacturingProcess = :manufacturingProcess)")

  Page<Supplier> findSuppliersByCriteria(
            @Param("location") String location,
            @Param("natureOfBusiness") NatureOfBusiness natureOfBusiness,
            @Param("manufacturingProcess") ManufacturingProcess manufacturingProcess,
            Pageable pageable);
}
