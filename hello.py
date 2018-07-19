a=10
b=20
print(a+b)

print(a*b)

#dealing with argument parser 
# import argparse
# parser = argparse.ArgumentParser()
# parser.add_argument("echo")
# args = parser.parse_args()
# print (args.echo)

import argparse
parser = argparse.ArgumentParser()
parser.add_argument("square", help="display a square of a given number")
args = parser.parse_args()
print (args.square**2)



