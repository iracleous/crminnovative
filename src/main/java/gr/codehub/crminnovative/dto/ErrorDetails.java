package gr.codehub.crminnovative.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Integer errorCode;
    private String errorMessage;

}
