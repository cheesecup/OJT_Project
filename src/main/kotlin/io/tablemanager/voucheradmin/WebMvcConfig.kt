package io.tablemanager.voucheradmin

import org.springframework.context.annotation.Configuration
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.data.web.config.EnableSpringDataWebSupport
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(
        resolvers: MutableList<HandlerMethodArgumentResolver>
    ) {
        resolvers.add(PageableHandlerMethodArgumentResolver())
        super.addArgumentResolvers(resolvers)
    }
}