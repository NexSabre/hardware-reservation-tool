import unittest

from machines import *


class TestMachines(unittest.TestCase):
    def test_add_one_machine(self):
        add_and_verifies_new_machine()

    def test_add_one_machine_and_get_details(self):
        created_machine = add_and_verifies_new_machine()
        machines_response = get_machine(created_machine["id"])
        self.assertEqual(machines_response["address"], created_machine["address"])
        self.assertEqual(machines_response["id"], created_machine["id"])

    def test_delete_one_machine(self):
        l_before = count_machines()
        created_machine = add_and_verifies_new_machine()

        assert remove_specific_machine(created_machine["id"]), "Service should return No Content"
        self.assertEqual(l_before, count_machines(), "Machine should be removed")

    def test_after_creation_machine_should_be_enabled(self):
        test_machine = get_machine(add_example_machine().get("id"))
        assert test_machine.get("enabled"), "Machine should be default enabled"

    def test_after_creation_machine_try_to_enable_twice(self):
        test_machine = get_machine(add_example_machine().get("id"))
        self.assertTrue(test_machine.get("enabled"), "Machine by the default should be enabled: True")
        self.assertTrue(enable(test_machine.get("id")), "Machine should return Ok, even if is in this state")
        self.assertTrue(get_machine(test_machine.get("id")).get("enabled"), "Machine should be enabled")

    def test_after_creation_machine_try_disable(self):
        test_machine = get_machine(add_example_machine().get("id"))
        self.assertTrue(test_machine.get("enabled"), "Machine by the default should be enabled: True")
        self.assertTrue(disable(test_machine.get("id")), "Machine should return Ok, even if is in this state")
        self.assertFalse(get_machine(test_machine.get("id")).get("enabled"), "Machine should be disabled")

    def tearDown(self) -> None:
        remove_all_machines()
