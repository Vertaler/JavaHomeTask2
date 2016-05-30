import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Данил on 29.05.20 16.
 */
//Метод serialize позволяет сериализовать в строку классы, все
//не transient поля которых примитивные
//Поля transient не сериализуются
public class StringSerializable {
    //Получение значние поля и крнвертирование его в строку
    //Если поле не примитивно кидается исключение NotPrimitiveException
    private String getFieldValue(Field field) throws NotPrimitiveException {
        try {
            Class fieldType = field.getType();

            if (!fieldType.isPrimitive()) {
                throw new NotPrimitiveException(field);
            }
            field.setAccessible(true);//Для доступа к private полям
            String fieldTypeName = fieldType.getName();

            // Получение имени метода в зависимости от типа поля. Например, getInt
            String methodName = "get" + fieldTypeName.substring(0, 1).toUpperCase() + fieldTypeName.substring(1);

            String result = Field.class.getMethod(methodName, Object.class).invoke(field, this).toString();// Получение значения поля
            field.setAccessible(false);
            return result;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException error) {
            //Эти исключения могут возникнуть в 28 строке
            //Но из-за проверки поля на примитивность и установки Accesible флага их не должно быть
            return "";
        }
    }

    public String serialize() throws NotPrimitiveException {
        String result = "";
        StringBuilder resultBuilder = new StringBuilder(result);
        for (Field field : this.getClass().getDeclaredFields()) {
            if ( (field.getModifiers() & Modifier.TRANSIENT) == 0  ) {
                String fieldValue = getFieldValue(field);
                resultBuilder.append(field.getName());
                resultBuilder.append(": ");
                resultBuilder.append(fieldValue);
                resultBuilder.append(";\n");
            }
        }
        result = resultBuilder.toString();
        return result;
    }
}
