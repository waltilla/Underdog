package waltilla.sebastian.underdog.dogDb.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dog {

    private String id;

    private String  birthDate;

    private String breed;

    private String name;

}