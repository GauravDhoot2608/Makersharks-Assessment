package com.makersharks.assessment.entity;

import com.makersharks.assessment.enums.ManufacturingProcess;
import com.makersharks.assessment.enums.NatureOfBusiness;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @NotNull(message = "Company name cannot be null")
    private String companyName;

    @NotNull(message = "Website cannot be null")
    private String website;

    @NotNull(message = "Location cannot be null")
    private String location;

    @Enumerated(EnumType.STRING)
    private NatureOfBusiness natureOfBusiness;

    @Enumerated(EnumType.STRING)
    private ManufacturingProcess manufacturingProcess;
}
