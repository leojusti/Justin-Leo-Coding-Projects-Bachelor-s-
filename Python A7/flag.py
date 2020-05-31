# using the SimpleGraphics library
from SimpleGraphics import *

# tell SimpleGraphics to only draw when I use the update() function
setAutoUpdate(False)


# use the random library to generate random numbers
import random
import math

# size of the circles drawn
diameter = 15

resize(600, 600)


##
# returns a vaid color based on the input coordinates
#
# @param x is an x-coordinate 
# @param y is a y-coordinate 
# @return a colour based on the input x,y values for the given flag
##

def define_colour(x,y):
    centerx = (7/18)*getWidth()
    centery = (1/2)*getHeight()
    radius = (4/18)*getWidth()
    tempx = (x-centerx)*(x-centerx)
    tempy = (y-centery)*(y-centery)
    d = math.sqrt(tempx+tempy)
    
    if (y<getHeight()/2): # height less than half of the height
        if(d<=radius): # if point is inside the circle
            return ("#EF1F35")
        else:
            return ("#FFFFFF")
    else: # height greater than half of the height
        if(d<=radius):
            return ("#FFFFFF")
        else:
            return ("#EF1F35")

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
