import unittest

from api_cmds import machines, reservations, reserve

from test_helpers.helpers import load_spring_configuration


class TestReservations(unittest.TestCase):
    def setUp(self) -> None:
        self.example_machine = machines.add_example_machine()

    def test_get_all_reservations(self):
        r = reservations.get_all_reservations()
        assert r, "Should return at least one available machine for reservation"

    def test_get_data_about_specific_machine_at_reservation(self):
        r = reservations.get_specific_reservation(self.example_machine["id"])
        assert r, "Should return at information about specific reservation"

    def test_negative_try_reserve_protected_machine_w_password(self):
        self.assertTrue(machines.protect(self.example_machine["id"],
                                         password=load_spring_configuration().get("rules").get("password")),
                        "Response from the service should be True")

        r = reservations.get_specific_reservation(self.example_machine["id"])

        self.assertTrue(r["protected"], "Machine should be protected")

    def test_negative_try_reserve_protected_machine_wo_password(self):
        self.assertFalse(machines.protect(self.example_machine["id"],
                                          password="wrongPaSwOrD*&"),
                         "Response from the service should be False")

        r = reservations.get_specific_reservation(self.example_machine["id"])

        self.assertFalse(r["protected"], "Machine should be unprotected")

    def test_turn_on_protection_and_try_disable_with_wrong_password(self):
        self.assertTrue(machines.protect(self.example_machine["id"],
                                         password=load_spring_configuration().get("rules").get("password")),
                        "Response from the service should be True")

        r = reservations.get_specific_reservation(self.example_machine["id"])

        self.assertTrue(r["protected"], "Machine should be protected")
        self.assertFalse(machines.protect(self.example_machine["id"],
                                          password="wrongPaSwOrD*&"),
                         "Response from the service should be False")
        self.assertTrue(r["protected"], "Machine should be protected")

    def test_turn_on_protection_and_try_disable_w_password(self):
        self.assertTrue(machines.protect(self.example_machine["id"],
                                         password=load_spring_configuration().get("rules").get("password")),
                        "Response from the service should be True")

        r = reservations.get_specific_reservation(self.example_machine["id"])
        self.assertTrue(r["protected"], "Machine should be protected")
        self.assertTrue(machines.unprotect(self.example_machine["id"],
                                           password=load_spring_configuration().get("rules").get("password")),
                        "Response from the service should be True")

        r = reservations.get_specific_reservation(self.example_machine["id"])
        self.assertFalse(r["protected"], "Machine should be protected")

    def test_get_all_reservations_show_only_available(self):
        r = reservations.get_all_reservations()
        machines.add_example_machine()

        self.assertTrue(reserve.post_reservation(self.example_machine["id"]), "Reservation request should be true")

        all_reservations = reservations.get_all_reservations()
        available_reservations = reservations.get_all_reservations(available=True)

        self.assertFalse([x for x in available_reservations if x["start"]])
        self.assertLess(len(available_reservations), len(all_reservations), "Len of available reservations should be "
                                                                            "lower than all reservations")

    def tearDown(self) -> None:
        machines.remove_all_machines()
