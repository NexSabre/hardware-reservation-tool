import json
import requests

from api_cmds import configuration as c


def is_reserved(machine_id):
    r = requests.get(c.api_v1(f"/reserve/{machine_id}"))
    machine = json.loads(r.content)
    if not machine["start"]:
        return False
    return True


def get_reserved(machine_id):
    r = requests.get(c.api_v1(f"/reserve/{machine_id}"))
    try:
        return json.loads(r.content)
    except Exception as e:
        print(e)
        return {}


def get_release_reservation(machine_id):
    status = requests.get(c.api_v1(f"/reserve/{machine_id}/release"))
    return True if status.status_code == 200 else False


def post_reservation(machined_id, start=None, duration=0, **kwargs, ):
    json_data = {
        "id": machined_id,
        "duration": duration
    }
    if start:
        json_data["start"] = start

    if kwargs:
        json_data = kwargs

    status = requests.post(c.api_v1(f"/reserve/{machined_id}"), json=json_data)
    return True if status.status_code == 200 else False
