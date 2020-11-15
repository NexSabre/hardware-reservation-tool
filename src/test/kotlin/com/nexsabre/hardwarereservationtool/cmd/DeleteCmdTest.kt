package com.nexsabre.hardwarereservationtool.cmd

import com.nexsabre.hardwarereservationtool.cmd.helpers.checkMachineExistsById
import com.nexsabre.hardwarereservationtool.cmd.helpers.createNewMachine
import com.nexsabre.hardwarereservationtool.cmd.helpers.deleteAllMachines
import com.nexsabre.hardwarereservationtool.cmd.helpers.getAllMachines
import com.nexsabre.hardwarereservationtool.server.models.Element
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.nexsabre.hardwarereservationtool.cmd.main as Application

internal class DeleteCmdTest {
    private val testMachineData: Pair<String, String> = Pair("TestMachine", "10.10.10.10")
    private lateinit var testMachine: Element

    @BeforeEach
    fun setUp() {
        deleteAllMachines()

        createNewMachine(testMachineData.first, testMachineData.second)
        testMachine = getAllMachines()!!.first {
            it.name == testMachineData.first && it.address == testMachineData.second
        }
    }

    @Test
    fun `Delete one machine`() {
        val command = arrayOf("delete", "-i", testMachine.id.toString())
        Application(command)

        assert(!checkMachineExistsById(testMachine.id!!))
    }

    @Test
    fun `Delete all machines`() {
        createNewMachine("Second test machine", "20.20.20.20")
        assert(getAllMachines()!!.size == 2)

        val command = arrayOf("delete", "--all")
        Application(command)

        assert(getAllMachines()!!.isEmpty())
    }
}
