import json
from typing import Dict

import requests

import configuration as c


def get_specific_reservation(machine_id) -> Dict:
    r = requests.get(c.api_v1(f"/reservations/{machine_id}"))
    try:
        return json.loads(r.content)
    except Exception as e:
        print(e)
        return {}


def get_all_reservations() -> Dict:
    content = requests.get(c.api_v1("/reservations")).content
    return json.loads(content)


def count_reservations() -> int:
    return len(get_all_reservations())
