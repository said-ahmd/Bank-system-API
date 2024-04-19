package org.fawry.bankapisystem.dto.transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositRequest {

    @Positive(message = "The amount should be positive.")
    @Min(value = 5, message = "The amount should be greater than 5.")
    private double amount;

    @Size(min = 16, max = 16, message = "The card number should be 16 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The card number should be only numbers.")
    private String cardNumber;

}
