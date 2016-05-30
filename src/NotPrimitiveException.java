/**
 * Created by Данил on 29.05.2016.
 */
import java.lang.reflect.Field;

public class NotPrimitiveException extends  Exception{
    private Field field;
    @Override
    public String getMessage(){
        return "Field " + field.getName() + " isn\'t primitive. It\'s type – " + field.getType().getName();
    }
    public NotPrimitiveException(Field field){
        this.field = field;
    }
}
