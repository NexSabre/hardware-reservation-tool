import json
import requests

import configuration as c


def get_all_machines():
    content = requests.get(c.api_v1("/machines")).content
    return json.loads(content)


def add_example_machine():
    r = requests.post(c.api_v1("/machines"), json={
        "name": c.random_name(),
        "address": c.random_ip()
    })
    assert r.status_code == 201, "Return code should be Created"


def count_machines():
    return len(get_all_machines())


def remove_all_machines():
    all_machines = get_all_machines()
    for machine in all_machines:
        r = requests.get(c.api_v1(f"/machines/{machine['id']}/delete"))
        assert r.status_code == 204, "Should return No Content"

    assert count_machines() == 0, "All machines should be removed"
