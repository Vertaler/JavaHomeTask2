/**
 * Created by Данил on 29.05.2016.
 */
//Класс, все поля которого примитивные
public class CorrectExample extends StringSerializable {
    private int intField = 42;
    private double doubleField = 2.71828;
    private boolean boolField = true;
    private short shortField = 1;
    private char charField = 'O';
    private byte byteField = 0;
    private float floatField = 1.1f;
    private long longField = 24556677;
    public transient double transientField = 3.1415;
    private transient String notPrimitive = "But transient";
}
