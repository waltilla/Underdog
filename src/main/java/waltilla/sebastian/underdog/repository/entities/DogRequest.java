package waltilla.sebastian.underdog.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DogRequest {

    /* TODO: Instead of DogValidator use the built in validator
            Now notblank is only used for openAPI spec
     */
    @NotBlank(message = "birthDate may not be null or blank")
    private String birthDate;

    private String breed;

    @NotBlank(message = "name may not be null or blank")
    private String name;

}