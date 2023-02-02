package ftn.kts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {

    @NotBlank
    private String username;

    @NotNull
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z]).{8,20}")
    @Size(min = 8, max = 20)
    private String password;

}
