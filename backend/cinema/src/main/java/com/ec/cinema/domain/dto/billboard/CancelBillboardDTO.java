package com.ec.cinema.domain.dto.billboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelBillboardDTO {
    private String affectedCustomers;
}
