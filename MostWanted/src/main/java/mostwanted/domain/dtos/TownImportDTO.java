package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportDTO {

    //exclude fields with expose annotation from GSON
    @Expose
    private String name;

    public TownImportDTO() {

    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
