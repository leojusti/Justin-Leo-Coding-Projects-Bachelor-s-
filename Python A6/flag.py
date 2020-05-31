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
    targetPoint = math.sqrt(float(getHeight()/3 * getHeight()/3)/2)
    
    p1x = 0.0
    p1y = targetPoint
    
    p2x = 0.0
    p2y = float(getHeight())
    
    p3x = float(getWidth() - targetPoint)
    p3y = float(getHeight())
    
    #u=upper triangle
    #p=lower triangle
    
    u1x = targetPoint
    u1y = 0.0
    
    u2x = float(getHeight())
    u2y = 0.0
    
    u3x = float(getHeight())
    u3y = float(getWidth() - targetPoint)
    
    alpha = float(((p2y - p3y)*(x - p3x) + (p3x - p2x)*(y - p3y)) /
        ((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y)));
    beta = float(((p3y - p1y)*(x - p3x) + (p1x - p3x)*(y - p3y)) /
           ((p2y - p3y)*(p1x - p3x) + (p3x - p2x)*(p1y - p3y)));
    gamma = float(1.00 - alpha - beta);
    
    alphaU = float(((u2y - u3y)*(x - u3x) + (u3x - u2x)*(y - u3y)) /
        ((u2y - u3y)*(u1x - u3x) + (u3x - u2x)*(u1y - u3y)));
    betaU = float(((u3y - u1y)*(x - u3x) + (u1x - u3x)*(y - u3y)) /
           ((u2y - u3y)*(u1x - u3x) + (u3x - u2x)*(u1y - u3y)));
    gammaU = float(1.00 - alpha - beta);

    if((alpha>0.0) and (beta>0.0) and (gamma>0.0)) or ((alphaU>0.0) and (betaU>0.0) and (gammaU>0.0)):
        return '#FF4000'
    else:
        return '#FFFFFF'


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
