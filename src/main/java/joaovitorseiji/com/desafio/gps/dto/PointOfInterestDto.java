package joaovitorseiji.com.desafio.gps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record PointOfInterestDto(@NotBlank String name, @PositiveOrZero Long x, @PositiveOrZero Long y) {

}
