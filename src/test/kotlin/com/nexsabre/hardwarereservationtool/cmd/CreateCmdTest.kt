package com.nexsabre.hardwarereservationtool.cmd

import com.nexsabre.hardwarereservationtool.cmd.helpers.getAllMachines
import org.junit.jupiter.api.Test
import org.springframework.util.Assert
import com.nexsabre.hardwarereservationtool.cmd.main as Application

internal class CreateCmdTest {
    val testMachine = Pair("Test Machine", "10.10.19.19")

    @Test
    fun `Create a test machine`() {
//        val listOfMachines = getAllMachines() ?: listOf()
        val command = arrayOf("create", "-n", testMachine.first, "-a", testMachine.second)
        Application(command)

        val listOfMachinesAfterOperation = getAllMachines() ?: listOf()
        Assert.notEmpty(listOfMachinesAfterOperation)
        Assert.notNull(listOfMachinesAfterOperation.first {
            it.name == testMachine.first
            it.address == testMachine.second
        })
    }
}