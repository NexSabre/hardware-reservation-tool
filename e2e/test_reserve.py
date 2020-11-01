import unittest

import machines
import reservations
import reserve

class TestReserve(unittest.TestCase):
    def setUp(self) -> None:
        self.example_machine = machines.add_example_machine()
        
    def tearDown(self) -> None:
        machines.remove_all_machines()
        
    def test_add_reservation(self):
        r = reserve.post_reservation(self.example_machine["id"])
        assert r, ""
        
    def test_release_reservation(self):
        r = reserve.post_reservation(self.example_machine["id"])
        assert r, ""
        assert reserve.is_reserved(self.example_machine["id"])