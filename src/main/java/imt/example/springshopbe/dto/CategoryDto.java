package imt.example.springshopbe.dto;

import imt.example.springshopbe.domain.CategoryStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link imt.example.springshopbe.domain.Category} entity
 */
@Data
public class CategoryDto implements Serializable {
    private  Long id;
    @NotEmpty(message = "Khong duoc bo trong ten!")
    private  String name;
    private  CategoryStatus status;
}