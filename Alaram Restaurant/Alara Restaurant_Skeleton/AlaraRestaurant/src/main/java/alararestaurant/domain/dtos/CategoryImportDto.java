package alararestaurant.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryImportDto {
    private String name;

    public CategoryImportDto() {
    }

    @NotNull
    @Size(min=3, max=30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
