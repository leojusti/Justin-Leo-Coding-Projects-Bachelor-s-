"""
COMP 1005 - Fall 2016
Assignment 10
Problem 1
"""
import re

def wordLengthStats(filename):
	'''
	Complete this function. Your function should not print anything to
	the screen (remove the print statement). Your function should
	return a dictionary as specified in the assignment specs.
	Change the return value to the dictionary you create.
	'''

	file = open(filename, 'r')
	stuff = {}

	for line in file:
		line = line.strip()
		for word in line.split(" "):
			wordLen = len(str(word))
			wordCount = 0

			#check if the key exists in the dictionary already
			if wordLen in stuff:
				wordCount = stuff[wordLen]

			stuff[wordLen] = wordCount + 1
	return stuff



def main():
	'''
	main method to test your wordLengthStats method
	'''

	# example from the assignment
	d = wordLengthStats("sample.txt")
	print("d should be { 3:5, 4:2, 5:1, 8:1, 10:1} ")
	print("d is", d)

	# use this main method to do your own testing of your
	# function


if __name__ == '__main__':
	main()
