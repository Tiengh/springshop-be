package edu.poly.springshopbe.dto;

import edu.poly.springshopbe.domain.CategoryStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryDto implements Serializable {
    private  Long id;
    @NotEmpty(message = "category name is required")
    private  String name;
    private  CategoryStatus status;
}
