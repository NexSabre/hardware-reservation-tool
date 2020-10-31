import unittest

import requests

from e2e import configuration as c


class TestMachines(unittest.TestCase):
    def setUp(self) -> None:
        self.get = requests.get

    @staticmethod
    def test_add_one_machine(self):
        l_before = self.count_machines()
        self.add_example_machine()
        l_after = self.count_machines()

        self.assertGreater(l_after, l_before, "One machine should be added")

    def tearDown(self) -> None:
        all_machines = self.get_all_machines()
        for machine in all_machines:
            r = requests.get(c.api_v1(f"/machines/{machine['id']}/delete"))
            assert r.status_code == 204, "Should return No Content"
        self.assertFalse(self.count_machines(), "All machines should be removed")
