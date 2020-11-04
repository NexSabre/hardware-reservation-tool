import unittest

import reservations
import machines
import yaml


def load_spring_configuration():
    with open("../src/main/resources/application-staging.yaml") as property_file:
        app_property = yaml.load(property_file.read())
        return app_property


class TestReservations(unittest.TestCase):
    def setUp(self) -> None:
        self.example_machine = machines.add_example_machine()

    def test_get_all_reservations(self):
        r = reservations.get_all_reservations()
        assert r, "Should return at least one available machine for reservation"

    def test_get_data_about_specific_machine_at_reservation(self):
        r = reservations.get_specific_reservation(self.example_machine["id"])
        assert r, "Should return at information about specific reservation"

    def test_negative_try_reserve_protected_machine_wo_password(self):
        r = reservations.get_specific_reservation(self.example_machine["id"])

        self.assertTrue(machines.protect(self.example_machine["id"],
                                         password=load_spring_configuration().get("rules").get("password")),
                        "Response from the service should be True")
        self.assertTrue(r["protected"], "Machine should be protected")

    def tearDown(self) -> None:
        machines.remove_all_machines()
