import json
import requests
from requests import status_codes

import configuration as c

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

def post_reservation(machined_id, start=None, duration=0):
    json_data = {
        "id": machined_id,
        "duration": duration
        }
    if start:
        json_data["start"] = start
    
    status = requests.post(c.api_v1(f"/reserve/{machined_id}"), json=json_data)
    return True if status.status_code == 200 else False 
