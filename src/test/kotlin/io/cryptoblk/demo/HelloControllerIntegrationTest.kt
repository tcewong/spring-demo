package io.cryptoblk.demo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import java.net.URL
import org.assertj.core.api.Assertions.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIntegrationTest {

    @LocalServerPort private var port: Int = 0

    private lateinit var base: URL

    @Autowired private lateinit var template: TestRestTemplate

    @BeforeEach
    @Throws(Exception::class)
    fun setup() {
        this.base = URL("http://localhost:$port/")
    }

    @Test
    @Throws(Exception::class)
    fun getHello() {
        val resp = template.getForEntity(base.toString(), String::class.java)
        assertThat(resp.body).isEqualTo("Greetings from Spring Boot!")
    }

}