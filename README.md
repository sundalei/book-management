# book-management
## step 1 validate template project working
```java
@RestController
public class HelloController {
    
    @GetMapping("/")
    public String greeting() {
        return "hello world";
    }
}
```

## step 2 use mongodb as backend storage and spring data mongo 
BookRepository