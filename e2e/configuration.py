import random
from os import getenv


def api_v1(endpoint):
    base = getenv('TEST_ENDPOINT', 'http://localhost:8080/api/v1')
    endpoint = ''.join(endpoint[1:]) if endpoint[0] == "/" else endpoint
    return f"{base}/{endpoint}"


def random_ip():
    ip_raw = [str(random.randrange(255)) for _ in range(4)]
    return ".".join(ip_raw)
