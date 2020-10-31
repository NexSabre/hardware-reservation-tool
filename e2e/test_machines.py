import unittest

from machines import *


class TestMachines(unittest.TestCase):
    def test_add_one_machine(self):
        add_and_verifies_new_machine()

    def test_add_one_machine_and_get_details(self):
        created_machine = add_and_verifies_new_machine()
        assert get_machine(created_machine["id"]), "Newly created machine should return details"

    def test_delete_one_machine(self):
        l_before = count_machines()
        created_machine = add_and_verifies_new_machine()

        assert remove_specific_machine(created_machine["id"]), "Service should return No Content"
        self.assertEqual(l_before, count_machines(), "Machine should be removed")

    def tearDown(self) -> None:
        remove_all_machines()
