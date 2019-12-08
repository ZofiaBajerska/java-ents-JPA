package zosia.tasks.example.ent.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Copse {

    @Id
    private String name;

    @OneToMany(mappedBy = "copse", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Ent> ents = new ArrayList<>();

    public int getPopulation(){
        return ents.size();
    }
    public Copse(String name) {
        this.name = name;
    }

}
