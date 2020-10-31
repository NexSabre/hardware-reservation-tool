import unittest

from machines import *


class TestMachines(unittest.TestCase):
    def test_add_one_machine(self):
        l_before = count_machines()
        add_example_machine()
        l_after = count_machines()

        self.assertGreater(l_after, l_before, "One machine should be added")

    def test_delete_one_machine(self):
        l_before = count_machines()
        created_machine = add_example_machine()

        l_after_add = count_machines()
        self.assertGreater(l_after_add, l_before)
        assert remove_specific_machine(created_machine["id"]), "Service should return No Content"
        self.assertEqual(l_before, count_machines(), "Machine should be removed")

    def tearDown(self) -> None:
        remove_all_machines()
