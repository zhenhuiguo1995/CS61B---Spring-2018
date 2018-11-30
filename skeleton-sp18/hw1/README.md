# Notes for hw1

- Interface declaration with generics

```java
public interface List<Item>
// items stands for a generic type
```

- If a class uses a generic type <T>, there's no need to declare a variable with type <T>, instead use Object

```java
public class Test<T> {
    private Object[] test; // don't uses <T>
    test = new Object[10]; //use Object instead of its type T
}
```





 



