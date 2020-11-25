import unittest

from api_cmds import machines, reservations, reserve
from test_reservations import get_protect_password


class TestReserve(unittest.TestCase):
    def setUp(self) -> None:
        self.example_machine = machines.add_example_machine()

    def test_add_reservation(self):
        reserve.post_reservation(self.example_machine["id"])
        is_reserved = reserve.is_reserved(self.example_machine["id"])
        self.assertTrue(is_reserved)

    def test_add_reservation_for_4_hours(self):
        reserve.post_reservation(self.example_machine["id"], duration=4)
        is_reserved = reserve.is_reserved(self.example_machine["id"])
        self.assertTrue(is_reserved)

        reserve_response = reserve.get_reserved(self.example_machine["id"])
        self.assertIsNotNone(reserve_response["start"])
        self.assertIsNotNone(reserve_response["ends"])

        delta_time = (reserve_response["ends"] - reserve_response["start"]) / 3_600_000
        self.assertEqual(int(delta_time), 4, "Delta should be 4 hours")

    def test_release_reservation(self):
        reserve.post_reservation(self.example_machine["id"])
        is_reserved = reserve.is_reserved(self.example_machine["id"])
        self.assertTrue(is_reserved)

        reserve.get_release_reservation(self.example_machine["id"])
        is_reserved = reserve.is_reserved(self.example_machine["id"])
        self.assertFalse(is_reserved)
        
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
        self.assertFalse(reserve.post_reservation(self.example_machine["id"]))

    def tearDown(self) -> None:
        machines.remove_all_machines()
