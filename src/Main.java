/**
 * Created by Данил on 29.05.2016.
 */
public class Main {
    public static void main(String[] args){
        try {
            StringSerializable example = new CorrectExample();
            String result = example.serialize();
            System.out.println(result);
            example = new IncorrectExample();
            result = example.serialize();
            System.out.println(result);// Не выполнится, так как будет брошено исключение
        }
        catch(NotPrimitiveException e){
            System.out.println( e.getMessage() );
        }
    }
}
