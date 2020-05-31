# using the SimpleGraphics library
from SimpleGraphics import *

# tell SimpleGraphics to only draw when I use the update() function
setAutoUpdate(False)


# use the random library to generate random numbers
import random


# size of the circles drawn
diameter = 5

resize(600, 300)

# takes in co-ordinates of a trianlge, see if the point x,y is inside the trianlge,
# if it is inside the triangle, return TRUE, else FAlSE
def isInTriangle(p1x, p1y, p2x, p2y, p3x, p3y, x, y):
	alpha = float(((p2y - p3y)*(x - p3x) + (p3x - p2x)*(y - p3y)) /((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y)));
	beta = float(((p3y - p1y)*(x - p3x) + (p1x - p3x)*(y - p3y)) /((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y)));
	gamma = float(1.00 - alpha - beta);

	if((alpha>0.0) and (beta>0.0) and (gamma>0.0)):
		return True
	else:
		return False
##
# returns a vaid color based on the input coordinates
#
# @param x is an x-coordinate
# @param y is a y-coordinate
# @return a colour based on the input x,y values for the given flag
##
def define_colour(x,y):
	inTriangle1 = isInTriangle(0, 0, 200, 0, 0, 300, x, y)
	inTriangle2 = isInTriangle(200, 0, 400, 0, 0, 300, x, y)
	inTriangle3 = isInTriangle(600, 100, 600, 200, 0, 300, x, y)
	inTriangle4 = isInTriangle(600, 200, 600, 300, 0, 300, x, y)

	if (inTriangle1):
		return "#255F87"
	elif (inTriangle2):
		return 'yellow'
	elif (inTriangle3):
		return 'white'
	elif (inTriangle4):
		return 'green'
	else:
		return 'red'

######################################################################
#
# Do NOT change anything below this line
#
######################################################################

# repeat until window is closed
while not closed():
	for i in range(500):
		# generate random x and y values
		x = random.randint(0, getWidth())
		y = random.randint(0, getHeight())

		# set colour for current circle
		setFill( define_colour(x,y) )

		# draw the current circle
		ellipse(x, y, diameter, diameter)
	update()
