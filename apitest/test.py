import sys
import unittest
import requests
import uuid

def is_subdict(small, big):
    return dict(big, **small) == big

def is_valid_uuid(val):
    try:
        uuid.UUID(str(val), version=4)
        return True
    except ValueError:
        return False

class DogAPITester(unittest.TestCase):
    API_URL = None

    @classmethod
    def post_req(cls, uri, body):
        return requests.post("http://" + cls.API_URL.rstrip('/') + uri, json=body)

    @classmethod
    def get_req(cls, uri):
        return requests.get( "http://" + cls.API_URL.rstrip('/') + uri)

    def test_get_by_id_invalid_param(self):
        response = self.get_req('/dogs/not_uuid')
        self.assertEqual(response.status_code, 400)

    def test_add_dog_with_invalid_date(self):
        response = self.post_req("/dogs", { "name": "fido", "breed": "labrador", "birthDate": "2022/02/02"})
        self.assertEqual(response.status_code, 400)


    def test_add_dog_without_name(self):
        req = { "birthDate": "2022-02-02", "breed": "labrador" }
        response = self.post_req("/dogs", req)
        self.assertEqual(response.status_code, 400)

    def test_add_dog_without_birth_data(self):
        req = { "name": "fido", "breed": "labrador"}
        response = self.post_req("/dogs", req)
        self.assertEqual(response.status_code, 400)

    def test_add_dog_without_breed(self):
        req = { "name": "fido", "birthDate": "2022-02-02"}
        response = self.post_req("/dogs", req)
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertEqual(is_subdict(req, data), True)
        self.assertEqual(is_valid_uuid(data["id"]), True)

    def test_add_dog(self):
        req = { "name": "fido", "birthDate": "2022-02-02", "breed": "labrador"}
        response = self.post_req("/dogs", req)
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertEqual(is_subdict(req, data), True)
        self.assertEqual(len(data), 4)
        self.assertEqual(is_valid_uuid(data["id"]), True)

    def test_add_dog_empty_name(self):
        req = { "name": "", "birthDate": "2022-02-02", "breed": "labrador"}
        response = self.post_req("/dogs", req)
        self.assertEqual(response.status_code, 400)

    def test_get_all_dogs_with_new_data(self):
        req = { "name": "fido2", "birthDate": "2022-02-02"}
        response = self.post_req("/dogs", req)
        newDog = response.json()
        response = self.get_req("/dogs")
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertIn(newDog, data)
        self.assertGreaterEqual(len(data), 2)

    def test_get_dog_with_id(self):
        req = { "name": "fido2", "birthDate": "2022-02-02"}
        response = self.post_req("/dogs", req)
        newDog = response.json()
        response = self.get_req("/dogs/" + newDog["id"])
        data = response.json()
        self.assertEqual(response.status_code, 200)
        self.assertEqual(newDog, data)

if __name__ == '__main__':
    if len(sys.argv) > 1:
        DogAPITester.API_URL = sys.argv.pop()
    else:
        raise Exception('The API URL is expected as first and only argument')

    unittest.main()