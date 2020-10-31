import unittest
import requests
import json


class TestInfo(unittest.TestCase):
    def setUp(self) -> None:
        self.response = requests.get("http://localhost:8080/api/v1/info")
             
    def test_check_info(self):
        assert self.response, "Info server do not work"
        
    def test_info_should_return_200(self):
        assert self.response.status_code == 200, "Should return a 200"
        
    def test_info_should_conatain_version(self):
        response_body = json.loads(self.response.content)
        self.assertTrue(response_body.get("version", False), "Key version should exist")
       