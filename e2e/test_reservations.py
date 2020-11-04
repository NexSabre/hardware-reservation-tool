import unittest

import reservations
import machines


class TestReservations(unittest.TestCase):
    def setUp(self) -> None:
        self.example_machine = machines.add_example_machine()

    def test_get_all_reservations(self):
        r = reservations.get_all_reservations()
        assert r, "Should return at least one available machine for reservation"

    def test_get_data_about_specific_machine_at_reservation(self):
        r = reservations.get_specific_reservation(self.example_machine["id"])
        assert r, "Should return at information about specific reservation"

    def tearDown(self) -> None:
        machines.remove_all_machines()
