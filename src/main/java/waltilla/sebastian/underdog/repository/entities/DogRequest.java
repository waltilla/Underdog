package waltilla.sebastian.underdog.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DogRequest {

    /*
    import org.jetbrains.annotations.NotNull;
    import javax.validation.constraints.NotBlank;

    @NotBlank(message = "birthDate may not be null or blank")
    @NotBlank(message = "name may not be null or blank")

    with return string
     */

    private String birthDate;

    private String breed;

    private String name;

}