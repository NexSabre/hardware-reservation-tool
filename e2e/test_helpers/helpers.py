import yaml


def load_spring_configuration():
    with open("../src/main/resources/application-staging.yaml") as property_file:
        app_property = yaml.load(property_file.read(), Loader=yaml.SafeLoader)
        return app_property


def get_protect_password() -> str:
    return load_spring_configuration().get("rules").get("password")
