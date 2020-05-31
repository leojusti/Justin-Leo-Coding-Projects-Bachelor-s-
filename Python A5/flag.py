# using the SimpleGraphics library
from SimpleGraphics import *

# use the random library to generate random numbers
import random

# size of the circles
diameter = 15


##
# sets the name of the output window
# sets the size of the output window
#
# you can change these
##
setWindowTitle("Flag of .....")
resize(500,500)




##
# returns a vaid color based on the input coordinates
#
# @param x is an x-coordinate 
# @param y is a y-coordinate 
# @return a colour based on the input x,y values for the given flag
##
def define_colour(x,y):
    
    newY=(10/16)*getWidth()
    if (y<= newY):
        #return yellow if x value is between 5/16s of width and 7/16s of width or y is between 4/10ths of new height and 6/10ths of new height.
        if ((x>=(getWidth()*5/16)) and (x<=getWidth()*7/16)) or ((y>=(4/10)*newY) and (y<=(6/10)*newY)):
            return "#FFFF00"
        #else return blue for background
        else:
            return "#0000CD"

    

    return None


# repeat until window is closed
# do NOT change anything in this while loop
while not closed():
    

    # generate random x and y values 
    x = random.randint(0, getWidth())
    y = random.randint(0,getHeight())

    # set colour for current circle
    setFill( define_colour(x,y) )

    # draw the current circle
    ellipse(x, y, diameter, diameter)