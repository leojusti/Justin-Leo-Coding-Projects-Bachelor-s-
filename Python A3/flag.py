# using the SimpleGraphics library
from SimpleGraphics import *
import math

# use the random library to generate random numbers
import random

diameter = 15


##
# returns a valid colour based on the input coordinates
#
# @param x is an x-coordinate 
# @param y is a y-coordinate 
# @return a colour based on the input x,y values for the given flag
##
def define_colour(x,y):
    newy = (2/3)*getWidth()
    centerx = getWidth()/2
    centery = newy/2
    radius = (3/10)*newy
    # check if y is greater than 2/3 of x
     # if so then dont do anything
        # else return white color
    if(y<=newy): #any y more than 2/3 of x is ignored
        tempx = (x-centerx)*(x-centerx)
        tempy = (y-centery)*(y-centery)
        d = math.sqrt(tempx+tempy)
        if(d<=radius):
            return ("#DC143C")
        else:
            return ("#FFFFFF")

# repeat until window is closed
while not closed():
        
        # generate random x and y values 
        x = random.randint(0, getWidth())
        y = random.randint(0,getHeight())

        # set colour for current circle
        setFill( define_colour(x,y) )

        # draw the current circle
        ellipse(x, y, diameter, diameter)