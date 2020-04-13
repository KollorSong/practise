>@RequestMapping 几个参数的用法:

1. **value(path)**:指定请求的实际地址，指定的地址可以是 URI Template 模式，此参数可以对多个URL进行映射配置。
2. **method**:指定请求的 method 类型。例如 GET、POST、PUT、DELETE 等。
3. **consumes**:指定处理请求的提交内容类型（Content-Type），例如application/json，text/html。
4. **produces**:指定返回的内容类型，仅当 request 请求头中的（Accept）类型中包含该指定类型才返回。
5. **params**:指定 request 中必须包含某些参数值，才让该方法处理请求。
6. **header**:指定 request 中必须包含某些指定的 header 值，才能让该方法处理请求。 
---


 