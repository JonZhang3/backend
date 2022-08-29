## 关于 JSON 序列化
### Enum
> 在 entity/domain bean 中尽量使用枚举实现枚举值

例如：

```java
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

class User {
    private String username;
    private UserType type;
}

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum UserType {
    ADMIN(1, "管理员"),
    ;

    private final Integer value;
    private final String desc;

    public UserType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
```
在注解中使用了两种注解

`@JsonValue`: 在get方法或类属性中使用该注解，将属性值（get方法的返回值）作为json中相应key的值

例如：User(username="张三", type=UserType.ADMIN) 转换为 json 为 {"username": "张三", "type": 1}

`@JsonFormat(shape = JsonFormat.Shape.OBJECT)`: 在类上使用该注解，将以以下的形式输出json：

{"username": "张三", "type": {"value": 1, desc: "管理员"}}

如果这两种方式均不能满足序列化，可以继承`StdSerializer` ，然后在相应的枚举类上使用`@JsonSerialize(using = XXXSerializer.class)`注解自定义序列化形式

反序列化需要继承 `StdDeserializer`

相关的其他注解：
1. `@JsonProperty`: 
