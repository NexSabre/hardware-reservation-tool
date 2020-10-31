import json
import requests

import configuration as c


def get_machine(machine_id):
    r = requests.get(c.api_v1(f"/api/v1/machine/{machine_id}"))
    try:
        return json.loads(r.content)
    except Exception as e:
        print(e)
        return {}


def get_all_machines():
    content = requests.get(c.api_v1("/machines")).content
    return json.loads(content)


def add_example_machine():
    r = requests.post(c.api_v1("/machines"), json={
        "name": c.random_name(),
        "address": c.random_ip()
    })
    assert r.status_code == 201, "Return code should be Created"
    try:
        return json.loads(r.content)
    except Exception as e:
        print(e)
        return {}


def add_and_verifies_new_machine():
    l_before = count_machines()
    example_machine = add_example_machine()
    l_after = count_machines()
    assert l_after > l_before, "One machine should be added"
    return example_machine


def count_machines() -> int:
    return len(get_all_machines())


def remove_specific_machine(machine_id):
    r = requests.get(c.api_v1(f"/machines/{machine_id}/delete"))
    return True if r.status_code == 204 else False


def remove_all_machines():
    all_machines = get_all_machines()
    for machine in all_machines:
        r = requests.get(c.api_v1(f"/machines/{machine['id']}/delete"))
        assert r.status_code == 204, "Should return No Content"

    assert count_machines() == 0, "All machines should be removed"
