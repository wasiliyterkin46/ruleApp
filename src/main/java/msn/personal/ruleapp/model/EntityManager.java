package msn.personal.ruleapp.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityManager {
    private static final Map<String, Set<Entity>> parentsOnly = new HashMap<>();
    private static Graph<Entity, DefaultEdge> network = new SimpleGraph<>(DefaultEdge.class);
    // Тут храним все списки сущностей. Ключ - тип сущности.
    private static Map<String, List<Entity>> entities = new HashMap<>();
    private static List<String> types = new ArrayList<>();

    static {
        types.add("функция");
        types.add("требование");
        createTestEntity("функция");
        createTestEntity("требование");
    }

    private static void createTestEntity(String typeEntity) {
        Entity oldEntity = null;
        for (int i = 0; i < 10; i++) {
            var entity = new Entity(typeEntity, typeEntity + "_" + i);
            addEntity(entity);
            if (i % 2 == 0) {
                oldEntity = entity;
            } else {
                setParent(oldEntity, entity);
            }
        }
    }

    public static Set<Entity> getParentsOnly(String typeEntity) {
        return parentsOnly.getOrDefault(typeEntity, new HashSet<>());
    }

    public static boolean directConnectionExist(Entity e1, Entity e2) {
        return true;
    }

    public static void createEntity(String typeEntity, String nameEntity) {
        if (types.contains(typeEntity)) {
            Entity entity = new Entity(typeEntity, nameEntity);
        }
    }

    public static void addEntity(Entity entity) {
        var typeEntity = entity.getType();

        addToParentsOnly(typeEntity, entity);
        addToEntities(typeEntity, entity);
        addToNetwork(entity);
    }

    private static void addToParentsOnly(String typeEntity, Entity entity) {
        parentsOnly.get(typeEntity).add(entity);
    }

    private static void addToEntities(String typeEntity, Entity entity) {
        if (!entities.containsKey(typeEntity)) {
            entities.put(typeEntity, new ArrayList<>());
        }
        entities.get(typeEntity).add(entity);
    }

    private static void addToNetwork(Entity entity) {
        network.addVertex(entity);
    }

    public static void addEdge(Entity e1, Entity e2) {
        network.addEdge(e1, e2);
    }

    public static void setParent(Entity parent, Entity child) {
        var parentType = parent.getType();
        var childType = child.getType();

        if (parentType.equals(childType)) {
            var oldParent = child.getParent();
            if (oldParent != null) {
                oldParent.getChildren().remove(child);
            }

            child.setParent(parent);
            parent.getChildren().add(child);
            parentsOnly.get(childType).remove(child);
        } else {
            System.out.println("ALARM. Типы родителя и ребенка не совпадают");
        }
    }

}
