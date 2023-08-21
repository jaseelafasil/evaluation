package com.example.data.database


import com.example.data.database.tables.SchemeTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(config:ApplicationConfig){
        println("connecting db ${config}")
        val database=Database.connect(CreateHikariDataSource(
            url=config.property("storage.jdbcURL").getString(),
            //url="jdbc:postgresql://localhost/postgres",
            driver = config.property("storage.driverClassName").getString(),
            //driver="org.postgresql.Driver",
            password = config.property("storage.password").getString(),
            //password="root",
            username = config.property("storage.username").getString()
            //username = "postgres"
        ))
        transaction(database){
            SchemaUtils.create(SchemeTable)
        }
    }


}

private fun CreateHikariDataSource(url:String,driver:String,username:String,password:String)=HikariDataSource(
    HikariConfig().apply {
        driverClassName=driver
        jdbcUrl=url
        maximumPoolSize=7
        isAutoCommit=false
        transactionIsolation="TRANSACTION_REPEATABLE_READ"
        this.password=password
        this.username=username
        validate()

    }
)
suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }