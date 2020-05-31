"""
COMP 1005 - Fall 2016
Assignment 10
Problem 3
"""

from random import randint
from time import clock

def sublist(S, low, high):
	'''
	Create a sublist of S that contains all values in S
	that are >= low and <= high.
	S will be a list of all numbers.

	This should be very efficient!

	The input S will contain both low and high.
	'''
	
	output = []
	for x in S:
		if(x>=low and x<=high):
			output.insert(len(output), x)
	return output

def checkIncreasing(S):
	for index in range(len(S)-1):
		if S[index] >= S[index+1]:
			return False
	return True


def main():
	'''
	main method to test your sublist method
	'''

	size = 1000
	nums = [ 5*num - randint(0,4) for num in range(size)]

	if checkIncreasing(nums):
		print("list is OK")
	else:
		print("list is not sorted!")

	numtests = 100
	correct = 0

	time1 = clock()
	for test in range(numtests):
		low_index = randint(0, size-10)
		high_index = randint(low_index+1, size-1)

		subnums = sublist(nums, nums[low_index], nums[high_index])
		if subnums == nums[low_index:high_index+1]:
			correct += 1

	time2 = clock()
	print("number of lists correct", correct, "/", numtests)
	print("time for tests", time2 - time1, "seconds")

	# use this main method to do your own testing of your
	# function



if __name__ == '__main__':
	main()
