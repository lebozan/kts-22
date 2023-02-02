package ftn.kts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RideEstimateDTO {

    private int estimateInMinutes, estimatedCost;
}
