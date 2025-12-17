package msn.personal.ruleapp.model;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    private String name;
    private List<Entity> entities;
    // Правила формирования списка
    private HowToMakeAList howToMakeAList;

    public EntityList(String name) {
        this.name = name;
        entities = new ArrayList<>();
    }
}
