package dev.nandeeshkamath.undercoverbookie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class UndercoverBookieApplication

fun main(args: Array<String>) {
	runApplication<UndercoverBookieApplication>(*args)
}
