import unittest

from machines import *


class TestMachines(unittest.TestCase):
    def test_add_one_machine(self):
        l_before = count_machines()
        add_example_machine()
        l_after = count_machines()

        self.assertGreater(l_after, l_before, "One machine should be added")

    def tearDown(self) -> None:
        remove_all_machines()
