import unittest

import machines
import reservations
import reserve
from test_reservations import get_protect_password


class TestReserve(unittest.TestCase):
    def setUp(self) -> None:
        self.example_machine = machines.add_example_machine()

    def test_add_reservation(self):
        r = reserve.post_reservation(self.example_machine["id"])
        assert r, ""
        
    def test_release_reservation(self):
        r = reserve.post_reservation(self.example_machine["id"])
        assert r, ""
        assert reserve.is_reserved(self.example_machine["id"])
        
    def test_negative_add_wrong_id_during_reservation(self):
        example_id = self.example_machine["id"]
        wrong_example_id = self.example_machine["id"] + 1 
        
        reserve.post_reservation(example_id, kwargs={"id": wrong_example_id})
        self.assertFalse(reserve.is_reserved(example_id),
                         "Should not reserve machine, during reserve process wrong id was provided")

    def test_try_to_reserve_protected_machines(self):
        self.assertTrue(machines.protect(self.example_machine["id"], password=get_protect_password()))

        reservation = reservations.get_specific_reservation(self.example_machine["id"])

        self.assertTrue(reservation.get("protected"))
        self.assertTrue(reservation.get("enabled"))
        self.assertTrue(self.example_machine["address"] in [x["address"] for x in reservations.get_all_reservations()])

        self.assertFalse(reserve.is_reserved(self.example_machine["id"]))
        self.assertFalse(reserve.post_reservation(self.example_machine))

    def tearDown(self) -> None:
        machines.remove_all_machines()
