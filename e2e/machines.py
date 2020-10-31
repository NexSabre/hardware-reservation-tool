import json
import random

import requests

import e2e.configuration as c


def get_all_machines():
    content = requests.get(c.api_v1("/machines")).content
    return json.loads(content)


def add_example_machine():
    r = requests.post(c.api_v1("/machines"), json={
        "name": f"Test Machine {random.randrange(2048)}",
        "address": c.random_ip()
    })
    assert r.status_code == 201, "Return code should be Created"


def count_machines(self):
    return len(get_all_machines())
