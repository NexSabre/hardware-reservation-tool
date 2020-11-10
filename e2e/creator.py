from machines import add_example_machine, remove_all_machines

import argparse

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("-m", "--machines", type=int, default=1)
    ap.add_argument("-c", "--cleanAll", action="store_true")
    args = ap.parse_args()

    if args.cleanAll:
        remove_all_machines()
        exit()

    for i in range(args.machines):
        add_example_machine()

if __name__ == "__main__":
    main()
