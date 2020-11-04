import unittest

from machines import *
from reservations import count_reservations


class TestMachines(unittest.TestCase):
    def setUp(self) -> None:
        self.test_machine = add_and_verifies_new_machine()

    def test_add_one_machine(self):
        add_and_verifies_new_machine()

    def test_add_one_machine_and_get_details(self):
        machines_response = get_machine(self.test_machine["id"])

        self.assertEqual(machines_response["address"], self.test_machine["address"])
        self.assertEqual(machines_response["id"], self.test_machine["id"])

    def test_delete_one_machine(self):
        l_before = count_machines()
        created_machine = add_and_verifies_new_machine()

        self.assertTrue(remove_specific_machine(created_machine["id"]), "Service should return No Content")
        self.assertEqual(l_before, count_machines(), "Machine should be removed")

    def test_after_creation_machine_should_be_enabled(self):
        test_machine = get_machine(self.test_machine["id"])

        assert test_machine.get("enabled"), "Machine should be default enabled"

    def test_after_creation_machine_try_to_enable_twice(self):
        test_machine = get_machine(self.test_machine["id"])

        self.assertTrue(test_machine["enabled"], "Machine by the default should be enabled: True")
        self.assertTrue(enable(test_machine["id"]), "Machine should return Ok, even if is in this state")
        self.assertTrue(get_machine(test_machine["id"]).get("enabled"), "Machine should be enabled")

    def test_after_creation_machine_try_disable(self):
        test_machine = get_machine(self.test_machine["id"])

        self.assertTrue(test_machine["enabled"], "Machine by the default should be enabled: True")
        self.assertTrue(disable(test_machine["id"]), "Machine should return Ok, even if is in this state")
        self.assertFalse(get_machine(test_machine["id"]).get("enabled"), "Machine should be disabled")

    def test_add_two_machines_disable_one(self):
        second_machine = get_machine(add_example_machine()["id"])

        l_machines_before = count_machines()
        l_reservations_before = count_reservations()

        disable(second_machine["id"])

        self.assertEqual(l_machines_before, count_machines(), "Machines should be the same number as before")
        self.assertLess(count_reservations(), l_reservations_before, "One machine should not be visible at "
                                                                     "reservations")

    def tearDown(self) -> None:
        remove_all_machines()
        self.assertFalse(count_machines(), "tearDown should remove all machines")
