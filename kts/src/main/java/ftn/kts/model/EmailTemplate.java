package ftn.kts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailTemplate {
    private String sentFrom;
    private String sendTo;
    private String subject;
    private String body;
    private String attachmentPath;
}
