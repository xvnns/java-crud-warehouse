package com.example.crudwarehouse.config;

import com.example.crudwarehouse.constant.SpringDocConst;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Swagger config for OpenAPI
 */
@OpenAPIDefinition(
        info = @Info(
                title = SpringDocConst.INFO_TITLE,
                description = SpringDocConst.INFO_DESCRIPTION,
                version = SpringDocConst.INFO_VERSION,
                contact = @Contact(
                        name = SpringDocConst.INFO_CONTACT_NAME,
                        email = SpringDocConst.INFO_CONTACT_EMAIL
                )
        )
)
public class OpenApiConfig {
}
