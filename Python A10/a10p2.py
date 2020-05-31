"""
COMP 1005 - Fall 2016
Assignment 10
Problem 2
"""

def plotStats(dictionary):
	'''
	Display the contents of the input dictionary
	as a bar plot: labels are the keys of the dictionary,
	the bar lengths are the values corresponding to the
	keys. Be sure to sort the keys.

	This function should return nothing. It should print
	to the screen (using the print function) the bar plot.
	'''
	retVal = ""
	if(len(dictionary)>0):
		retVal = retVal + "keys|values\n"
		retVal = retVal + "----+-------------\n"
	for key in dictionary:
		numHashes = dictionary[key]
		strHashes = ""
		for i in range(0, numHashes):
			strHashes=strHashes+"#"
		if(key<10):
			key = "0"+str(key)
		retVal = retVal + "  %s|%s" % (key, strHashes) + '\n'

	print (retVal)
	#return None

def main():
	'''
	main method to test your plotStats method
	'''

	# example from the assignment
	d = { 3:5, 4:2, 5:1, 8:1, 10:1}
	print("the plot for d = { 3:5, 4:2, 5:1, 8:1, 10:1} should look like")
	print("keys|values")
	print("----+-------------")
	print("  03|#####")
	print("  04|##")
	print("  05|#")
	print("  08|#")
	print("  10|#")

	print("Your plot is")
	plotStats(d)

	# use this main method to do your own testing of your
	# function


if __name__ == '__main__':
	main()
