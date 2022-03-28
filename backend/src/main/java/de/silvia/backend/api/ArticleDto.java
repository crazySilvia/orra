package de.silvia.backend.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    @Id
    @NotNull
    private String name;
    private float amount;
    private String unit;
}