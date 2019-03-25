package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.model.EntityModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateUtil {
    public static <T extends EntityModel> T updateWhereNotNull(T updated, T dbEntity) {
        try {
            Map<String, Object> fieldMap = getNonNullFields(updated);

            Map<String, Field> dbEntityFields = Arrays.stream(dbEntity.getClass().getDeclaredFields())
                    .collect(Collectors.toMap(Field::getName, f -> f));

            for (Map.Entry<String, Object> field : fieldMap.entrySet()) {
                Field dbField = dbEntityFields.get(field.getKey());

                if (dbField != null) {
                    dbField.setAccessible(true);
                    dbField.set(dbEntity, field.getValue());
                }
            }

            return dbEntity;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    private static <T extends EntityModel> Map<String, Object> getNonNullFields(T entity) throws Exception {
        Field[] fields = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f-> !Modifier.isStatic(f.getModifiers())).toArray(Field[]::new);

        Map<String, Object> nonNulls = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(entity);

            if (value != null) {
                nonNulls.put(field.getName(), value);
            }
        }

        return nonNulls;
    }
}
