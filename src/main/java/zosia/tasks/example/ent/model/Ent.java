package zosia.tasks.example.ent.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ent {

    @Id
    private String name;

    private EntType type;

    private int height;

    @ManyToOne
    private Copse copse;

}
