package br.com.alura.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EntityScan("br.com.alura.forum.model")
@SpringBootApplication
@EnableCaching
class ForumApplication

fun main(args: Array<String>) {
	runApplication<ForumApplication>(*args)
}
