package me.mircoporetti.coroutinesblog.api.post

import io.micronaut.test.support.TestPropertyProvider
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class MongoDBIntegrationTest : TestPropertyProvider {

    companion object {
        @Container
        private val mongoContainer: MongoDBContainer = MongoDBContainer("mongo:4.4.3").apply { start() }
    }

    override fun getProperties(): Map<String, String> {
        return mapOf(
            "mongodb.uri" to mongoContainer.replicaSetUrl
        )
    }
}
