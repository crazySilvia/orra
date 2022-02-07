package de.silvia.backend.api;

import de.silvia.backend.models.Artikel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtikelListDto {
    @Id
    @NonNull
    private String listName;
    private List<Artikel> artikelList;
}