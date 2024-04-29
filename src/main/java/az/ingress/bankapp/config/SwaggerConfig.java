package az.ingress.bankapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        title = "Bank Application API",
        description = "API for customers' bank operations",
        version = "1.0",
        contact = @Contact(
                name = "Mahammad Ilyazov",
                email = "ilyazovmehemmed@gmail.com",
                url = "https://github.com/Mahammad55"
        )
))
public class SwaggerConfig {
}
