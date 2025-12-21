package msn.personal.ruleapp.model;

import java.util.HashSet;
import java.util.Set;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Экземпляры сущностей разных типов
@RequiredArgsConstructor
public class Entity {
    @NonNull @Getter private String type;
    @NonNull @Getter @Setter private String name;

    @Getter @Setter private Entity parent;
    @Getter private Set<Entity> children = new HashSet<>();
}
