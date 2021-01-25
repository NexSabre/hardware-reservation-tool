from api_cmds.machines import *

class FillDemo():
    def __init__(self):
        #  create tests machine
        self.test_machine = add_and_verifies_new_machine()
        for i in range(10):
            add_and_verifies_new_machine()


if __name__ == "__main__":
    demo = FillDemo()
